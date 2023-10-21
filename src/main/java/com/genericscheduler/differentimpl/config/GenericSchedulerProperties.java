package com.genericscheduler.differentimpl.config;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "crons")
public class GenericSchedulerProperties {

    private List<Cron> crons;

    @Getter
    @Setter
    public static class Cron {

        private String cron;
        private String event;
        private Long timeOut;
        private Integer retry;
    }
}
