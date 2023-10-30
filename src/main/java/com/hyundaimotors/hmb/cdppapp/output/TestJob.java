package com.hyundaimotors.hmb.cdppapp.output;

import org.hamcrest.Condition.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TestJob {

    @Bean
    public Job testSimpleJob(JobRepository jobRepository, Step testStep){
        return new JobBuilder("testSimpleJob", jobRepository)
                .start(testStep)
                .build();

    }

    @Bean
    public Step testStep(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("testStep", jobRepository)
                .tasklet(testTasklet, platformTransactionManager).build();
    }

    @Bean
    public Tasklet testTasklet(){
        return ((contribution, chunkContext) -> {
            System.out.println("테스트1");

            return RepeatStatus.FINISHED;
        });
    }
}
