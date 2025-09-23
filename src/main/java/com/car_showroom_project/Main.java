package com.car_showroom_project;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void mainMenu() {
        System.out.println("\n=============== *** WELCOME TO SHOWROOM MANAGEMENT SYSTEM *** ===============\n");
        System.out.println("1]. ADD SHOWROOM");
        System.out.println("2]. ADD EMPLOYEE");
        System.out.println("3]. ADD CAR");
        System.out.println("4]. VIEW ALL SHOWROOMS");
        System.out.println("5]. VIEW ALL EMPLOYEES OF A SHOWROOM");
        System.out.println("6]. VIEW ALL CARS OF A SHOWROOM");
        System.out.println("0]. EXIT");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);

        // Initialize Firebase
        FirebaseInitializer.initialize();

        boolean running = true;
        while (running) {
            mainMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> { // Add Showroom
                    Showroom showroom = new Showroom();
                    System.out.println("=============== ENTER SHOWROOM DETAILS ===============");
                    System.out.print("Showroom Name: ");
                    showroom.showroom_name = sc.nextLine();
                    System.out.print("Showroom Address: ");
                    showroom.showroom_address = sc.nextLine();
                    System.out.print("Manager Name: ");
                    showroom.manager_name = sc.nextLine();
                    System.out.print("Total Employees: ");
                    showroom.total_employees = sc.nextInt();
                    System.out.print("Total Cars in Stock: ");
                    showroom.total_cars_in_stock = sc.nextInt();
                    sc.nextLine();

                    FirestoreService.addShowroom(showroom);
                }

                case 2 -> { // Add Employee
                    System.out.print("Enter Showroom Document ID to add employee: ");
                    String showroomId = sc.nextLine();
                    Employees emp = new Employees();
                    emp.setDetails();
                    FirestoreService.addEmployee(showroomId, emp);
                }

                case 3 -> { // Add Car
                    System.out.print("Enter Showroom Document ID to add car: ");
                    String showroomId = sc.nextLine();
                    Cars car = new Cars();
                    car.setDetails();
                    FirestoreService.addCar(showroomId, car);
                }

                case 4 -> { // View all showrooms
                    FirestoreService.getAllShowrooms();
                }

                case 5 -> { // View all employees of a showroom
                    System.out.print("Enter Showroom Document ID to view employees: ");
                    String showroomId = sc.nextLine();
                    FirestoreService.getAllEmployees(showroomId);
                }

                case 6 -> { // View all cars of a showroom
                    System.out.print("Enter Showroom Document ID to view cars: ");
                    String showroomId = sc.nextLine();
                    FirestoreService.getAllCars(showroomId);
                }

                case 0 -> {
                    System.out.println("Exiting...");
                    running = false;
                }

                default -> System.out.println("Invalid choice! Please try again.");
            }
            System.out.println();
        }
        sc.close();
    }
}