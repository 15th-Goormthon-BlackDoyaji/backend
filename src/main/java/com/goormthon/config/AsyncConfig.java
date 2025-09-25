package com.goormthon.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "asyncTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 운영 환경에 따라 이 값들을 조정하세요.
        executor.setCorePoolSize(10);            // 유지할 최소 스레드 수
        executor.setMaxPoolSize(50);             // 최대 생성 스레드 수
        executor.setQueueCapacity(200);          // 큐에 들어갈 작업 수
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("async-");

        // 큐가 가득 찼고 최대치에 도달했을 때 정책
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 애플리케이션 종료시 작업 완료를 기다리게 함
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);

        executor.initialize();
        return executor;
    }
}

