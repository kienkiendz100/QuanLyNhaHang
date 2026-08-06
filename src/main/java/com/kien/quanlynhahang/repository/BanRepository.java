package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.entity.enums.TrangThaiBan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BanRepository extends JpaRepository <Ban,Integer>{
    List<Ban> findByStatus(TrangThaiBan status);
    List<Ban> findBySucChuaGreaterThanEqual(Integer sucChua);
    List<Ban> findByStatusAndSucChuaGreaterThanEqual(TrangThaiBan status, Integer sucChua);}
