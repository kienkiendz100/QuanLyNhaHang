package com.kien.quanlynhahang.dashboard.controller;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dashboard.dto.DashboardDTO;
import com.kien.quanlynhahang.dashboard.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(
        name = "Dashboard",
        description = "API thống kê doanh thu, số lượng hóa đơn và khách hàng"
)
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(
            summary = "Lấy dữ liệu Dashboard",
            description = "Thống kê doanh thu, số lượng hóa đơn, khách hàng và các chỉ số trong khoảng thời gian được chọn."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Lấy dashboard thành công"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Dữ liệu đầu vào không hợp lệ",
                    content = @Content
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "Lỗi hệ thống",
                    content = @Content
            )
    })
    @GetMapping
    public ApiResponse<DashboardDTO> dashboard(

            @Parameter(
                    description = "Ngày bắt đầu thống kê",
                    example = "2026-08-01",
                    required = true
            )
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate tuNgay,

            @Parameter(
                    description = "Ngày kết thúc thống kê",
                    example = "2026-08-31",
                    required = true
            )
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate denNgay) {

        return ApiResponse.success(
                "Lấy dashboard thành công",
                dashboardService.dashboard(tuNgay, denNgay)
        );
    }
}