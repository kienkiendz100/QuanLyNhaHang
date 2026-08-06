package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.MonAn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn,Integer>, JpaSpecificationExecutor<MonAn> {

}