package com.kien.quanlynhahang.dashboard.query;

public final class DashboardQuery {

    private DashboardQuery() {
    }

    /**
     * Tổng doanh thu
     */
    public static final String TONG_DOANH_THU = """
        SELECT COALESCE(SUM(h.tongTien),0)
        FROM HoaDon h
        WHERE h.trangThai = 'Đã thanh toán'
          AND h.ngayLap BETWEEN :tuNgay AND :denNgay
        """;

    /**
     * Tổng hóa đơn
     */
    public static final String TONG_HOA_DON = """
        SELECT COUNT(h)
        FROM HoaDon h
        WHERE h.trangThai = 'Đã thanh toán'
          AND h.ngayLap BETWEEN :tuNgay AND :denNgay
        """;

    /**
     * Tổng khách hàng
     */
    public static final String TONG_KHACH_HANG = """
        SELECT COUNT(DISTINCT h.khachHang.maKH)
        FROM HoaDon h
        WHERE h.trangThai = 'Đã thanh toán'
          AND h.ngayLap BETWEEN :tuNgay AND :denNgay
        """;

    /**
     * Doanh thu theo tháng
     */
    public static final String DOANH_THU_THEO_THANG = """
        SELECT new com.kien.quanlynhahang.dashboard.dto.DoanhThuThangDTO(
            MONTH(h.ngayLap),
            COALESCE(SUM(h.tongTien),0)
        )
        FROM HoaDon h
        WHERE YEAR(h.ngayLap) = :nam
          AND h.trangThai = 'Đã thanh toán'
        GROUP BY MONTH(h.ngayLap)
        ORDER BY MONTH(h.ngayLap)
        """;

    /**
     * Top món bán chạy
     */
    public static final String TOP_MON_BAN_CHAY = """
        SELECT new com.kien.quanlynhahang.dashboard.dto.TopMonDTO(
            c.monAn.maMon,
            c.monAn.tenMon,
            SUM(c.soLuong),
            SUM(c.thanhTien)
        )
        FROM ChiTietHoaDon c
        JOIN c.hoaDon h
        WHERE h.trangThai = 'Đã thanh toán'
        GROUP BY
            c.monAn.maMon,
            c.monAn.tenMon
        ORDER BY SUM(c.soLuong) DESC
        """;

    /**
     * Top khách hàng
     */
    public static final String TOP_KHACH_HANG = """
        SELECT new com.kien.quanlynhahang.dashboard.dto.TopKhachHangDTO(
            h.khachHang.maKH,
            h.khachHang.hoTen,
            COUNT(h),
            COALESCE(SUM(h.tongTien),0)
        )
        FROM HoaDon h
        WHERE h.trangThai = 'Đã thanh toán'
        GROUP BY
            h.khachHang.maKH,
            h.khachHang.hoTen
        ORDER BY SUM(h.tongTien) DESC
        """;

    /**
     * Doanh thu theo ngày (SQL Server)
     */
    public static final String DOANH_THU_THEO_NGAY = """
        SELECT
            CAST(NgayLap AS DATE) AS ngay,
            SUM(TongTien) AS doanhThu
        FROM HoaDon
        WHERE TrangThai = N'Đã thanh toán'
          AND NgayLap BETWEEN :tuNgay AND :denNgay
        GROUP BY CAST(NgayLap AS DATE)
        ORDER BY ngay
        """;
}