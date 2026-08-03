package com.kien.quanlynhahang.specification;

import com.kien.quanlynhahang.entity.HoaDon;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class HoaDonSpecificationBuilder {

    public static Specification<HoaDon> build(
            LocalDate tuNgay,
            LocalDate denNgay,
            Integer maKH,
            String trangThai) {

        return Specification
                .where(HoaDonSpecification.tuNgay(tuNgay))
                .and(HoaDonSpecification.denNgay(denNgay))
                .and(HoaDonSpecification.coKhachHang(maKH))
                .and(HoaDonSpecification.coTrangThai(trangThai));
    }
}
