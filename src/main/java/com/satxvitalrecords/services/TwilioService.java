package com.satxvitalrecords.services;

import com.satxvitalrecords.models.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service ("textService")
public class TwilioService {

    private static final String ACCOUNT_SID = "AC3b031c8223c21f7739d3e2286ec02573";
    private static final String AUTH_TOKEN = "e635a7f9262ebd76703f0460d089b35a";

    public void sendText(User user){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String messageBody = "";
        Message message = Message
                .creator(new PhoneNumber(user.getPhone_num()), // to
                new PhoneNumber("+12109439303"), // from
                messageBody).create();


        System.out.println(message.getSid());
    }
}


