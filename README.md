# Payroll System

**Description:**  
A system that automates the calculation of employee salaries, taxes, and benefits.  
This desktop application supports Admin and Viewer roles, handles Full-time and Part-time employees, and stores data persistently in a text file.
## Features

- Add, view, and manage employees (Admin only)
- View employee salaries (Admin and Viewer)
- Automatic tax calculation for Full-time employees
- Supports Full-time and Part-time employees
- Persistent storage using `employees.txt`
- Role-based GUI (Admin vs Viewer)
## Technology Stack

- Java SE (Java 17 recommended)
- JavaFX for GUI
- File I/O for persistent storage (`employees.txt`)
- Object-Oriented Programming principles (Encapsulation, Abstraction, Inheritance, Polymorphism)
- Exception handling for invalid input
## Project Structure

PayrollSystem/
├── src/
│ ├── model/ # Employee classes, UserRole
│ ├── repository/ # EmployeeRepository interface, FileEmployeeRepository
│ ├── service/ # PayrollService, EmployeeFactory
│ └── ui/ # PayrollApp.java
├── data/
│ └── employees.txt
└── README.md
## How to Run

1. Open the project in IntelliJ IDEA / Eclipse / NetBeans.
2. Ensure JavaFX SDK is configured in your IDE.
3. Compile the project.
4. Run `ui.PayrollApp` as a JavaFX Application.
5. Use the GUI to add, view, and delete employees (Admin only).
6. Viewer role can only see employee information.

## User Roles

- **Admin:** Can add, delete, and view all employees.
- **Viewer:** Can only view employee details; all input fields and buttons are disabled.
## Salary Calculation

- **Full-time Employee:** `Salary = Base + Benefits - 10% Tax`
- **Part-time Employee:** `Salary = Hours * Rate` 
- Tax calculation is handled automatically in the Employee classes.
