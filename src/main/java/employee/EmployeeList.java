package employee;

import java.util.ArrayList;

public class EmployeeList {
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void listEmployee() {
        System.out.println("Here are the employees in your employee list:");
        for (Employee employee : employees) {
            System.out.print(employee.getEmployeeId() + ". ");
            System.out.println(employee.getEmployeeName());
        }
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Got it. I've added this employee:");
        System.out.println(employee.getEmployeeName());
        System.out.println("Now you have " + employees.size() + " employees in the employee list.");
    }

    public static void removeEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                System.out.println("Noted. I've removed this employee:");
                System.out.println(employee.getEmployeeName());
                System.out.println("Now you have " + (employees.size() - 1) + " employees in the employee list.");
                employees.remove(employee);
                break;
            }
        }
    }
}