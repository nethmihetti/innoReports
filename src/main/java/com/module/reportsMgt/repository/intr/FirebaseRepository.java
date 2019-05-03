package com.module.reportsMgt.repository.intr;

public interface FirebaseRepository {
    void initFirebase();

    String saveImage(byte[] image, String name);
}
