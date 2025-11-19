Student Management System — Java Swing | MySQL | JDBC

A modern desktop-based Student Management System built using Java Swing, MySQL, and JDBC, featuring a clean & professional UI powered by FlatLaf.
This application provides full CRUD operations, login authentication, and a dashboard-style user interface.

🚀 Features
🔐 User Authentication

Secure login system

Credentials stored in MySQL

Validates username + password

🎨 Modern UI (FlatLaf)

Clean, modern look

Sidebar navigation

Header bar

Responsive layout

Styled buttons & forms

📦 Student Management (CRUD)

➕ Add Student

📄 View All Students

✏️ Update Student

🗑️ Delete Student

🔍 View Student by ID

🗄️ Database (MySQL)

JDBC connectivity

DAO pattern for clean structure

Efficient SQL queries

🛠️ Tech Stack
Technology	Purpose
Java (JDK 22)	Core logic & GUI
Swing	Desktop UI framework
FlatLaf	Modern themes & styling
MySQL	Database
JDBC	Database connectivity
DAO Pattern	Code organization
📂 Project Structure
/SwingStudentManagement
│── Login.java
│── MainGUI.java
│── Student.java
│── StudentDAO.java
│── StudentDAOImpl.java
│── LoginDAO.java
│── LoginDAOImpl.java
│── DBConnection.java
│── mysql-connector-j-9.5.0.jar
│── flatlaf-3.4.1.jar
│── StudentManagementSystem.jar   <-- Runnable JAR
│── README.md

▶️ How to Run the Project
1️⃣ Install MySQL

Create database:

CREATE DATABASE studentdb;
USE studentdb;


Create tables:

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin123');

CREATE TABLE students (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  grade VARCHAR(20)
);

2️⃣ Run the JAR File
java -jar StudentManagementSystem.jar


⚠ Must have mysql-connector-j-9.5.0.jar and flatlaf-3.4.1.jar in the same folder.

🏗️ Build Instructions (Compile Yourself)
javac -cp ".:mysql-connector-j-9.5.0.jar:flatlaf-3.4.1.jar" *.java
java -cp ".:mysql-connector-j-9.5.0.jar:flatlaf-3.4.1.jar" Login
🧱 Future Improvements

Password hashing (SHA-256 / BCrypt)

Export students to CSV

Search & filter system

Dark mode toggle

Student photo upload

Attendance & marks module

🧑‍💻 Author

Ashmita Barnwal
Built as a Java learning project to explore GUI development, JDBC, and MySQL.

⭐ If you found this helpful

Consider giving the repo a star ⭐ on GitHub!
