package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.dto.DatBanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.DatBan;
import com.kien.quanlynhahang.service.DatBanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/datban")
public class DatBanController {
    private final DatBanService dbsv;

    @Operation(summary = "Tạo đặt bàn")
    @PostMapping
    public DatBan them(@RequestBody DatBanDTO dto){
        return dbsv.them(dto);
    }

    @Operation(summary = "Lấy danh sách đặt bàn")
    @GetMapping
    public List<DatBan> laytat(){
        return dbsv.laytat();
    }
}
