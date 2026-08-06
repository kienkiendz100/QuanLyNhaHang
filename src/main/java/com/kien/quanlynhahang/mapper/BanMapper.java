package com.kien.quanlynhahang.mapper;

import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BanMapper {

    BanDTO toDTO(Ban ban);

    Ban toEntity(BanDTO dto);
}