package com.roylic.java17.demo.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
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
                5,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque(30),
//                new ThreadFactoryBuilder().setNameFormat("contr-th-%d").setDaemon(true).build(),
                new ThreadFactoryBuilder().setNameFormat("contr-th-%d").build(),
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
        threadPoolTaskExecutor.setMaxPoolSize(5);
        threadPoolTaskExecutor.setKeepAliveSeconds(10);
        threadPoolTaskExecutor.setQueueCapacity(30);
        threadPoolTaskExecutor.setThreadNamePrefix("contr-tak-");
        // default one
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(3);
        return threadPoolTaskExecutor;
    }

}
