package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.Ban;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  BanRepository extends JpaRepository <Ban,Integer>{
    List<Ban> findByTrangThai(String trangThai);
    List<Ban> findBySucChuaGreaterThanEqual(Integer sucChua);
    List<Ban> findByTrangThaiAndSucChuaGreaterThanEqual(String trangThai,Integer sucChua);
}
