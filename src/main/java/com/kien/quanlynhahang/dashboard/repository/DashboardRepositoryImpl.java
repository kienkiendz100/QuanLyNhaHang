package com.kien.quanlynhahang.dashboard.repository;

import com.kien.quanlynhahang.dashboard.dto.DoanhThuNgayDTO;
import com.kien.quanlynhahang.dashboard.dto.DoanhThuThangDTO;
import com.kien.quanlynhahang.dashboard.dto.TopKhachHangDTO;
import com.kien.quanlynhahang.dashboard.dto.TopMonDTO;
import com.kien.quanlynhahang.dashboard.query.DashboardQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DashboardRepositoryImpl implements DashboardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BigDecimal tongDoanhThu(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return entityManager.createQuery(DashboardQuery.TONG_DOANH_THU, BigDecimal.class)
                .setParameter("tuNgay", tuNgay)
                .setParameter("denNgay", denNgay)
                .getSingleResult();
    }

    @Override
    public Long tongHoaDon(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return entityManager.createQuery(DashboardQuery.TONG_HOA_DON, Long.class)
                .setParameter("tuNgay", tuNgay)
                .setParameter("denNgay", denNgay)
                .getSingleResult();
    }

    @Override
    public Long tongKhachHang(LocalDateTime tuNgay, LocalDateTime denNgay) {
        return entityManager.createQuery(DashboardQuery.TONG_KHACH_HANG, Long.class)
                .setParameter("tuNgay", tuNgay)
                .setParameter("denNgay", denNgay)
                .getSingleResult();
    }

    @Override
    public List<DoanhThuThangDTO> doanhThuTheoThang(Integer nam) {
        return entityManager.createQuery(
                        DashboardQuery.DOANH_THU_THEO_THANG,
                        DoanhThuThangDTO.class)
                .setParameter("nam", nam)
                .getResultList();
    }

    @Override
    public List<DoanhThuNgayDTO> doanhThuTheoNgay(LocalDateTime tuNgay, LocalDateTime denNgay) {

        @SuppressWarnings("unchecked")
        List<Object[]> result = entityManager
                .createNativeQuery(DashboardQuery.DOANH_THU_THEO_NGAY)
                .setParameter("tuNgay", tuNgay)
                .setParameter("denNgay", denNgay)
                .getResultList();

        return result.stream()
                .map(row -> new DoanhThuNgayDTO(
                        ((Date) row[0]).toLocalDate(),
                        (BigDecimal) row[1]
                ))
                .toList();
    }

    @Override
    public List<TopMonDTO> topMonBanChay(int limit) {
        return entityManager.createQuery(
                        DashboardQuery.TOP_MON_BAN_CHAY,
                        TopMonDTO.class)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<TopKhachHangDTO> topKhachHang(int limit) {
        return entityManager.createQuery(
                        DashboardQuery.TOP_KHACH_HANG,
                        TopKhachHangDTO.class)
                .setMaxResults(limit)
                .getResultList();
    }
}