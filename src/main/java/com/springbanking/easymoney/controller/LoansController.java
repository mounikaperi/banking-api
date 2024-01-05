package com.springbanking.easymoney.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {
    @GetMapping("/myCards")
    public String getCardDetails() {
        return "Here are the card details from the DB";
    }
}
