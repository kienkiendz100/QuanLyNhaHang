package com.kien.quanlynhahang.controller;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/khuvuc")
public class KhuVucController {
private final KhuVucRepository repo;

@GetMapping
    public List<KhuVuc> laytatca(){
    return repo.findAll();
}
}