package employee;

public class Employee {
    private static int id = 0;
    private final int employeeId;
    private String employeeName;

    public Employee(String employeeName) {
        this.employeeId = ++id;
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {

        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
