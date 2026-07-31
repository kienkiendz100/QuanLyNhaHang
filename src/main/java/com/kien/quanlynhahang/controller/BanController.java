package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.BanRepository;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import com.kien.quanlynhahang.service.BanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ban")
public class BanController {
    private final BanRepository banrp;
    private final BanService bansv;

    @Operation(summary = "Lấy danh sách bàn")
    @GetMapping
    public List<Ban> laytatca(){
        return banrp.findAll();
    }

    @Operation(summary = "Thêm bàn mới")
    @PostMapping
    public Ban themban(@RequestBody BanDTO dto){
        return bansv.themban(dto);
    }

    @Operation(summary = "Lấy danh sách bàn theo trạng thái")
    @GetMapping("/trangthai/{trangThai}")
    public List<Ban> laybantheotrangthai(@PathVariable String trangThai) {
        return bansv.laybantheotrangthai(trangThai);
    }

    @Operation(summary = "Tìm bàn theo sức chứa")
    @GetMapping("/ducho/{songuoi}")
    public List<Ban> timbanducho (@PathVariable Integer songuoi){
        return bansv.timbanducho(songuoi);
    }

    @Operation(summary = "Tìm bàn trống đủ chỗ")
    @GetMapping("timban")
    public List<Ban> timban (@RequestParam Integer songuoi){
        return bansv.timbantrongducho(songuoi);
    }
}
