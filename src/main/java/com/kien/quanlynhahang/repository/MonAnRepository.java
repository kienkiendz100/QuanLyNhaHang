package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MonAnRepository extends JpaRepository<MonAn,Integer>,
        JpaSpecificationExecutor<MonAn> {
}
