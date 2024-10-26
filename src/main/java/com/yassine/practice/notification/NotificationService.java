package com.yassine.practice.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(Integer userId, Notification notification) {
        log.info("Sending WS Notification to userId " + userId + " with payload " + notification);
        messagingTemplate.convertAndSendToUser
                (String.valueOf(userId),
                        "/notifications",
                        notification);
    }
}
