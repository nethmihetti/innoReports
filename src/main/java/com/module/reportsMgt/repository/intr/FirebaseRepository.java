package com.module.reportsMgt.repository.intr;

import java.io.File;

public interface FirebaseRepository {
    void initFirebase();

    String saveImage(File image, String name);
}
