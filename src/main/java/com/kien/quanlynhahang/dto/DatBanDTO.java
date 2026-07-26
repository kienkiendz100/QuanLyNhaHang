package com.kien.quanlynhahang.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class DatBanDTO {
    private Integer maKH;
    private LocalDate ngayDat;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;
    private Integer soNguoi;

}
