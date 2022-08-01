package com.roylic.java17.demo.service;

import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Testing for Async & Sync Controller's difference
 */
public abstract class AbstractTask {

    private static Random random = new Random();

    public void doTaskOne() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Task 1 Start");
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        stopWatch.stop();
        System.out.println("Task 1 Finished, with time consuming: " + stopWatch.getTotalTimeMillis() + "ms");
    }

    public void doTaskTwo() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Task 2 Start");
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println("Task 2 Finished");
        stopWatch.stop();
        System.out.println("Task 2 Finished, with time consuming: " + stopWatch.getTotalTimeMillis() + "ms");
    }

    public void doTaskThree() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Task 3 Start");
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.println("Task 3 Finished");
        stopWatch.stop();
        System.out.println("Task 3 Finished, with time consuming: " + stopWatch.getTotalTimeMillis() + "ms");
    }

}
