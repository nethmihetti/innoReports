package com.module.reportsMgt.service.intr;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseService {
    String saveImage(MultipartFile image);
}
