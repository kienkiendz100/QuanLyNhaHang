package com.kien.quanlynhahang.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeKhachHangDTO {

    private Long tongKhachHang;

    private List<TopKhachHangDTO> topKhachHang;

}