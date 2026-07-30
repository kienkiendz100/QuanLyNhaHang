package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.service.MonAnService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/monan")
public class MonAnController {
    private final MonAnService monAnService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<MonAn> laytat() {
        return monAnService.layTat();
    }

    @PutMapping("/{id}")
    public MonAn suaMon(
            @PathVariable Integer id,
            @RequestPart("monAn") MonAnDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        return monAnService.capNhat(id, dto, file);

    }
}