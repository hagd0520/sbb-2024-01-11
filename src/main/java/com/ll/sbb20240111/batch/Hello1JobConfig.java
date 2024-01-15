package com.ll.sbb20240111.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class Hello1JobConfig {
    @Bean
    public Job hello1Job(JobRepository jobRepository, Step hello1Step) {
        return new JobBuilder("hello1Job", jobRepository)
                .start(hello1Step)
                .incrementer(new RunIdIncrementer())
                .build();
    }
    @Bean
    public Step hello1Step(JobRepository jobRepository, Tasklet hello1StepTasklet1, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("helloStep1Tasklet1", jobRepository)
                .tasklet(hello1StepTasklet1, platformTransactionManager)
                .build();
    }
    @Bean
    public Tasklet hello1StepTasklet1() {
        return ((contribution, chunkContext) -> {
            log.info("Hello World 1");
            System.out.println("Hello World 1");
            return RepeatStatus.FINISHED;
        });
    }
}