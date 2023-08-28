package com.myplanet.notificationservice.domain;

public interface SMSSender {
    void sendSms(SMSRequest smsRequest) throws IllegalAccessException;
}
