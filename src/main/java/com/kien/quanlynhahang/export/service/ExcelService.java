package com.kien.quanlynhahang.export.service;

import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final HoaDonRepository hoaDonRepository;

    public Workbook taoWorkbook(List<HoaDon> hoaDons) {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("HoaDon");

        taoHeader(workbook, sheet);

        ghiDuLieu(workbook, sheet, hoaDons);

        autoSize(sheet);

        // Freeze Header
        sheet.createFreezePane(0, 1);

        // Auto Filter
        sheet.setAutoFilter(
                new CellRangeAddress(
                        0,
                        0,
                        0,
                        5
                )
        );

        return workbook;
    }

    private void taoHeader(Workbook workbook, Sheet sheet) {

        CellStyle headerStyle = taoHeaderStyle(workbook);

        Row header = sheet.createRow(0);

        taoCell(header, 0, "STT", headerStyle);
        taoCell(header, 1, "Mã HD", headerStyle);
        taoCell(header, 2, "Khách hàng", headerStyle);
        taoCell(header, 3, "Ngày lập", headerStyle);
        taoCell(header, 4, "Tổng tiền", headerStyle);
        taoCell(header, 5, "Trạng thái", headerStyle);
    }

    private void taoCell(Row row,
                         int column,
                         String value,
                         CellStyle style) {

        Cell cell = row.createCell(column);

        cell.setCellValue(value);

        cell.setCellStyle(style);
    }
    private void ghiDuLieu(Workbook workbook,
                           Sheet sheet,
                           List<HoaDon> hoaDons) {

        CellStyle dataStyle = taoDataStyle(workbook);
        CellStyle ngayStyle = taoNgayStyle(workbook);
        CellStyle tienStyle = taoTienStyle(workbook);

        int rowIndex = 1;
        int stt = 1;

        for (HoaDon hoaDon : hoaDons) {

            Row row = sheet.createRow(rowIndex++);

            Cell sttCell = row.createCell(0);
            sttCell.setCellValue(stt++);
            sttCell.setCellStyle(dataStyle);

            Cell maHDCell = row.createCell(1);
            maHDCell.setCellValue(hoaDon.getMaHD());
            maHDCell.setCellStyle(dataStyle);

            Cell khCell = row.createCell(2);
            khCell.setCellValue(hoaDon.getKhachHang().getHoTen());
            khCell.setCellStyle(dataStyle);

            Cell ngayCell = row.createCell(3);
            ngayCell.setCellValue(
                    Timestamp.valueOf(hoaDon.getNgayLap()));
            ngayCell.setCellStyle(ngayStyle);

            Cell tienCell = row.createCell(4);
            tienCell.setCellValue(
                    hoaDon.getTongTien().doubleValue());
            tienCell.setCellStyle(tienStyle);

            Cell ttCell = row.createCell(5);
            ttCell.setCellValue(hoaDon.getTrangThai());
            ttCell.setCellStyle(dataStyle);
        }
    }

    public byte[] exportHoaDon() {

        List<HoaDon> hoaDons = hoaDonRepository.findAll();

        Workbook workbook = taoWorkbook(hoaDons);

        try (ByteArrayOutputStream outputStream =
                     new ByteArrayOutputStream()) {

            workbook.write(outputStream);

            workbook.close();

            return outputStream.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Xuất Excel thất bại", e);
        }
    }

    private void autoSize(Sheet sheet) {

        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }
    }
    private CellStyle taoHeaderStyle(Workbook workbook) {

        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();

        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());

        style.setFont(font);

        style.setFillForegroundColor(
                IndexedColors.DARK_BLUE.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        style.setAlignment(
                HorizontalAlignment.CENTER);

        style.setVerticalAlignment(
                VerticalAlignment.CENTER);

        setBorder(style);

        return style;
    }

    private CellStyle taoDataStyle(Workbook workbook) {

        CellStyle style = workbook.createCellStyle();

        style.setAlignment(
                HorizontalAlignment.CENTER);

        style.setVerticalAlignment(
                VerticalAlignment.CENTER);

        setBorder(style);

        return style;
    }

    private CellStyle taoTienStyle(Workbook workbook) {

        CellStyle style = workbook.createCellStyle();

        DataFormat format = workbook.createDataFormat();

        style.setDataFormat(
                format.getFormat("#,##0 ₫"));

        style.setAlignment(
                HorizontalAlignment.RIGHT);

        style.setVerticalAlignment(
                VerticalAlignment.CENTER);

        setBorder(style);

        return style;
    }

    private CellStyle taoNgayStyle(Workbook workbook) {

        CellStyle style = workbook.createCellStyle();

        DataFormat format = workbook.createDataFormat();

        style.setDataFormat(
                format.getFormat("dd/MM/yyyy HH:mm"));

        style.setAlignment(
                HorizontalAlignment.CENTER);

        style.setVerticalAlignment(
                VerticalAlignment.CENTER);

        setBorder(style);

        return style;
    }

    private void setBorder(CellStyle style) {

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}