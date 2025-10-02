import java.util.*;

// Employee class for Easy level
class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Salary=" + salary;
    }
}

// Card class for Medium level
class Card {
    String symbol;
    int number;

    Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String toString() {
        return symbol + " - " + number;
    }
}

// TicketBooking class for Hard level
class TicketBooking {
    private boolean seatBooked = false;

    public synchronized void bookTicket(String user) {
        if (!seatBooked) {
            seatBooked = true;
            System.out.println(user + " booked Seat 1");
        } else {
            System.out.println(user + " could not book. Seat already booked.");
        }
    }
}

// Thread for ticket booking
class BookingThread extends Thread {
    private TicketBooking booking;
    private String user;

    BookingThread(TicketBooking booking, String user) {
        this.booking = booking;
        this.user = user;
    }

    public void run() {
        booking.bookTicket(user);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Easy Level: Employee management with ArrayList
        System.out.println("=== Easy Level: Employee Management with ArrayList ===");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "John", 50000));
        employees.add(new Employee(102, "Alice", 60000));

        // Add Employee
        employees.add(new Employee(103, "Bob", 55000));

        // Update Employee by ID
        for (Employee e : employees) {
            if (e.id == 101) {
                e.salary = 52000;
            }
        }

        // Search Employee
        int searchId = 101;
        boolean found = false;
        for (Employee e : employees) {
            if (e.id == searchId) {
                System.out.println("Employee Found: " + e);
                found = true;
            }
        }
        if (!found) System.out.println("Employee not found.");

        // Remove Employee
        employees.removeIf(e -> e.id == 102);

        System.out.println("All Employees:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        // Medium Level: Cards with HashMap
        System.out.println("\n=== Medium Level: Cards with HashMap ===");
        HashMap<String, ArrayList<Card>> cardMap = new HashMap<>();

        Card[] cardArray = {
                new Card("Spade", 1),
                new Card("Spade", 3),
                new Card("Spade", 10),
                new Card("Heart", 5),
                new Card("Heart", 7)
        };

        for (Card c : cardArray) {
            cardMap.putIfAbsent(c.symbol, new ArrayList<>());
            cardMap.get(c.symbol).add(c);
        }

        System.out.print("Enter symbol to search: ");
        String symbol = sc.nextLine();

        if (cardMap.containsKey(symbol)) {
            System.out.println("Cards with symbol '" + symbol + "':");
            for (Card c : cardMap.get(symbol)) {
                System.out.println(c);
            }
        } else {
            System.out.println("No cards found with symbol '" + symbol + "'");
        }

        // Hard Level: Ticket Booking with Threads
        System.out.println("\n=== Hard Level: Ticket Booking with Threads ===");
        TicketBooking booking = new TicketBooking();

        BookingThread t1 = new BookingThread(booking, "Normal User");
        BookingThread t2 = new BookingThread(booking, "VIP User");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
