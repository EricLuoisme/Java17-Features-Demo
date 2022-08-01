package com.roylic.java17.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@Configuration
@EnableAsync
public class AsyncConfiguration {

    @Bean("controllerExecutor")
    public Executor controllerExecutor() {
        return new ThreadPoolExecutor(2,
                2,
                10,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * For Async Handling, if we just shut-down, it might cause database problem
     * (if the db not stored the stuff correctly)
     */
    @Bean("controllerScheduleExecutor")
    public Executor controllerScheduleExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("contr-sche-");
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(10);
        return threadPoolTaskExecutor;
    }

}
