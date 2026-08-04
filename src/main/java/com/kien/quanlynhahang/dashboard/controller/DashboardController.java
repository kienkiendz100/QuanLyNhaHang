package com.kien.quanlynhahang.dashboard.controller;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dashboard.dto.*;
import com.kien.quanlynhahang.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardDTO> dashboard(

            @RequestParam LocalDate tuNgay,

            @RequestParam LocalDate denNgay){

        return ApiResponse.success(

                "Lấy dashboard thành công",

                dashboardService.dashboard(
                        tuNgay,
                        denNgay));

    }
}