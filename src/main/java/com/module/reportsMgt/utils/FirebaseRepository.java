package com.module.reportsMgt.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FirebaseRepository {

    public void saveFile(String file) throws IOException {
        File file1 = new File("Jazz.jpg");
        InputStream content = new FileInputStream(file1);

        String blobName = "Jazz.jpg";

        FileInputStream serviceAccount =
                new FileInputStream("ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://innoreport-66483.firebaseio.com")
                .setStorageBucket("innoreport-66483.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);

        Bucket bucket = StorageClient.getInstance().bucket();

        Blob blob = bucket.create(blobName, content);

        System.out.println(blob);
    }

}
