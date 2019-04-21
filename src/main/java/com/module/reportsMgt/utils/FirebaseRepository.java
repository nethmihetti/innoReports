package com.module.reportsMgt.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FirebaseRepository {

    @PostConstruct
    public void initFirebase() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://innoreport-66483.firebaseio.com")
                .setStorageBucket("innoreport-66483.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }


//    public void saveFile(String file) throws IOException {
//        File file1 = new File("Jazz.jpg");
//        InputStream content = new FileInputStream(file1);
//
//        String blobName = "Jazz6.jpg";
//
//        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference();
//
//        String jazz = "Jazz01";
//
//        Bucket bucket = StorageClient.getInstance().bucket();
//        Blob blob = bucket.create(blobName, content);
//        System.out.println(blob.getMediaLink());
//
//        System.out.println(blob.getSelfLink());
//        dRef.child("report-images").child("fileid06").setValue(blob.getMediaLink(), (error, ref) -> {
//            System.out.println(ref);
//        });
//
//        System.out.println(blob);
//    }
    public void saveFile(String file) throws IOException {
        File file1 = new File("Jazz.jpg");
        InputStream content = new FileInputStream(file1);

        String blobName = "Jazz7.jpg";


        DatabaseReference dRef = FirebaseDatabase.getInstance().getReference();

        String jazz = "Jazz01";

        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(blobName, content);
        System.out.println(blob.getMediaLink());
        System.out.println(blob.getSelfLink());

        System.out.println(blob.getSelfLink());
        dRef.child("report-images").child("fileid07").setValue(blob.getSelfLink(), (error, ref) -> {
            System.out.println(ref);
        });

        System.out.println(blob);
    }

}
