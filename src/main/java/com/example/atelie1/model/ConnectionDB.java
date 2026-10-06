package com.example.atelie1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    private static ConnectionDB instance;
    private static final String URL = "jdbc:postgresql://localhost:5433/atelier_crud";
    private final String USER = "atelier_user";
    private final String PASSWORD = "atelier_pass";
    private Connection connection;
    private ConnectionDB(){
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connection established successfully in constructor.");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
    public static ConnectionDB getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new ConnectionDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
