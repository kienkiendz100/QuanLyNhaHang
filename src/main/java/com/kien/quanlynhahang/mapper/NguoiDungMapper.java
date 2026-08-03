package com.kien.quanlynhahang.mapper;

import com.kien.quanlynhahang.dto.NguoiDungDTO;
import com.kien.quanlynhahang.entity.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {

    @Mapping(target = "maND", ignore = true)
    @Mapping(target = "matKhau", ignore = true)
    NguoiDung toEntity(NguoiDungDTO dto);

    @Mapping(target = "maND", ignore = true)
    @Mapping(target = "matKhau", ignore = true)
    void updateEntity(NguoiDungDTO dto, @MappingTarget NguoiDung nguoiDung);
}
