package com.cg.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cg.tasks.Task1;
import com.cg.tasks.Task2;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jobs;
	@Autowired
	private StepBuilderFactory steps;
	@Bean
	public Step step1() {
		return steps.get("step1").tasklet(new Task1()).build();
	}
	@Bean
	public Step step2() {
		return steps.get("step2").tasklet(new Task2()).build();
	}
	@Bean
	public Job demoJob() {
		return jobs.get("demoJob").incrementer(new RunIdIncrementer()).start(step1()).next(step2()).build();
	}
}
