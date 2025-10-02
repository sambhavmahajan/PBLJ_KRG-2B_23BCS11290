import java.io.*;
import java.util.*;

// Medium Level: Serializable Student
class Student implements Serializable {
    int id;
    String name;
    double gpa;

    Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nGPA: " + gpa;
    }
}

// Hard Level: Employee for file storage
class Employee {
    String name;
    int id;
    String designation;
    double salary;

    Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public String toString() {
        return name + " | " + id + " | " + designation + " | " + salary;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Easy Level: Autoboxing and sum
        System.out.println("=== Easy Level: Sum using Autoboxing ===");
        System.out.print("Enter numbers (comma separated): ");
        String input = sc.nextLine();
        String[] numStrings = input.split(",");
        ArrayList<Integer> numbers = new ArrayList<>();
        int sum = 0;
        for (String str : numStrings) {
            try {
                int num = Integer.parseInt(str.trim());
                numbers.add(num); // autoboxing
                sum += num;       // unboxing
            } catch (NumberFormatException e) {
                System.out.println("Invalid number: " + str);
            }
        }
        System.out.println("Sum of numbers = " + sum);

        // Medium Level: Serialization/Deserialization
        System.out.println("\n=== Medium Level: Student Serialization ===");
        Student student = new Student(101, "Sambhav Mahajan", 9.1);
        String filename = "student.ser";

        try {
            // Serialize
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(student);
            oos.close();
            System.out.println("Student serialized successfully!");

            // Deserialize
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Student deserialized = (Student) ois.readObject();
            ois.close();

            System.out.println("Student deserialized:");
            System.out.println(deserialized);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("IO Exception occurred.");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
        }

        // Hard Level: Employee File Management
        System.out.println("\n=== Hard Level: Employee File Management ===");
        String empFile = "employees.txt";

        while (true) {
            System.out.println("\nMenu:\n1. Add Employee\n2. Display All\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Designation: ");
                String designation = sc.nextLine();
                System.out.print("Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();

                Employee emp = new Employee(name, id, designation, salary);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(empFile, true))) {
                    bw.write(emp.toString());
                    bw.newLine();
                    System.out.println("Employee added successfully!");
                } catch (IOException e) {
                    System.out.println("Error writing to file.");
                }

            } else if (choice == 2) {
                System.out.println("Employee List:");
                try (BufferedReader br = new BufferedReader(new FileReader(empFile))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("No employees found.");
                } catch (IOException e) {
                    System.out.println("Error reading file.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting program.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
