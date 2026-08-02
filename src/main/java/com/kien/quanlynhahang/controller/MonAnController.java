package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.service.MonAnService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/monan")
public class MonAnController {

    private final MonAnService monAnService;

    @Operation(summary = "Lấy danh sách món ăn")
    @GetMapping
    public List<MonAn> layTat() {
        return monAnService.layTat();
    }

    @Operation(summary = "Lấy món ăn theo mã")
    @GetMapping("/{id}")
    public MonAn layTheoMa(@PathVariable Integer id) {
        return monAnService.layTheoMa(id);
    }

    @Operation(summary = "Thêm món ăn")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MonAn themMon(
            @Valid @RequestPart("monAn") MonAnDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return monAnService.themMon(dto, file);
    }

    @Operation(summary = "Cập nhật món ăn")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MonAn capNhat(
            @PathVariable Integer id,
            @Valid @RequestPart("monAn") MonAnDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return monAnService.capNhat(id, dto, file);
    }

    @Operation(summary = "Xóa món ăn")
    @DeleteMapping("/{id}")
    public void xoa(@PathVariable Integer id) {
        monAnService.xoa(id);
    }

}