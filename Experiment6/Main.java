import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - " + age + " - " + salary;
    }
}

class Student {
    String name;
    int id;
    double marks;

    Student(String name, int id, double marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
    }
}

public class Main {
    public static void main(String[] args) {
        // ---------- EASY LEVEL ----------
        System.out.println("===== EASY LEVEL =====");
        System.out.println("Sorting Employees using Lambda Expressions\n");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 30, 50000));
        employees.add(new Employee("Alice", 25, 60000));
        employees.add(new Employee("Bob", 28, 75000));

        // Sort by salary using Lambda
        employees.sort((e1, e2) -> Double.compare(e1.salary, e2.salary));

        System.out.println("Sorted by Salary:");
        employees.forEach(System.out::println);

        // ---------- MEDIUM LEVEL ----------
        System.out.println("\n===== MEDIUM LEVEL =====");
        System.out.println("Filtering and Sorting Students using Stream API\n");

        List<Student> students = Arrays.asList(
                new Student("Ravi", 1, 85),
                new Student("Aditi", 2, 92),
                new Student("Kiran", 3, 78),
                new Student("Meena", 4, 65),
                new Student("Raj", 5, 70)
        );

        System.out.println("Students scoring above 75%:");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted((s1, s2) -> Double.compare(s2.marks, s1.marks)) // descending order
                .map(s -> s.name)
                .forEach(System.out::println);
    }
}
