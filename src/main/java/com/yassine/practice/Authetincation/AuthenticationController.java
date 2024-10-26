package com.yassine.practice.Authetincation;

import com.yassine.practice.Email.MailingRespDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
@Tag(name = "authentication")
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("register")
    ResponseEntity<?> registerUser(
            @Valid @RequestBody RegistrationRequest req
    ) throws MessagingException {
        authService.registerUser(req);
        return ResponseEntity.ok().build();
    }

    @GetMapping("activate-account")
    ResponseEntity<?> activateAccount(@RequestParam("code") String code)
            throws MessagingException {
        authService.activateAccount(code);
        return ResponseEntity.ok().build();
    }

    @PostMapping("authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest req
    ) {
        return ResponseEntity.ok().body(
                authService.authenticateUser(req)
        );
    }

    @PatchMapping("set-new-password")
    ResponseEntity<MailingRespDto> setNewPassword
            (@RequestParam("recoveryCode") String recoveryCode,
             @RequestBody @Valid RecoverPasswordRequest req) {
        return ResponseEntity.ok().body
                (authService.specifyNewPassword(recoveryCode, req));
    }

    @PatchMapping("set-recover-code")
    ResponseEntity<MailingRespDto> specifyRecoverCode(
            @RequestParam("recoveryCode") String recoveryCode
    ) throws MessagingException {
        return ResponseEntity.ok().
                body(authService.setRecoveryCode(recoveryCode));
    }

    @GetMapping("demand-recover")
    ResponseEntity<MailingRespDto> recoverPass(@RequestParam("email") String email)
            throws MessagingException {
        return ResponseEntity.accepted().body
                (authService.sendRecoveryEmail(email));
    }
}
