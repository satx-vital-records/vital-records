package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.ChargeRequest;
import com.satxvitalrecords.services.EmailService;
import com.satxvitalrecords.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.AuthenticationException;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private EmailService emailService;

    public ChargeController(EmailService emailService){
        this.emailService = emailService;
    }

    @GetMapping("/charge")
    public String show(){
        return "charge";
    }

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException, AuthenticationException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        sendEmail();

        System.out.println("Email sent");
        return "result";
    }


    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

        public String sendEmail(){
            emailService.prepareAndSend("Application successfully sent!", "Thank you for your application, we are locating your record and will send it to you as fast as possible. - San Antonio Vital Records");

            return "redirect:/";
        }
}
