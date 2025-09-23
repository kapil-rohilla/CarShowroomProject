package com.car_showroom_project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void initialize() {
        if (initialized) return; // already initialized

        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/car-showroom-14428-firebase-adminsdk-fbsvc-4104b9025c.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase initialized successfully.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to initialize Firebase.");
        }
    }

    public static Firestore getFirestore() {
        if (!initialized) initialize(); // ensure Firebase is initialized
        return FirestoreClient.getFirestore();
    }
}