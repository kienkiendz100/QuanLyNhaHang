    package com.kien.quanlynhahang.dashboard.service;

    import com.kien.quanlynhahang.dashboard.dto.*;
    import com.kien.quanlynhahang.dashboard.repository.DashboardRepository;
    import com.kien.quanlynhahang.repository.KhachHangRepository;
    import com.kien.quanlynhahang.repository.MonAnRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.math.BigDecimal;
    import java.time.LocalDate;
    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class DashboardService {

        private final DashboardRepository dashboardRepository;
        private final MonAnRepository monAnRepository;
        private final KhachHangRepository khachHangRepository;

        @Transactional(readOnly = true)
        public DashboardDTO dashboard(LocalDate tuNgay,
                                      LocalDate denNgay) {

            BigDecimal tongDoanhThu =
                    dashboardRepository.tongDoanhThu(
                            tuNgay.atStartOfDay(),
                            denNgay.atTime(23, 59, 59));

            Long tongHoaDon =
                    dashboardRepository.tongHoaDon(
                            tuNgay.atStartOfDay(),
                            denNgay.atTime(23, 59, 59));

            Long tongMonAn =
                    monAnRepository.count();

            Long tongKhachHang =
                    khachHangRepository.count();

            List<TopMonDTO> topMon =
                    dashboardRepository.topMonBanChay(5);

            List<TopKhachHangDTO> topKhachHang =
                    dashboardRepository.topKhachHang(5);

            List<DoanhThuThangDTO> doanhThuThang =
                    dashboardRepository.doanhThuTheoThang(
                            tuNgay.getYear());

            return new DashboardDTO(
                    tongDoanhThu,
                    tongHoaDon,
                    tongMonAn,
                    tongKhachHang,
                    topMon,
                    topKhachHang,
                    doanhThuThang
            );
        }

        @Transactional(readOnly = true)
        public DoanhThuDTO revenue(LocalDate tuNgay,
                                   LocalDate denNgay) {

            return DoanhThuDTO.builder()
                    .tongDoanhThu(
                            dashboardRepository.tongDoanhThu(
                                    tuNgay.atStartOfDay(),
                                    denNgay.atTime(23,59,59)))
                    .tongHoaDon(
                            dashboardRepository.tongHoaDon(
                                    tuNgay.atStartOfDay(),
                                    denNgay.atTime(23,59,59)))
                    .build();
        }

        @Transactional(readOnly = true)
        public List<TopMonDTO> topSelling() {
            return dashboardRepository.topMonBanChay(5);
        }

        @Transactional(readOnly = true)
        public List<DoanhThuThangDTO> monthly(Integer nam) {
            return dashboardRepository.doanhThuTheoThang(nam);
        }

        @Transactional(readOnly = true)
        public ThongKeKhachHangDTO customer() {

            return ThongKeKhachHangDTO.builder()
                    .tongKhachHang(khachHangRepository.count())
                    .topKhachHang(dashboardRepository.topKhachHang(5))
                    .build();
        }
    }