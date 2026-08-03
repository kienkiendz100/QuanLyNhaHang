package com.kien.quanlynhahang.mapper;

import com.kien.quanlynhahang.dto.HoaDonDTO;
import com.kien.quanlynhahang.entity.HoaDon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HoaDonMapper {

    @Mapping(target = "maHD", ignore = true)
    @Mapping(target = "ngayLap", ignore = true)
    @Mapping(target = "tongTien", ignore = true)
    @Mapping(target = "trangThai", ignore = true)
    @Mapping(target = "khachHang", ignore = true)
    HoaDon toEntity(HoaDonDTO dto);
}
