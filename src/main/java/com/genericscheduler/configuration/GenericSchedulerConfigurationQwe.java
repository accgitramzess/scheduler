package com.genericscheduler.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//@Configuration
@Service
public class GenericSchedulerConfigurationQwe {

    @Scheduled(cron = "0 0/5 * * * *")
    public void position() {
        System.out.println(LocalDateTime.now() + " Sync Position");
    }

    @Scheduled(cron = "0 0/10 * * * *")
    public void securities() {
        System.out.println(LocalDateTime.now() + " Sync Securities");
    }
}
