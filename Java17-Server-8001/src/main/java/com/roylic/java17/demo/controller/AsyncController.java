package com.roylic.java17.demo.controller;

import com.roylic.java17.demo.service.AsyncCallbackTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController("/async")
public class AsyncController {

    @Autowired
    private AsyncCallbackTask asyncCallbackTask;

    @PostMapping("/test")
    @Async("controllerExecutor")
    public CompletableFuture<String> getFutureTest() throws Exception {
        CompletableFuture<String> future_1 = asyncCallbackTask.doTaskOneCallback();
        CompletableFuture<String> future_2 = asyncCallbackTask.doTaskTwoCallback();
        CompletableFuture<String> future_3 = asyncCallbackTask.doTaskThreeCallback();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future_1, future_2, future_3);
        voidCompletableFuture.join();

        return CompletableFuture.completedFuture("finished");
    }
}
