package com.kien.quanlynhahang.specification;

import com.kien.quanlynhahang.entity.ChiTietDatBan;
import com.kien.quanlynhahang.entity.DatBan;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class DatBanSpecification {

    public static Specification<DatBan> loc(
            LocalDate ngay,
            Integer maKhuVuc,
            Integer maBan,
            String trangThai) {

        return Specification
                .where(coNgay(ngay))
                .and(coKhuVuc(maKhuVuc))
                .and(coBan(maBan))
                .and(coTrangThai(trangThai));
    }

    public static Specification<DatBan> coNgay(LocalDate ngay) {
        return (root, query, cb) -> ngay == null
                ? null
                : cb.equal(root.get("ngayDat"), ngay);
    }

    public static Specification<DatBan> coKhuVuc(Integer maKhuVuc) {
        return (root, query, cb) -> {
            if (maKhuVuc == null) {
                return null;
            }

            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<ChiTietDatBan> chiTiet = subquery.from(ChiTietDatBan.class);

            subquery.select(chiTiet.get("datBan").get("maDatBan"))
                    .where(cb.equal(
                            chiTiet.get("ban").get("khuVuc").get("maKhuVuc"),
                            maKhuVuc));

            return root.get("maDatBan").in(subquery);
        };
    }

    public static Specification<DatBan> coBan(Integer maBan) {
        return (root, query, cb) -> {
            if (maBan == null) {
                return null;
            }

            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<ChiTietDatBan> chiTiet = subquery.from(ChiTietDatBan.class);

            subquery.select(chiTiet.get("datBan").get("maDatBan"))
                    .where(cb.equal(chiTiet.get("ban").get("maBan"), maBan));

            return root.get("maDatBan").in(subquery);
        };
    }

    public static Specification<DatBan> coTrangThai(String trangThai) {
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
