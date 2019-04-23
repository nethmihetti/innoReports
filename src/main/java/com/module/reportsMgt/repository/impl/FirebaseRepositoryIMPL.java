package com.module.reportsMgt.repository.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.module.reportsMgt.repository.intr.FirebaseRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.*;

@Repository
public class FirebaseRepositoryIMPL implements FirebaseRepository {
    @PostConstruct
    @Override
    public void initFirebase() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("ServiceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://innoreport-66483.firebaseio.com")
                    .setStorageBucket("innoreport-66483.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String saveImage(File image, String name) {
        try {
            InputStream content = new FileInputStream(image);
            Bucket bucket = StorageClient.getInstance().bucket();
            Blob blob = bucket.create(name, content);

            return blob.getMediaLink();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
