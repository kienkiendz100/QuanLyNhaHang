package com.kien.quanlynhahang.dashboard.repository;

import com.kien.quanlynhahang.dashboard.dto.DoanhThuNgayDTO;
import com.kien.quanlynhahang.dashboard.dto.DoanhThuThangDTO;
import com.kien.quanlynhahang.dashboard.dto.TopKhachHangDTO;
import com.kien.quanlynhahang.dashboard.dto.TopMonDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface DashboardRepository {

    BigDecimal tongDoanhThu(LocalDateTime tuNgay,
                            LocalDateTime denNgay);

    Long tongHoaDon(LocalDateTime tuNgay,
                    LocalDateTime denNgay);

    Long tongKhachHang(LocalDateTime tuNgay,
                       LocalDateTime denNgay);

    List<DoanhThuThangDTO> doanhThuTheoThang(Integer nam);

    List<DoanhThuNgayDTO> doanhThuTheoNgay(LocalDateTime tuNgay,
                                           LocalDateTime denNgay);

    List<TopMonDTO> topMonBanChay(int limit);

    List<TopKhachHangDTO> topKhachHang(int limit);
}