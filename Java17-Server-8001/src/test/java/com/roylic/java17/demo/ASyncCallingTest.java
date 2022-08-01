package com.roylic.java17.demo;

import com.roylic.java17.demo.service.AsyncCallbackTask;
import com.roylic.java17.demo.service.AsyncTask;
import com.roylic.java17.demo.service.ConcreteTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ASyncCallingTest {

    @Autowired
    private ConcreteTask concreteTask;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncCallbackTask asyncCallbackTask;


    @Test
    public void syncCallingTest() throws Exception {
        concreteTask.doTaskOne();
        concreteTask.doTaskTwo();
        concreteTask.doTaskThree();
    }

    @Test
    public void asyncCallingTest() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
        System.out.println();
    }

    @Test
    public void asyncCallingBackTest() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture<String> future_1 = asyncCallbackTask.doTaskOneCallback();
        CompletableFuture<String> future_2 = asyncCallbackTask.doTaskTwoCallback();
        CompletableFuture<String> future_3 = asyncCallbackTask.doTaskThreeCallback();

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future_1, future_2, future_3);
        voidCompletableFuture.join();

        stopWatch.stop();
        System.out.println("Finished all three tasks with total time: " + stopWatch.getTotalTimeMillis() + "ms");
    }
}
