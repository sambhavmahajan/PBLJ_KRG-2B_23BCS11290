import java.util.*;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private String prerequisite;
    private int capacity;
    private int enrolled;

    public Course(String name, String prerequisite, int capacity) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public void enroll(String student, boolean hasCompletedPrerequisite)
            throws CourseFullException, PrerequisiteNotMetException {
        if (enrolled >= capacity) {
            throw new CourseFullException("Course is full: " + name);
        }
        if (!hasCompletedPrerequisite && prerequisite != null) {
            throw new PrerequisiteNotMetException(
                    "Complete prerequisite course (" + prerequisite + ") before enrolling in " + name
            );
        }
        enrolled++;
        System.out.println(student + " successfully enrolled in " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Easy Level
        System.out.println("=== Easy Level: Square Root Calculation ===");
        try {
            System.out.print("Enter a number: ");
            double num = Double.parseDouble(sc.nextLine());
            if (num < 0) {
                throw new ArithmeticException("Cannot calculate the square root of a negative number");
            }
            System.out.println("Square root: " + Math.sqrt(num));
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input, please enter a numeric value.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Medium Level
        System.out.println("\n=== Medium Level: ATM Withdrawal System ===");
        int balance = 3000;
        int correctPIN = 1234;
        try {
            System.out.print("Enter PIN: ");
            int pin = Integer.parseInt(sc.nextLine());
            if (pin != correctPIN) {
                throw new SecurityException("Invalid PIN.");
            }
            System.out.print("Enter withdrawal amount: ");
            int amount = Integer.parseInt(sc.nextLine());
            if (amount > balance) {
                throw new InsufficientBalanceException("Insufficient balance. Current Balance: " + balance);
            }
            balance -= amount;
            System.out.println("Withdrawal successful. Amount withdrawn: " + amount);
        } catch (SecurityException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Remaining Balance: " + balance);
        }

        // Hard Level
        System.out.println("\n=== Hard Level: University Enrollment System ===");
        Course coreJava = new Course("Core Java", null, 2);
        Course advJava = new Course("Advanced Java", "Core Java", 2);

        try {
            coreJava.enroll("Alice", true);
            coreJava.enroll("Bob", true);
            advJava.enroll("Charlie", false);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            coreJava.enroll("David", true);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
