import java.sql.*;
import java.util.*;

public class Main {

    // Database credentials (change as per your local setup)
    static final String URL = "jdbc:mysql://localhost:3306/testdb"; // replace 'testdb' with your DB name
    static final String USER = "root"; // replace with your username
    static final String PASSWORD = "password"; // replace with your password

    public static void main(String[] args) {
        System.out.println("===== EXPERIMENT 2.4 =====");
        System.out.println("CO Mapped – CO3, CO4");
        System.out.println("JDBC Connectivity & CRUD Operations\n");

        // EASY LEVEL
        fetchEmployeeData();

        // MEDIUM LEVEL
        performProductCRUD();
    }

    // ==================== EASY LEVEL ====================
    // Fetch all Employee records
    public static void fetchEmployeeData() {
        System.out.println("===== EASY LEVEL =====");
        System.out.println("Fetching data from Employee table...\n");

        try {
            // 1. Load the JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Create statement
            Statement stmt = con.createStatement();

            // 4. Execute query
            ResultSet rs = stmt.executeQuery("SELECT EmpID, Name, Salary FROM Employee");

            // 5. Process results
            while (rs.next()) {
                System.out.println("EmpID: " + rs.getInt("EmpID")
                        + ", Name: " + rs.getString("Name")
                        + ", Salary: " + rs.getDouble("Salary"));
            }

            // 6. Close connection
            con.close();

        } catch (Exception e) {
            System.out.println("Error fetching Employee data: " + e.getMessage());
        }

        System.out.println("\n-----------------------------------\n");
    }

    // ==================== MEDIUM LEVEL ====================
    // Menu-driven CRUD operations for Product table
    public static void performProductCRUD() {
        System.out.println("===== MEDIUM LEVEL =====");
        System.out.println("Performing CRUD Operations on Product Table...\n");

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false); // Begin transaction

            while (true) {
                System.out.println("\n--- PRODUCT MANAGEMENT MENU ---");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                try {
                    switch (choice) {
                        case 1:
                            addProduct(con, sc);
                            break;
                        case 2:
                            viewProducts(con);
                            break;
                        case 3:
                            updateProduct(con, sc);
                            break;
                        case 4:
                            deleteProduct(con, sc);
                            break;
                        case 5:
                            System.out.println("Exiting program...");
                            con.close();
                            return;
                        default:
                            System.out.println("Invalid choice!");
                    }
                    con.commit(); // Commit transaction if successful
                } catch (Exception ex) {
                    con.rollback(); // Rollback on failure
                    System.out.println("Transaction failed, rolled back. Error: " + ex.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // ---------- CRUD Methods ----------

    // CREATE
    private static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product(ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setDouble(3, price);
        ps.setInt(4, qty);

        ps.executeUpdate();
        System.out.println("Product added successfully!");
    }

    // READ
    private static void viewProducts(Connection con) throws SQLException {
        String sql = "SELECT * FROM Product";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("\n--- PRODUCT LIST ---");
        while (rs.next()) {
            System.out.println("ProductID: " + rs.getInt("ProductID")
                    + ", Name: " + rs.getString("ProductName")
                    + ", Price: " + rs.getDouble("Price")
                    + ", Quantity: " + rs.getInt("Quantity"));
        }
    }

    // UPDATE
    private static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to Update: ");
        int id = sc.nextInt();
        System.out.print("Enter new Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter new Quantity: ");
        int qty = sc.nextInt();

        String sql = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, price);
        ps.setInt(2, qty);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Product updated successfully!");
        else System.out.println("Product not found!");
    }

    // DELETE
    private static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to Delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM Product WHERE ProductID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Product deleted successfully!");
        else System.out.println("Product not found!");
    }
}
