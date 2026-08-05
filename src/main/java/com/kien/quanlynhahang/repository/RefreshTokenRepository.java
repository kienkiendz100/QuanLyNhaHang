package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken,Integer> {

    Optional<RefreshToken> findByToken(String token);

}