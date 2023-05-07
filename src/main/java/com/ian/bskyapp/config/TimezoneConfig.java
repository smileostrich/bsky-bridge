package com.ian.bskyapp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class TimezoneConfig {

    @PostConstruct
    public void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getDefault());
    }

}
