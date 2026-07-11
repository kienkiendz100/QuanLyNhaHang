package com.kien.quanlynhahang.service;

import com.kien.quanlynhahang.entity.KhuVuc;
import com.kien.quanlynhahang.repository.KhuVucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KhuVucService {

    @Autowired
    private KhuVucRepository repository;

    public List<KhuVuc> layTatCa() {
        return repository.findAll();
    }
}
