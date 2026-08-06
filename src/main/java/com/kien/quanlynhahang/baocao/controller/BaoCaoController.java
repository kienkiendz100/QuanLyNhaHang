package com.kien.quanlynhahang.baocao.controller;

import com.kien.quanlynhahang.baocao.dto.BaoCaoDoanhThuDTO;
import com.kien.quanlynhahang.baocao.service.BaoCaoService;
import com.kien.quanlynhahang.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class BaoCaoController {

    private final BaoCaoService baoCaoService;

    @GetMapping("/revenue")
    public ApiResponse<BaoCaoDoanhThuDTO> layBaoCaoDoanhThu() {

        return ApiResponse.success(
                "Lấy báo cáo doanh thu thành công",
                baoCaoService.layBaoCaoDoanhThu()
        );
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> xuatPDF() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=BaoCaoDoanhThu.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(baoCaoService.xuatPDF());
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> xuatExcel() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=BaoCaoDoanhThu.xlsx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(baoCaoService.xuatExcel());
    }
}