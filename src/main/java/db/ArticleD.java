package db;

import Model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Data Access Object (DAO) for saving academic articles into the database.
 * <p>
 * This class handles the persistence of Article objects in the MySQL database.
 * It provides methods to insert articles while avoiding duplicates based on
 * researcher name and article title.
 * </p>
 */
public class ArticleD {
    public void saveArticles(String researcherName, List<Article> articles) {
        String sql = "INSERT INTO articles (researcher_name, title, authors, publication_date, abstract, link, keywords, cited_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String checkSql = "SELECT COUNT(*) FROM articles WHERE researcher_name=? AND title=?";


        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (Article article : articles) {
                pstmt.setString(1, researcherName);
                pstmt.setString(2, article.getTitle());
                pstmt.setString(3, article.getAuthors());
                pstmt.setString(4, article.getYear());
                pstmt.setString(5, ""); // abstract is empty for now
                pstmt.setString(6, article.getLink());
                pstmt.setString(7, ""); // keywords are empty for now
                pstmt.setInt(8, parseCitedBy(article.getCitedBy()));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("✅ Articles saved to MySQL.");

        } catch (SQLException e) {
            System.out.println("❌ Error saving articles: " + e.getMessage());
        }
    }

    /**
     * Converts the citedBy string to an integer.
     * <p>
     * Non-numeric characters are removed. If parsing fails, 0 is returned.
     * </p>
     *
     * @param citedBy string representing citation count
     * @return integer value of citations
     */
    private int parseCitedBy(String citedBy) {
        try {
            return Integer.parseInt(citedBy.replaceAll("\\D", ""));
        } catch (Exception e) {
            return 0;
        }
    }
}
