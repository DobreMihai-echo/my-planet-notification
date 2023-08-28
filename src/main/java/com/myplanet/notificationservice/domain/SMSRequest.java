package com.myplanet.notificationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
@AllArgsConstructor
public class SMSRequest {
    private final String phoneNumber;
    private final String message;
}
