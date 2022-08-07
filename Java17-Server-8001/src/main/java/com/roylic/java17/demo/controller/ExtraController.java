package com.roylic.java17.demo.controller;

import com.roylic.java17.demo.service.AsyncCallbackTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Controller for testing @HttpExchange
 *
 * @author Roylic
 * 2022/7/25
 */
@RestController
@RequestMapping("/sync")
public class ExtraController {


    @Autowired
    private AsyncCallbackTask asyncCallbackTask;


    @PostMapping("/test")
    public String syncCallingTest() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        return "finished";
    }


    //    @PostExchange()
    public String simple_Endpoint() {
        return null;
    }


}
