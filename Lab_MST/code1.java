import java.util.HashMap;

class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}

public class EmployeeMap {
    private HashMap<Integer, String> employeeMap = new HashMap<>();

    public void addEmployee(int id, String name) {
        employeeMap.put(id, name);
    }

    public String getEmployeeName(int id) throws EmployeeNotFoundException {
        if (!employeeMap.containsKey(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        return employeeMap.get(id);
    }

    public static void main(String[] args) {
        EmployeeMap employees = new EmployeeMap();
        employees.addEmployee(101, "Alice");
        employees.addEmployee(102, "Bob");

        try {
            System.out.println(employees.getEmployeeName(101));
            System.out.println(employees.getEmployeeName(103));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
