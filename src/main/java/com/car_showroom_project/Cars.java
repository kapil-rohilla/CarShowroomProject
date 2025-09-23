package com.car_showroom_project;

import java.util.Scanner;

public class Cars {
    public String car_name;
    public String car_color;
    public String car_fuel_type;
    public int car_price;
    public String car_type;
    public String car_transmission;
    public String documentId; // Firestore document ID

    public void setDetails() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=============== ENTER CAR DETAILS ===============");

        System.out.print("Car Name: ");
        car_name = sc.nextLine();

        System.out.print("Car Color: ");
        car_color = sc.nextLine();

        System.out.print("Car Fuel Type (Petrol/Diesel): ");
        car_fuel_type = sc.nextLine();

        System.out.print("Car Price: ");
        car_price = sc.nextInt();
        sc.nextLine();

        System.out.print("Car Type (Sedan/SUV/Hatchback): ");
        car_type = sc.nextLine();

        System.out.print("Car Transmission (Automatic/Manual): ");
        car_transmission = sc.nextLine();
    }
}