package com.satxvitalrecords.services;

import com.satxvitalrecords.models.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.ui.Model;

public class TwilioService {

    public static final String ACCOUNT_SID = "AC3b031c8223c21f7739d3e2286ec02573";
    public static final String AUTH_TOKEN = "e635a7f9262ebd76703f0460d089b35a";

//    public static void main(String[] args) {
    public void sendText(Model model, User user){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(user.getPhone_num()), // to
                new PhoneNumber("+12109439303"), // from
                "Twilio phone test").create();


        System.out.println(message.getSid());
    }
}


