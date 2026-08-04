package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.KhachHang;
import com.kien.quanlynhahang.entity.KhuVuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

}
