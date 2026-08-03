package com.kien.quanlynhahang.specification;

import com.kien.quanlynhahang.entity.HoaDon;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HoaDonSpecification {

    public static Specification<HoaDon> loc(
            LocalDate tuNgay,
            LocalDate denNgay,
            Integer maKH,
            String trangThai) {

        return Specification
                .where(tuNgay(tuNgay))
                .and(denNgay(denNgay))
                .and(coKhachHang(maKH))
                .and(coTrangThai(trangThai));
    }

    public static Specification<HoaDon> tuNgay(LocalDate tuNgay) {
        return (root, query, cb) -> {
            if (tuNgay == null) {
                return null;
            }

            LocalDateTime startOfDay = tuNgay.atStartOfDay();
            return cb.greaterThanOrEqualTo(root.get("ngayLap"), startOfDay);
        };
    }

    public static Specification<HoaDon> denNgay(LocalDate denNgay) {
        return (root, query, cb) -> {
            if (denNgay == null) {
                return null;
            }

            LocalDateTime nextDay = denNgay.plusDays(1).atStartOfDay();
            return cb.lessThan(root.get("ngayLap"), nextDay);
        };
    }

    public static Specification<HoaDon> coKhachHang(Integer maKH) {
        return (root, query, cb) -> maKH == null
                ? null
                : cb.equal(root.get("khachHang").get("maKH"), maKH);
    }

    public static Specification<HoaDon> coTrangThai(String trangThai) {
        return (root, query, cb) -> {
            if (!hasText(trangThai)) {
                return null;
            }

            return cb.equal(root.get("trangThai"), trangThai);
        };
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
