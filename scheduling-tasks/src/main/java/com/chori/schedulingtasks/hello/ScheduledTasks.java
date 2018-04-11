package com.chori.schedulingtasks.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.ZonedDateTime;

@Configuration
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

//    @Scheduled(cron = "1/3 * * * * *")
//    @Scheduled(fixedDelay = 3000)
    @Scheduled(fixedRate = 3000)
    public void reportCurrentTime() {
        log.info("The time is start {}", ZonedDateTime.now().toString());

        for(long i=0; i<10000000000l; i++) {
        }

        log.info("The time is end {}", ZonedDateTime.now().toString());
    }
}
