package com.myplanet.notificationservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myplanet.notificationservice.domain.Message;
import com.myplanet.notificationservice.domain.SMSRequest;
import com.myplanet.notificationservice.domain.TwilioSMSSender;
import com.myplanet.notificationservice.service.EmailService;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
public class KafkaListenerCustom {

    @Autowired
    private TwilioSMSSender twilioSmsSender;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "${spring.kafka.topic.name}")
    void listener(String message) throws IllegalAccessException, IOException, MessagingException, TemplateException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message data = objectMapper.readValue(message, Message.class);
        System.out.println("ReceivedP:" + data);
        if (data.getToEmail()!=null) {
            emailService.sendAccountVerifyEmail(data);
        }
        if (data.getToPhone()!=null){
            System.out.println("MY PHONE NUMBER:" + data.getToPhone());
            twilioSmsSender.sendSms(new SMSRequest(data.getToPhone(),"This is a message from the small wieners club. Unfortunatelly Mister Robert Sava you do not qualify to join our club as your penis is masive."));
        }
    }

    @KafkaListener(topics = "provider-approval")
    void listener2(Message data) throws IllegalAccessException, IOException, MessagingException, TemplateException {
        log.info("RECEIVED PPP");
        System.out.println("ReceivedP2affvbvsdsdAFASSFGAFGAGFGAGG:" + data);
        if (data.getToEmail()!=null) {
            emailService.sendAccountVerifyEmail(data);
        }
    }
}
