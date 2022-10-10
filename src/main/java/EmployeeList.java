import java.util.ArrayList;

public class EmployeeList {
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void listEmployee() {
        for (Employee employee : employees) {
            System.out.print(employee.getEmployeeId() + " ");
            System.out.print(employee.getEmployeeName()+ " ");
        }
    }

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static void removeEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                employees.remove(employee);
                break;
            }
        }
    }
}