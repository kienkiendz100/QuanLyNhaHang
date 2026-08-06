package com.kien.quanlynhahang.repository;

import com.kien.quanlynhahang.baocao.dto.BaoCaoDoanhThuDTO;
import com.kien.quanlynhahang.entity.HoaDon;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer>, JpaSpecificationExecutor<HoaDon> {

    List<HoaDon> findByNgayLapBetween(LocalDateTime from, LocalDateTime to);

    List<HoaDon> findByTrangThai(String trangThai);

    List<HoaDon> findByKhachHang_MaKH(Integer maKH);




    @Query("""
SELECT COUNT(h) AS tongHoaDon,
       COALESCE(SUM(h.tongTien),0) AS tongDoanhThu,
       COALESCE(AVG(h.tongTien),0) AS trungBinhHoaDon
FROM HoaDon h
""")
    Tuple layBaoCaoDoanhThu();
}