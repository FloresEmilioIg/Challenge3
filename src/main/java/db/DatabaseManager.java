package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Manages database connectivity and initialization.
 * <p>
 * This class handles connecting to a MySQL database, creating required tables,
 * and ensuring the database is ready for article storage.
 * </p>
 */
public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/Challenge3";
    private static final String USER = "root";        // <--- MySQL user
    private static final String PASSWORD = "1234";    // <--- MySQL password

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return a {@link Connection} object if successful, or null if the connection fails
     */
    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver not found: " + e.getMessage());
        }
        return null;
    }

    /**
     * Initializes the database schema by creating the 'articles' table if it does not exist.
     * <p>
     * The table includes columns for researcher name, title, authors, publication date,
     * abstract, link, keywords, and citation count.
     * </p>
     */
    public static void initializeDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS articles (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "researcher_name VARCHAR(255) NOT NULL," +
                    "title TEXT," +
                    "authors TEXT," +
                    "publication_date VARCHAR(50)," +
                    "abstract TEXT," +
                    "link TEXT," +
                    "keywords TEXT," +
                    "cited_by INT)";
            stmt.execute(sql);
            System.out.println("✅ Database and table ready.");
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }
    }
}
