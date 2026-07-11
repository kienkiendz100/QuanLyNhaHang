package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich,Integer> {
}