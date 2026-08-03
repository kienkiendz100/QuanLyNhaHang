package com.kien.quanlynhahang.mapper;

import com.kien.quanlynhahang.dto.MonAnDTO;
import com.kien.quanlynhahang.entity.MonAn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MonAnMapper {

    @Mapping(target = "maMon", ignore = true)
    @Mapping(target = "trangThai", ignore = true)
    @Mapping(target = "anh", ignore = true)
    @Mapping(target = "loaiMon", ignore = true)
    MonAn toEntity(MonAnDTO dto);

    @Mapping(target = "maMon", ignore = true)
    @Mapping(target = "trangThai", ignore = true)
    @Mapping(target = "anh", ignore = true)
    @Mapping(target = "loaiMon", ignore = true)
    void updateEntity(MonAnDTO dto, @MappingTarget MonAn monAn);
}
