package com.roylic.java17.demo.controller;

import com.roylic.java17.demo.service.AsyncCallbackTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<String> future_1 = asyncCallbackTask.doTaskOneCallback();
        CompletableFuture<String> future_2 = asyncCallbackTask.doTaskTwoCallback();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future_1, future_2);
        voidCompletableFuture.join();
        return "finished";
    }


    //    @PostExchange()
    public String simple_Endpoint() {
        return null;
    }


}
