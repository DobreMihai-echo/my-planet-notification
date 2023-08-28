package com.myplanet.notificationservice.domain;

import com.myplanet.notificationservice.config.TwilioConfig;
import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioInitializer {

    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioInitializer(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(
                twilioConfig.getAccountSid(),
                twilioConfig.getAuthToken()
        );
        log.info("Twilio is initialized...with account sid{}",twilioConfig.getAccountSid());
    }
}
