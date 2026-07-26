package com.kien.quanlynhahang.service;

import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class KhuVucService {
    private final KhuVucRepository repository;

    public List<KhuVuc> layTatCa() {
        return repository.findAll();
    }
}
