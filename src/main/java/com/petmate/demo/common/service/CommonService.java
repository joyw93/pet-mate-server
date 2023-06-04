package com.petmate.demo.common.service;

import com.petmate.demo.community.model.CommunityPostImage;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.File;
import java.io.IOException;

public interface CommonService {
    List<String> uploadFile(MultipartFile[] files) throws IOException;
}
