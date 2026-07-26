package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.MonAn;
import com.kien.quanlynhahang.service.KhachHangService;
import com.kien.quanlynhahang.service.MonAnService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/monan")
public class MonAnController {
    private final MonAnService mas;

    @GetMapping
    public List<MonAn> laytat(){
        return mas.laytat();
    }

    @PostMapping
    public MonAn them ( @Valid @RequestBody MonAnDTO dto ){
        return mas.them(dto);
    }
}
