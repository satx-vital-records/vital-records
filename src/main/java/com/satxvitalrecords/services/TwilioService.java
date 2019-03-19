package com.satxvitalrecords.services;

import com.satxvitalrecords.models.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.ui.Model;

public class TwilioService {

    public static final String ACCOUNT_SID = "ACcc5401977e40cf09d248c89beac282bf";
    public static final String AUTH_TOKEN = "516f070174d5cbf6ea7dbb142eb43bf5";

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


