package com.kien.quanlynhahang.repository;
import com.kien.quanlynhahang.dashboard.dto.TopMonDTO;
import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.id.ChiTietHoaDonId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.kien.quanlynhahang.entity.HoaDon;
@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, ChiTietHoaDonId> {
    List<ChiTietHoaDon> findByHoaDon(HoaDon hoaDon);


    }
