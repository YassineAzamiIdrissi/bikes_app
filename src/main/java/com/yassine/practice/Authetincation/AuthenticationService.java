package com.yassine.practice.Authetincation;

import com.yassine.practice.ActivationCode.ActivationCode;
import com.yassine.practice.ActivationCode.ActivationCodeRepository;
import com.yassine.practice.Authority.Authority;
import com.yassine.practice.Authority.AuthorityRepository;
import com.yassine.practice.CustomExceptions.*;
import com.yassine.practice.Email.EmailService;
import com.yassine.practice.Email.MailingRespDto;
import com.yassine.practice.ResetPassword.ResetPasswordCode;
import com.yassine.practice.ResetPassword.ResetPasswordCodeRepository;
import com.yassine.practice.Security.JwtService;
import com.yassine.practice.User.User;
import com.yassine.practice.User.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.yassine.practice.Email.EmailTemplateName.ACTIVATE_ACCOUNT;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    // repositories :
    private final UserRepository userRepo;
    private final ActivationCodeRepository activationCodeRepo;
    private final AuthorityRepository authorityRepo;
    private final ResetPasswordCodeRepository resetPasswordCodeRepo;
    // utility beans :
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    // services :
    private final JwtService jwtService;
    private final EmailService emailService;
    // static value : activation url link :
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    // authenticate user :
    public AuthenticationResponse authenticateUser(
            AuthenticationRequest req
    ) {
        var authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                req.getEmail(),
                                req.getPassword()
                        ));
        User user = (User) authentication.getPrincipal();
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("fullname", user.userFullName());
        extraClaims.put("id", user.getId());
        String accessToken = jwtService.generateToken(
                extraClaims,
                user
        );
        return AuthenticationResponse.builder().
                accessToken(accessToken).
                build();
    }

    // register user :
    public void registerUser(
            RegistrationRequest req
    ) throws MessagingException {
        Authority authority = authorityRepo.findByAuthority("USER").
                orElseThrow(
                        () -> new AuthorityNotFoundException
                                ("Authorities are not initialized yet ! ")
                );
        User user = User.builder().
                firstname(req.getFirstname()).
                lastname(req.getLastname()).
                password(passwordEncoder.encode(req.getPassword())).
                email(req.getEmail()).
                enabled(false).
                locked(false).
                auths(List.of(authority)).
                build();
        userRepo.save(user);
        sendEmail(user, "Activate your account"
        );
    }

    // activate account :
    public void activateAccount(String activationCode)
            throws MessagingException {
        ActivationCode code = activationCodeRepo.findByActivationCode(
                activationCode
        ).orElseThrow(
                () -> new ActivationCodeNotFoundException
                        ("Incorrect activation code " + activationCode + " !")
        );
        if (code.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendEmail(code.getUser(), "Activate your account"
            );
            throw new ActivationCodeExpiredException
                    ("Token expired a new one has been already sent");
        }
        User user = userRepo.findById(code.getUser().getId()).orElseThrow
                (() -> new UsernameNotFoundException("Use not found "));
        user.setEnabled(true);
        code.setValidatedAt(LocalDateTime.now());
        userRepo.save(user);
        activationCodeRepo.save(code);
    }

    // sending the mail to the user :
    private void sendEmail(User user, String subject)
            throws MessagingException {
        String code = generateAndSaveActivationCode(user);
        emailService.sendEmail(
                user.getEmail(),
                user.userFullName(),
                ACTIVATE_ACCOUNT,
                activationUrl,
                code,
                subject
        );
    }

    // generating and saving the activation code :
    private String generateAndSaveActivationCode(
            User user
    ) {
        String code = generateActivationCode(10);
        ActivationCode activationCode =
                ActivationCode.builder().
                        user(user).
                        createdAt(LocalDateTime.now()).
                        expiresAt(LocalDateTime.now().plusMinutes(20)).
                        activationCode(code).
                        build();
        activationCodeRepo.save(activationCode);
        return code;
    }

    public MailingRespDto specifyNewPassword(
            String recoveryCode,
            RecoverPasswordRequest req
    ) {
        ResetPasswordCode code = resetPasswordCodeRepo.findByCode
                (recoveryCode).orElseThrow(
                () -> new RecoveryCodeNotFoundException
                        ("Code " + recoveryCode + " not found")
        );
        if (!Objects.equals(req.getPassword(), req.getConfirmPassword())) {
            throw new RecoveryErrorException("Passwords must be identical");
        }
        User user = code.getUser();
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepo.save(user);
        MailingRespDto resp = new MailingRespDto();
        resp.setMessage("Password updated..");
        return resp;
    }

    public MailingRespDto setRecoveryCode(String recoveryCode)
            throws MessagingException {
        ResetPasswordCode passCode = resetPasswordCodeRepo.findByCode(recoveryCode).
                orElseThrow(() -> new RecoveryCodeNotFoundException
                        ("Code " + recoveryCode + " is incorrect"));
        if (passCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendRecoveryEmail(passCode.getUser().getEmail());
            throw new RecoveryCodeExpiredException
                    ("This recovery code expired a new one has been already sent !");
        }
        passCode.setUsed(true);
        resetPasswordCodeRepo.save(passCode);
        MailingRespDto resp = new MailingRespDto();
        resp.setMessage("set your new password");
        return resp;
    }

    public MailingRespDto sendRecoveryEmail(String userEmail)
            throws MessagingException {
        User user = userRepo.findUserByEmail(userEmail).orElseThrow(
                () -> new UsernameNotFoundException
                        ("User with email " + userEmail + " not found")
        );
        String recoveryCode = generateAndSaveRecoveryCode(user);
        emailService.sendEmail(userEmail,
                user.userFullName(),
                null,
                activationUrl,
                recoveryCode,
                "Recover your password");
        MailingRespDto resp = new MailingRespDto();
        resp.setMessage("A mail sent to your email..");
        return resp;
    }

    private String generateAndSaveRecoveryCode(
            User user
    ) {
        String code = generateActivationCode(10);
        ResetPasswordCode recoveryCode = ResetPasswordCode.builder().
                code(code).
                createdAt(LocalDateTime.now()).
                expiresAt(LocalDateTime.now().plusMinutes(20)).
                used(false).
                user(user).
                build();
        resetPasswordCodeRepo.save(recoveryCode);
        return code;
    }

    // generating activation code :
    private String generateActivationCode(int length) {
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randIndex));
        }
        return sb.toString();
    }
}
