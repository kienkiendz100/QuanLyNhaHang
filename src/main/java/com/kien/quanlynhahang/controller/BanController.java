package com.kien.quanlynhahang.controller;

import com.kien.quanlynhahang.dto.CapNhatTrangThaiBanDTO;
import com.kien.quanlynhahang.entity.enums.TrangThaiBan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.kien.quanlynhahang.common.ApiResponse;
import com.kien.quanlynhahang.dto.BanDTO;
import com.kien.quanlynhahang.entity.Ban;
import com.kien.quanlynhahang.repository.BanRepository;
import com.kien.quanlynhahang.service.BanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ban")
public class BanController {
    private final BanRepository banRepository;
    private final BanService banService;

    @Operation(summary = "Lấy danh sách bàn")
    @GetMapping
    public ApiResponse<List<Ban>> laytatca(){
        List<Ban> bans = banRepository.findAll();

        return ApiResponse.<List<Ban>>builder()
                .success(true)
                .message("Lấy danh sách bàn thành công")
                .data(bans)
                .build();
    }

    @Operation(summary = "Thêm bàn mới")
    @PostMapping
    public ApiResponse<Ban> themban(@RequestBody BanDTO dto){
        Ban ban = banService.themban(dto);

        return ApiResponse.<Ban>builder()
                .success(true)
                .message("Thêm bàn thành công")
                .data(ban)
                .build();
    }

    @Operation(summary = "Lấy danh sách bàn theo trạng thái")
    @GetMapping("/trangthai")
    public ApiResponse<List<Ban>> layTheoTrangThai(
            @RequestParam TrangThaiBan status) {

        List<Ban> bans = banService.laybantheotrangthai(status);

        return ApiResponse.<List<Ban>>builder()
                .success(true)
                .message("Lấy danh sách bàn theo trạng thái thành công")
                .data(bans)
                .build();
    }

    @Operation(summary = "Tìm bàn theo sức chứa")
    @GetMapping("/ducho/{songuoi}")
    public ApiResponse<List<Ban>> timbanducho (@PathVariable Integer songuoi){
        List<Ban> bans = banService.timbanducho(songuoi);

        return ApiResponse.<List<Ban>>builder()
                .success(true)
                .message("Tìm bàn theo sức chứa thành công")
                .data(bans)
                .build();
    }

    @Operation(summary = "Tìm bàn trống đủ chỗ")
    @GetMapping("timban")
    public ApiResponse<List<Ban>> timban (@RequestParam Integer songuoi){
        List<Ban> bans = banService.timbantrongducho(songuoi);

        return ApiResponse.<List<Ban>>builder()
                .success(true)
                .message("Tìm bàn trống đủ chỗ thành công")
                .data(bans)
                .build();
    }
    @PutMapping("/{id}/status")
    @Operation(summary = "Cập nhật trạng thái bàn")
    public ApiResponse<BanDTO> capNhatTrangThai(
            @PathVariable Integer id,
            @Valid @RequestBody CapNhatTrangThaiBanDTO request) {

        BanDTO banDTO = banService.capNhatTrangThai(id, request.getStatus());

        return ApiResponse.<BanDTO>builder()
                .message("Cập nhật trạng thái bàn thành công")
                .data(banDTO)
                .build();
    }
}
