package com.roylic.java17.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class AsyncCallbackTask extends AbstractTask {
//    @Async
//    public Future<String> doTaskOneCallback() throws Exception {
//        super.doTaskOne();
//        return new AsyncResult<>("Task 1 totally Finished");
//    }

    @Async
    public CompletableFuture<String> doTaskOneCallback() throws Exception {
        super.doTaskOne();
        return CompletableFuture.completedFuture("Task 1 totally Finished");
    }

    @Async
    public CompletableFuture<String> doTaskTwoCallback() throws Exception {
        super.doTaskTwo();
        return CompletableFuture.completedFuture("Task 2 totally Finished");
    }

    @Async
    public CompletableFuture<String> doTaskThreeCallback() throws Exception {
        super.doTaskThree();
        return CompletableFuture.completedFuture("Task 3 totally Finished");
    }
}
