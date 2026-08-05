package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository
        extends JpaRepository<Otp,Integer> {

    Optional<Otp> findTopByEmailOrderByIdDesc(String email);

}