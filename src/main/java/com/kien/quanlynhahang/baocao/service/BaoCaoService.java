package com.kien.quanlynhahang.baocao.service;

import com.kien.quanlynhahang.baocao.dto.BaoCaoDoanhThuDTO;

public interface BaoCaoService {

    BaoCaoDoanhThuDTO layBaoCaoDoanhThu();

    byte[] xuatPDF();

    byte[] xuatExcel();
}