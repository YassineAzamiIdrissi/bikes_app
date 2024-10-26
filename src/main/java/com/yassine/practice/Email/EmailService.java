package com.yassine.practice.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Async
    public void sendEmail(String to,
                          String username,
                          EmailTemplateName emailTemplateName,
                          String activationUrl,
                          String activationCode,
                          String subject)
            throws MessagingException {
        String emailTemplate;
        if (emailTemplateName != null) {
            emailTemplate = emailTemplateName.getName();
        } else {
            emailTemplate = "recover_password";
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper
                (message,
                        MULTIPART_MODE_MIXED,
                        UTF_8.name());
        Map<String, Object> props = new HashMap<>();
        props.put("username", username);
        props.put("activation_code", activationCode);
        props.put("confirmationUrl", activationUrl);

        Context context = new Context();
        context.setVariables(props);
        messageHelper.setFrom("AppSupportTeam@gmail.com");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);

        String template = templateEngine.process(emailTemplate, context);
        messageHelper.setText(template, true);
        mailSender.send(message);
    }
}
