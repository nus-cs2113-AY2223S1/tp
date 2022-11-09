package employee;

import java.util.ArrayList;

public class EmployeeList {
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void listEmployee() {
        System.out.println("Here are the employees in your employee list:");
        int index = 1;
        for (Employee employee : employees) {
            System.out.print(index + ". " + "ID:" + employee.getEmployeeId() + "\t");
            System.out.println(" " + "name:" + employee.getEmployeeName() + "\t");
            index += 1;
        }
    }

    public static ArrayList<Employee> employeeArrayList() {
        return employees;
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.print("Got it. I've added this employee: ");
        System.out.println(employee.getEmployeeName());
        System.out.println("Now you have " + employees.size() + " employees in the list.");
    }

    public static void removeEmployee(int employeeId) {
        boolean removeFlag = false;
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employees.remove(employee);
                System.out.print("Noted. I've removed this employee: ");
                System.out.println(employee.getEmployeeName());
                System.out.println("Now you have " + (employees.size()) + " employees in the list.");
                removeFlag = true;
                break;
            }
        }
        if (!removeFlag) {
            System.out.println("Sorry, no corresponding employee found.");
        }
    }

    public static Employee findEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    // view task for a particular employee
    public static void viewEmployeeTasks(int employeeId) {
        Employee associatedEmployee = EmployeeList.findEmployee(employeeId);
        assert associatedEmployee != null;
        associatedEmployee.viewTasks();
    }
}