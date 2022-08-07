package com.roylic.java17.demo.controller;

import com.roylic.java17.demo.service.AsyncCallbackTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncCallbackTask asyncCallbackTask;


//    @Async("controllerScheduleExecutor")
    @PostMapping("/test")
    @Async("controllerScheduleExecutor")
    public CompletableFuture<String> getFutureTest() throws Exception {

        log.debug(">>>> Receive Calling");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<String> future_1 = asyncCallbackTask.doTaskOneCallback();
        CompletableFuture<String> future_2 = asyncCallbackTask.doTaskTwoCallback();
        CompletableFuture<String> future_3 = asyncCallbackTask.doTaskThreeCallback();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future_1, future_2, future_3);
        voidCompletableFuture.join();
        stopWatch.stop();
        log.debug("<<< Response Calling");

        return CompletableFuture.completedFuture("Finished, in time: " + stopWatch.getTotalTimeMillis() + "ms");
    }

    @PostMapping("/test2")
    @Async("controllerExecutor")
    public DeferredResult<String> getFutureTest_withDeferredResult() throws Exception {

        DeferredResult<String> output = new DeferredResult<>();

        log.debug(">>>> Receive Calling");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<String> future_1 = asyncCallbackTask.doTaskOneCallback();
        CompletableFuture<String> future_2 = asyncCallbackTask.doTaskTwoCallback();
        CompletableFuture<String> future_3 = asyncCallbackTask.doTaskThreeCallback();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future_1, future_2, future_3);
        voidCompletableFuture.join();
        stopWatch.stop();
        log.debug("<<< Response Calling");

        output.setResult(String.valueOf(stopWatch.getTotalTimeMillis()));
        return output;
    }
}
