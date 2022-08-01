package com.roylic.java17.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

/**
 * Controller for testing @HttpExchange
 *
 * @author Roylic
 * 2022/7/25
 */
@RestController
public class ExtraController {

    @PostExchange()
    public String simple_Endpoint() {
        return null;
    }


}
