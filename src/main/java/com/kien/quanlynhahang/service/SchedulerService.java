package com.kien.quanlynhahang.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    @Scheduled(cron = "0 0 0 * * *")
    public void thongKeDoanhThu() {

    }

}