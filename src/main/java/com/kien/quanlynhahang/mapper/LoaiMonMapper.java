package com.kien.quanlynhahang.mapper;

import com.kien.quanlynhahang.dto.LoaiMonDTO;
import com.kien.quanlynhahang.entity.LoaiMon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoaiMonMapper {

    @Mapping(target = "maLoai", ignore = true)
    LoaiMon toEntity(LoaiMonDTO dto);

    @Mapping(target = "maLoai", ignore = true)
    void updateEntity(LoaiMonDTO dto, @MappingTarget LoaiMon loaiMon);
}
