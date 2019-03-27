package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.ChargeRequest;
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

        return "/confirmation";
    }


    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "/confirmation";
    }


}
