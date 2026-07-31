package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/BancoTimes";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Falha na conexão com o banco: " + e.getMessage());
        }
    }
}