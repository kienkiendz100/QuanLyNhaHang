package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/khuvuc")
public class KhuVucController {
private final KhuVucRepository repo;

@Operation(summary = "Lấy danh sách khu vực")
@GetMapping
    public ApiResponse<List<KhuVuc>> laytatca(){
    List<KhuVuc> khuVucs = repo.findAll();

    return ApiResponse.<List<KhuVuc>>builder()
            .success(true)
            .message("Lấy danh sách khu vực thành công")
            .data(khuVucs)
            .build();
}
}
