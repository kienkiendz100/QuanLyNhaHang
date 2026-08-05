package com.kien.quanlynhahang.export.controller;

import com.kien.quanlynhahang.export.service.ExcelService;
import com.kien.quanlynhahang.export.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Xuất dữ liệu", description = "API xuất hóa đơn ra file Excel và PDF")
public class ExportController {

    private final ExcelService excelService;
    private final PdfService pdfService;

    @Operation(
            summary = "Xuất danh sách hóa đơn ra Excel",
            description = "Tải xuống file Excel chứa toàn bộ danh sách hóa đơn."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Xuất file Excel thành công"),
            @ApiResponse(responseCode = "500", description = "Lỗi khi xuất file", content = @Content)
    })
    @GetMapping("/hoadon/export/excel")
    public ResponseEntity<byte[]> exportHoaDon() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=DanhSachHoaDon.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelService.exportHoaDon());
    }

    @Operation(
            summary = "Xuất hóa đơn ra PDF",
            description = "Tải xuống file PDF của một hóa đơn theo mã hóa đơn."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Xuất file PDF thành công"),
            @ApiResponse(responseCode = "404", description = "Không tìm thấy hóa đơn"),
            @ApiResponse(responseCode = "500", description = "Lỗi khi xuất file", content = @Content)
    })
    @GetMapping("/hoadon/{maHD}/pdf")
    public ResponseEntity<byte[]> exportPdf(
            @Parameter(
                    description = "Mã hóa đơn cần xuất",
                    example = "1",
                    required = true
            )
            @PathVariable Integer maHD) {

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=HoaDon_" + maHD + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfService.exportHoaDon(maHD));
    }
}