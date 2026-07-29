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
    private final MonAnService mas;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<MonAn> laytat(){
        return mas.layTat();
    }

    @PostMapping(value = "/them", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MonAn themMon(@RequestPart("monAn") String monAn,
                         @RequestPart("file") MultipartFile file) throws IOException {

        MonAnDTO dto = objectMapper.readValue(monAn, MonAnDTO.class);
        return mas.themMon(dto, file);
    }}
