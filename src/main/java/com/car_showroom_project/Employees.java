package com.car_showroom_project;

import java.util.Scanner;
import java.util.UUID;

public class Employees {
    public String emp_id;
    public String emp_name;
    public int emp_age;
    public String emp_department;
    public String documentId; // Firestore document ID

    public void setDetails() {
        Scanner sc = new Scanner(System.in);
        emp_id = UUID.randomUUID().toString();

        System.out.println("=============== ENTER EMPLOYEE DETAILS ===============");
        System.out.print("Employee Name: ");
        emp_name = sc.nextLine();

        System.out.print("Employee Age: ");
        emp_age = sc.nextInt();
        sc.nextLine();

        System.out.print("Employee Department: ");
        emp_department = sc.nextLine();
    }
}
