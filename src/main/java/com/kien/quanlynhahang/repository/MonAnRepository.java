package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MonAnRepository
        extends JpaRepository<MonAn,Integer>,
        JpaSpecificationExecutor<MonAn> {

}