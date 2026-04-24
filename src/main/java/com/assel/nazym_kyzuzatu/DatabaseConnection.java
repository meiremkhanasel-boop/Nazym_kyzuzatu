package com.assel.nazym_kyzuzatu;

import java.sql.*;

/**
 * Класс для управления подключением к MSSQL базе данных
 */
public class DatabaseConnection {

    // MSSQL соединение параметры
    private static final String SERVER = "localhost";
    private static final int PORT = 1433;
    private static final String DATABASE = "NazymDatabase";
    private static final String USERNAME = "test";  // Используем существующий логин
    private static final String PASSWORD = "";  // Оставить пустым если нет пароля, или укажите свой

    // Connection string для MSSQL
    private static final String CONNECTION_STRING =
        String.format("jdbc:sqlserver://%s:%d;databaseName=%s;encrypt=true;trustServerCertificate=true;",
                SERVER, PORT, DATABASE);

    static {
        try {
            // Загружаем MSSQL драйвер
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("MSSQL драйвер не найден: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Получить подключение к БД
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Закрыть ресурсы
     */
    public static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println("Ошибка при закрытии ресурсов: " + e.getMessage());
        }
    }

    /**
     * Проверить подключение
     */
    public static boolean testConnection() {
        Connection conn = null;
        try {
            conn = getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println("✓ БД: " + metaData.getDatabaseProductName());
            System.out.println("✓ Версия: " + metaData.getDatabaseProductVersion());
            return true;
        } catch (SQLException e) {
            System.err.println("✗ Ошибка подключения: " + e.getMessage());
            return false;
        } finally {
            closeResources(conn, null, null);
        }
    }
}

