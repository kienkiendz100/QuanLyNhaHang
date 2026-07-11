package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.DatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatBanRepository extends JpaRepository<DatBan,Integer> {

}