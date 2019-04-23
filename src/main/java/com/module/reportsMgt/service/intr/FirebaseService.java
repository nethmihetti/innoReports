package com.module.reportsMgt.service.intr;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FirebaseService {
    String saveImage(File file);
}
