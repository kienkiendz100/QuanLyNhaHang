package com.kien.quanlynhahang.export.controller;

import com.kien.quanlynhahang.export.service.ExcelService;
import com.kien.quanlynhahang.export.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExportController {

    private final ExcelService excelService;
    private final PdfService pdfService ;
    @GetMapping("/hoadon/export/excel")
    public ResponseEntity<byte[]> exportHoaDon() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=DanhSachHoaDon.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelService.exportHoaDon());

    }
    @GetMapping("/hoadon/{maHD}/pdf")
    public ResponseEntity<byte[]> exportPdf(
            @PathVariable Integer maHD) {

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=HoaDon_" + maHD + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfService.exportHoaDon(maHD));
    }

}