package com.kien.quanlynhahang.specification;

import com.kien.quanlynhahang.entity.DatBan;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DatBanSpecificationBuilder {

    public static Specification<DatBan> build(
            LocalDate ngay,
            Integer maKhuVuc,
            Integer maBan,
            String trangThai) {

        return Specification
                .where(DatBanSpecification.coNgay(ngay))
                .and(DatBanSpecification.coKhuVuc(maKhuVuc))
                .and(DatBanSpecification.coBan(maBan))
                .and(DatBanSpecification.coTrangThai(trangThai));
    }
}
