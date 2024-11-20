import java.sql.*;
import java.util.Scanner;

public class OnlineReservationSystem {
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/reservation_db";
    private static final String USER = "root";
    private static final String PASS = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println("Connected to the database.");
            }
            
            while (true) {
                System.out.println("\n--- Online Reservation System ---");
                System.out.println("1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        // Reservation process
                        reservation(conn, sc);
                        break;
                    case 2:
                        // Cancellation process
                        cancellation(conn, sc);
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        sc.close();
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Reservation function
    private static void reservation(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Name: ");
            String name = sc.next();
            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            System.out.print("Enter Gender (M/F): ");
            String gender = sc.next();
            System.out.print("Enter Boarding Point: ");
            String boardingPoint = sc.next();
            System.out.print("Enter Destination: ");
            String destination = sc.next();

            String query = "INSERT INTO reservations (name, age, gender, boarding_point, destination) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, gender);
            pstmt.setString(4, boardingPoint);
            pstmt.setString(5, destination);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Reservation successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cancellation function
    private static void cancellation(Connection conn, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID for cancellation: ");
            int reservationId = sc.nextInt();

            String query = "DELETE FROM reservations WHERE reservation_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, reservationId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reservation cancelled successfully!");
            } else {
                System.out.println("Reservation ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}