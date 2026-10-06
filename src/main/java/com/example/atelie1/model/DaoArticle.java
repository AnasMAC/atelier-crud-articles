package com.example.atelie1.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DaoArticle {
    private static DaoArticle instance = null;

    // Using the persistent connection from your Singleton ConnectionDB
    private Connection connection;

    private DaoArticle() {
        this.connection = ConnectionDB.getInstance().getConnection();
        init();
    }

    public static DaoArticle getInstance() {
        if (instance == null) {
            instance = new DaoArticle();
        }
        return instance;
    }

    public void init() {
        // 1. Create the table automatically if it doesn't exist
        String createTableSql = "CREATE TABLE IF NOT EXISTS articles (" +
                "code VARCHAR(50) PRIMARY KEY, " +
                "destination VARCHAR(255) NOT NULL, " +
                "prix DOUBLE PRECISION NOT NULL)";
        try (PreparedStatement ps = connection.prepareStatement(createTableSql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2. Insert the seed data ONLY if the database is currently empty
        if (getAll().isEmpty()) {
            List<Article> seed = Arrays.asList(
                    new Article("ar_001", "tanger", 100.),
                    new Article("ar_002", "assilah", 100.),
                    new Article("ar_003", "raba", 100.)
            );
            for (Article article : seed) {
                save(article);
            }
        }
    }

    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        String sql = "SELECT code, destination, prix FROM articles";

        // try-with-resources automatically closes the PreparedStatement and ResultSet
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                articles.add(new Article(
                        rs.getString("code"),
                        rs.getString("destination"),
                        rs.getDouble("prix")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public Optional<Article> getById(String code) {
        String sql = "SELECT code, destination, prix FROM articles WHERE code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Article article = new Article(
                            rs.getString("code"),
                            rs.getString("destination"),
                            rs.getDouble("prix")
                    );
                    return Optional.of(article);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Boolean deleteArticle(String code) {
        String sql = "DELETE FROM articles WHERE code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean save(Article article) {
        String sql = "INSERT INTO articles (code, destination, prix) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, article.getCode());
            ps.setString(2, article.getDestination());
            ps.setDouble(3, article.getPrix());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // This will catch Duplicate Key exceptions if the code already exists
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Article article) {
        String sql = "UPDATE articles SET destination = ?, prix = ? WHERE code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, article.getDestination());
            ps.setDouble(2, article.getPrix());
            ps.setString(3, article.getCode());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}