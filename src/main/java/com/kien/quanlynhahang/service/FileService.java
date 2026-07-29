package com.kien.quanlynhahang.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file);

    void delete(String fileName);

}