package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.dto.DatBanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.DatBan;
import com.kien.quanlynhahang.service.DatBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datban")
public class DatBanController {
    @Autowired
    public DatBanService dbsv;

    @PostMapping
    public DatBan them(@RequestBody DatBanDTO dto){
        return dbsv.them(dto);
    }

    @GetMapping
    public List<DatBan> laytat(){
        return dbsv.laytat();
    }
}
