package com.module.reportsMgt.service.impl;

import com.module.reportsMgt.repository.intr.FirebaseRepository;
import com.module.reportsMgt.service.intr.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
public class FirebaseServiceIMPL implements FirebaseService {

    @Autowired
    FirebaseRepository firebaseRepository;

    public String saveImage(MultipartFile file) {
        File mFile = convert(file);
        return saveImage(mFile);
    }

    public String saveImage(File file) {
        String filename = generateRandomFileName() + getFileExtension(file.getName());

        return firebaseRepository.saveImage(file, filename);
    }

    public static String getFileExtension(final String path) {
        if (path != null && path.lastIndexOf('.') != -1) {
            return path.substring(path.lastIndexOf('.'));
        }
        return null;
    }

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    public static String generateRandomFileName() {
        String POSSIBLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 13) { // length of the random string.
            int index = (int) (rnd.nextFloat() * POSSIBLE.length());
            salt.append(POSSIBLE.charAt(index));
        }
        String result = salt.toString();
        return result;


    }

    public static File convert(MultipartFile file) {
        try {
            File convFile = new File(file.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
            return convFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
