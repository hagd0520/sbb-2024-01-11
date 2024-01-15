package com.ll.sbb20240111.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class Hello3JobConfig {
    @Bean
    public Job hello3Job(JobRepository jobRepository, Step hello3Step1, Step hello3Step2, Step hello3Step3) {
        Flow flow1 = new FlowBuilder<SimpleFlow>("flow1")
                .start(hello3Step1)
                .build();

        Flow flow2 = new FlowBuilder<SimpleFlow>("flow2")
                .start(hello3Step2)
                .build();

        Flow parallelFlow = new FlowBuilder<SimpleFlow>("parallelFlow")
                .split(new SimpleAsyncTaskExecutor())
                .add(flow1, flow2)
                .build();

        FlowJobBuilder jobBuilder = new JobBuilder("hello3Job", jobRepository)
                .start(parallelFlow)
                .next(hello3Step3)
                .end();


        return jobBuilder.build();
    }
    @Bean
    public Step hello3Step1(JobRepository jobRepository, Tasklet hello3Step1Tasklet1, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("hello3Step1Tasklet1", jobRepository)
                .tasklet(hello3Step1Tasklet1, platformTransactionManager)
                .build();
    }
    @Bean
    public Tasklet hello3Step1Tasklet1() {
        return ((contribution, chunkContext) -> {
            log.info("Hello World 3/1");
            System.out.println("Hello World 3/1");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Step hello3Step2(JobRepository jobRepository, Tasklet hello3Step2Tasklet1, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("hello3Step2Tasklet1", jobRepository)
                .tasklet(hello3Step2Tasklet1, platformTransactionManager)
                .build();
    }
    @Bean
    public Tasklet hello3Step2Tasklet1() {
        return ((contribution, chunkContext) -> {
            log.info("Hello World 3/2");
            System.out.println("Hello World 3/2");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Step hello3Step3(JobRepository jobRepository, Tasklet hello3Step3Tasklet1, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("hello3Step3Tasklet1", jobRepository)
                .tasklet(hello3Step3Tasklet1, platformTransactionManager)
                .build();
    }
    @Bean
    public Tasklet hello3Step3Tasklet1() {
        return ((contribution, chunkContext) -> {
            log.info("Hello World 3/3");
            System.out.println("Hello World 3/3");
            return RepeatStatus.FINISHED;
        });
    }
}