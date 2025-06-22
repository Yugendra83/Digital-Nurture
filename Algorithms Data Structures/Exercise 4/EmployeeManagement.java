import java.util.Scanner;

public class EmployeeManagement {

    // Employee class
    static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return employeeId + " | " + name + " | " + position + " | ₹" + salary;
        }
    }

    static final int MAX = 100; // Max employees
    static Employee[] employees = new Employee[MAX];
    static int count = 0; // Current number of employees

    // Add employee
    public static void addEmployee(Employee emp) {
        if (count < MAX) {
            employees[count++] = emp;
            System.out.println("✅ Employee added.");
        } else {
            System.out.println("❌ Employee list full.");
        }
    }

    // Search employee by ID
    public static void searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("🔍 Found: " + employees[i]);
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    // Traverse (Display) all employees
    public static void displayEmployees() {
        if (count == 0) {
            System.out.println("📭 No employees to display.");
        } else {
            System.out.println("📋 Employee List:");
            for (int i = 0; i < count; i++) {
                System.out.println(employees[i]);
            }
        }
    }

    // Delete employee by ID
    public static void deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                // Shift remaining elements
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--count] = null;
                System.out.println("🗑️ Employee deleted.");
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. Display All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Position: ");
                    String pos = sc.nextLine();
                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();
                    addEmployee(new Employee(id, name, pos, sal));
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    int sid = sc.nextInt();
                    searchEmployee(sid);
                    break;
                case 3:
                    displayEmployees();
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    deleteEmployee(did);
                    break;
                case 5:
                    System.out.println("👋 Exiting.");
                    sc.close();
                    return;
                default:
                    System.out.println("❗ Invalid choice.");
            }
        }
    }
}
