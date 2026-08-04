package com.kien.quanlynhahang.export.service;

import com.kien.quanlynhahang.entity.ChiTietHoaDon;
import com.kien.quanlynhahang.entity.HoaDon;
import com.kien.quanlynhahang.exception.KhongTimThayException;
import com.kien.quanlynhahang.repository.ChiTietHoaDonRepository;
import com.kien.quanlynhahang.repository.HoaDonRepository;
import com.lowagie.text.pdf.BaseFont;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final HoaDonRepository hoaDonRepository;

    private final ChiTietHoaDonRepository chiTietHoaDonRepository;
    public byte[] exportHoaDon(Integer maHD) {

        HoaDon hoaDon = hoaDonRepository.findById(maHD)
                .orElseThrow(() ->
                        new KhongTimThayException("Không tìm thấy hóa đơn"));

        List<ChiTietHoaDon> chiTietHoaDons =
                chiTietHoaDonRepository.findByHoaDon(hoaDon);

        try {
            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            Document document =
                    new Document(PageSize.A4);

            PdfWriter.getInstance(
                    document,
                    outputStream);

            document.open();

            // ===================== Tiêu đề =====================
            Font titleFont = taoFont(18, Font.BOLD);

            Paragraph title = new Paragraph(
                    "HÓA ĐƠN THANH TOÁN",
                    titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);
            document.add(new Paragraph(" "));

            // ===================== Thông tin hóa đơn =====================
            Font normalFont = taoFont(12, Font.NORMAL);

            document.add(new Paragraph(
                    "Mã hóa đơn: " + hoaDon.getMaHD(),
                    normalFont));

            document.add(new Paragraph(
                    "Khách hàng: " +
                            hoaDon.getKhachHang().getHoTen(),
                    normalFont));

            document.add(new Paragraph(
                    "Ngày lập: " +
                            hoaDon.getNgayLap(),
                    normalFont));

            document.add(new Paragraph(" "));

            // ===================== Bảng =====================
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);

            table.setWidths(new float[]{4, 1, 2, 2});

            table.addCell(new PdfPCell(
                    new Phrase("Tên món", normalFont)));

            table.addCell(new PdfPCell(
                    new Phrase("SL", normalFont)));

            table.addCell(new PdfPCell(
                    new Phrase("Đơn giá", normalFont)));

            table.addCell(new PdfPCell(
                    new Phrase("Thành tiền", normalFont)));

            // ===================== Dữ liệu =====================
            for (ChiTietHoaDon ct : chiTietHoaDons) {

                table.addCell(new Phrase(
                        ct.getMonAn().getTenMon(),
                        normalFont));

                table.addCell(new Phrase(
                        String.valueOf(ct.getSoLuong()),
                        normalFont));

                table.addCell(new Phrase(
                        ct.getDonGia().toString(),
                        normalFont));

                table.addCell(new Phrase(
                        ct.getThanhTien().toString(),
                        normalFont));
            }

            document.add(table);

            document.add(new Paragraph(" "));

            // ===================== Tổng tiền =====================
            Font totalFont = taoFont(14, Font.BOLD);

            document.add(new Paragraph(
                    "Tổng tiền: " + hoaDon.getTongTien(),
                    totalFont));

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Lỗi xuất PDF", e);
        }
    }
    private Font taoFont(float size, int style) {

        try {

            BaseFont baseFont = BaseFont.createFont(
                    "C:/Windows/Fonts/arial.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);

            return new Font(baseFont, size, style);

        } catch (Exception e) {

            throw new RuntimeException("Không tạo được font PDF", e);

        }

    }
}