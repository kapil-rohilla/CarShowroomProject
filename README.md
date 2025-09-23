# CarShowroomFirebase

A simple **Showroom Management System** built in **Java (Maven)** with **Firebase Firestore** integration.

---

## Features

- Add **Showrooms**, **Employees**, and **Cars**.  
- Store all data in **Firebase Firestore**.  
- Retrieve and view all Showrooms, Employees, and Cars.  
- Keeps sensitive credentials safe using `.gitignore`.

---

## Project Structure

CarShowroomFirebase/
├─ src/main/java/com/car_showroom_project/
│ ├─ Main.java
│ ├─ Showroom.java
│ ├─ Employees.java
│ ├─ Cars.java
│ ├─ FirestoreService.java
│ ├─ FirebaseInitializer.java
├─ src/main/resources/
│ └─ car-showroom-14428-firebase-adminsdk-fbsvc-4104b9025c.json (ignored)
├─ pom.xml
├─ .gitignore
└─ README.md


---

## Prerequisites

- **Java 21** or higher  
- **Maven**  
- **Firebase project** with Firestore enabled  
- Firebase service account JSON placed in `src/main/resources/` (ignored by Git)

---

## How to Run

1. Clone the repository:  
```bash
git clone https://github.com/kapilrohilla2001/CarShowroomFirebase.git
cd CarShowroomFirebase

2. Add your Firebase JSON key to src/main/resources/

3. Build and run the project:
mvn clean compile exec:java -Dexec.mainClass="com.car_showroom_project.Main"

4. Follow the on-screen menu to add or view Showrooms, Employees, and Cars.

## Notes

Do not commit the Firebase service account JSON.

Firestore collections used: Showrooms, Showrooms/{showroomId}/Employees, Showrooms/{showroomId}/Cars.

**License**

MIT License


