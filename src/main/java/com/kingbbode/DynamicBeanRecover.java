package com.kingbbode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Created by YG on 2017-01-11.
 */

@Service
@EnableScheduling
public class DynamicBeanRecover {

    @Autowired
    private DynamicBeanFactory dynamicBeanFactory;

    @Scheduled(cron = "0/30 * * * * *")
    public void recover() {
        this.dynamicBeanFactory.recovery(LocalTime.now().toSecondOfDay() + 86400);
    }
}
