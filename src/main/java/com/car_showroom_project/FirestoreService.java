package com.car_showroom_project;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirestoreService {

    // Add Showroom
    public static String addShowroom(Showroom showroom) throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("showroom_name", showroom.showroom_name);
        data.put("showroom_address", showroom.showroom_address);
        data.put("manager_name", showroom.manager_name);
        data.put("total_employees", showroom.total_employees);
        data.put("total_cars_in_stock", showroom.total_cars_in_stock);

        ApiFuture<DocumentReference> addedDocRef = db.collection("Showrooms").add(data);
        String id = addedDocRef.get().getId();
        showroom.documentId = id;
        System.out.println("Showroom saved with id: " + id);
        return id;
    }

    // Add Employee under a showroom
    public static String addEmployee(String showroomId, Employees emp) throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();
        String empId = (emp.emp_id != null && !emp.emp_id.isEmpty()) ? emp.emp_id : UUID.randomUUID().toString();

        Map<String, Object> data = new HashMap<>();
        data.put("emp_id", empId);
        data.put("emp_name", emp.emp_name);
        data.put("emp_age", emp.emp_age);
        data.put("emp_department", emp.emp_department);

        ApiFuture<WriteResult> writeResult = db.collection("Showrooms")
                .document(showroomId)
                .collection("Employees")
                .document(empId)
                .set(data);

        writeResult.get();
        System.out.println("Employee saved under showroom " + showroomId + " with id: " + empId);
        return empId;
    }

    // Add Car under a showroom
    public static String addCar(String showroomId, Cars car) throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();

        Map<String, Object> data = new HashMap<>();
        data.put("car_name", car.car_name);
        data.put("car_color", car.car_color);
        data.put("car_fuel_type", car.car_fuel_type);
        data.put("car_price", car.car_price);
        data.put("car_type", car.car_type);
        data.put("car_transmission", car.car_transmission);

        ApiFuture<DocumentReference> addedDocRef = db.collection("Showrooms")
                .document(showroomId)
                .collection("Cars")
                .add(data);

        String id = addedDocRef.get().getId();
        System.out.println("Car saved under showroom " + showroomId + " with id: " + id);
        return id;
    }

    // Get all Showrooms
    public static void getAllShowrooms() throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Showrooms").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        if (documents.isEmpty()) {
            System.out.println("No showrooms found.");
            return;
        }

        for (QueryDocumentSnapshot doc : documents) {
            System.out.println("Showroom ID: " + doc.getId());
            System.out.println("Name: " + doc.getString("showroom_name"));
            System.out.println("Address: " + doc.getString("showroom_address"));
            System.out.println("Manager: " + doc.getString("manager_name"));
            System.out.println("Total Employees: " + doc.getLong("total_employees"));
            System.out.println("Total Cars in Stock: " + doc.getLong("total_cars_in_stock"));
            System.out.println("---------------------------");
        }
    }

    // Get all Employees of a showroom
    public static void getAllEmployees(String showroomId) throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Showrooms")
                .document(showroomId)
                .collection("Employees")
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            System.out.println("No employees found in this showroom.");
            return;
        }

        for (QueryDocumentSnapshot doc : documents) {
            System.out.println("Employee ID: " + doc.getString("emp_id"));
            System.out.println("Name: " + doc.getString("emp_name"));
            System.out.println("Age: " + doc.getLong("emp_age"));
            System.out.println("Department: " + doc.getString("emp_department"));
            System.out.println("---------------------------");
        }
    }

    // Get all Cars of a showroom
    public static void getAllCars(String showroomId) throws InterruptedException, ExecutionException {
        Firestore db = FirebaseInitializer.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Showrooms")
                .document(showroomId)
                .collection("Cars")
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        if (documents.isEmpty()) {
            System.out.println("No cars found in this showroom.");
            return;
        }

        for (QueryDocumentSnapshot doc : documents) {
            System.out.println("Car ID: " + doc.getId());
            System.out.println("Name: " + doc.getString("car_name"));
            System.out.println("Color: " + doc.getString("car_color"));
            System.out.println("Fuel Type: " + doc.getString("car_fuel_type"));
            System.out.println("Price: " + doc.getLong("car_price"));
            System.out.println("Type: " + doc.getString("car_type"));
            System.out.println("Transmission: " + doc.getString("car_transmission"));
            System.out.println("---------------------------");
        }
    }
}