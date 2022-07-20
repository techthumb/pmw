package org.sterl.pmw.spring.config;

import org.quartz.Scheduler;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.sterl.pmw.component.SimpleWorkflowStepStrategy;
import org.sterl.pmw.component.WorkflowRepository;
import org.sterl.pmw.quartz.boundary.QuartzWorkflowService;
import org.sterl.pmw.quartz.job.PwmQuartzJobFactory;

@Configuration
public class PmwConfig {

    @Bean
    WorkflowRepository workflowRepository() {
        return new WorkflowRepository();
    }
    @Bean
    QuartzWorkflowService quartzWorkflowService(Scheduler scheduler) {
        return new QuartzWorkflowService(scheduler, workflowRepository());
    }
    @Bean
    SchedulerFactoryBeanCustomizer registerPwm(
            ApplicationContext applicationContext) {
        
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        return (sf) -> {
            sf.setJobFactory(new PwmQuartzJobFactory(
                    new SimpleWorkflowStepStrategy(), workflowRepository(), jobFactory));
        };
    }
}
