package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.BanRepository;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import com.kien.quanlynhahang.service.BanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ban")
public class BanController {
    @Autowired
    private BanRepository banrp;
    @Autowired
    private BanService bansv;

    @GetMapping
    public List<Ban> laytatca(){
        return banrp.findAll();
    }

    @PostMapping
    public Ban themban(@RequestBody BanDTO dto){
        return bansv.themban(dto);
    }

    @GetMapping("/trangthai/{trangThai}")
    public List<Ban> laybantheotrangthai(@PathVariable String trangThai) {
        return bansv.laybantheotrangthai(trangThai);
    }

    @GetMapping("/ducho/{songuoi}")
    public List<Ban> timbanducho (@PathVariable Integer songuoi){
        return bansv.timbanducho(songuoi);
    }

    @GetMapping("timban")
    public List<Ban> timban (@RequestParam Integer songuoi){
        return bansv.timbantrongducho(songuoi);
    }
}
