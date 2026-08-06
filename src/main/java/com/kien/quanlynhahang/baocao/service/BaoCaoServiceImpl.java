package com.kien.quanlynhahang.baocao.service;

import com.kien.quanlynhahang.baocao.dto.BaoCaoDoanhThuDTO;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import com.itextpdf.layout.element.Paragraph;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BaoCaoServiceImpl implements BaoCaoService {

    private final HoaDonRepository hoaDonRepository;

    @Override
    public BaoCaoDoanhThuDTO layBaoCaoDoanhThu() {

        Tuple t = hoaDonRepository.layBaoCaoDoanhThu();

        return BaoCaoDoanhThuDTO.builder()
                .tongHoaDon(((Number) t.get("tongHoaDon")).longValue())
                .tongDoanhThu((BigDecimal) t.get("tongDoanhThu"))
                .trungBinhHoaDon(
                        BigDecimal.valueOf(((Number) t.get("trungBinhHoaDon")).doubleValue())
                )
                .build();
    }
    @Override
    public byte[] xuatPDF() {

        BaoCaoDoanhThuDTO dto = layBaoCaoDoanhThu();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat("#,###", symbols);

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("========== BAO CAO DOANH THU =========="));
            document.add(new Paragraph(""));

            document.add(new Paragraph("Tong hoa don: " + dto.getTongHoaDon()));

            document.add(new Paragraph(
                    "Tong doanh thu: " + df.format(dto.getTongDoanhThu()) + " VND"));

            document.add(new Paragraph(
                    "Trung binh hoa don: " + df.format(dto.getTrungBinhHoaDon()) + " VND"));
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Khong the xuat PDF", e);
        }
    }

    @Override
    public byte[] xuatExcel() {

        BaoCaoDoanhThuDTO dto = layBaoCaoDoanhThu();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("vi", "VN"));
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,###", symbols);

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Bao Cao Doanh Thu");

            // Tiêu đề
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Dòng tiêu đề
            Row header = sheet.createRow(0);

            Cell c0 = header.createCell(0);
            c0.setCellValue("Tổng hóa đơn");
            c0.setCellStyle(headerStyle);

            Cell c1 = header.createCell(1);
            c1.setCellValue("Tổng doanh thu");
            c1.setCellStyle(headerStyle);

            Cell c2 = header.createCell(2);
            c2.setCellValue("Trung bình hóa đơn");
            c2.setCellStyle(headerStyle);

            // Dữ liệu
            Row row = sheet.createRow(1);

            row.createCell(0).setCellValue(dto.getTongHoaDon());

            row.createCell(1)
                    .setCellValue(df.format(dto.getTongDoanhThu()) + " VND");

            row.createCell(2)
                    .setCellValue(df.format(dto.getTrungBinhHoaDon()) + " VND");

            // Tự động căn độ rộng cột
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            workbook.write(out);

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Không thể xuất Excel", e);
        }
    }
}