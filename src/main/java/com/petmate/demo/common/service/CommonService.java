package com.petmate.demo.common.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface CommonService {
    String uploadFile(MultipartFile[] files) throws IOException;
}
