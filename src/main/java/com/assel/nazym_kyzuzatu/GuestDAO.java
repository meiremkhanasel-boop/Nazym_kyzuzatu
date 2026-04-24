package com.assel.nazym_kyzuzatu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO класс для работы с гостями в БД
 */
public class GuestDAO {

    /**
     * Сохранить нового гостя в БД
     */
    public static int saveGuest(Guest guest) throws SQLException {
        String sql = "INSERT INTO Guests (Name, PhoneNumber, GuestCount, Spouse, Attendance, Notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getPhoneNumber());
            pstmt.setInt(3, guest.getGuestCount());
            pstmt.setString(4, guest.getSpouse());
            pstmt.setString(5, guest.getAttendance());
            pstmt.setString(6, guest.getNotes());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            return -1;
        } finally {
            DatabaseConnection.closeResources(null, pstmt, generatedKeys);
            if (conn != null) conn.close();
        }
    }

    /**
     * Получить всех гостей из БД
     */
    public static List<Guest> getAllGuests() throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT GuestID, Name, PhoneNumber, GuestCount, Spouse, Attendance, SubmittedAt, Notes " +
                     "FROM Guests ORDER BY SubmittedAt DESC";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt("GuestID"));
                guest.setName(rs.getString("Name"));
                guest.setPhoneNumber(rs.getString("PhoneNumber"));
                guest.setGuestCount(rs.getInt("GuestCount"));
                guest.setSpouse(rs.getString("Spouse"));
                guest.setAttendance(rs.getString("Attendance"));
                guest.setNotes(rs.getString("Notes"));

                guests.add(guest);
            }

        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }

        return guests;
    }

    /**
     * Получить гостя по ID
     */
    public static Guest getGuestById(int guestId) throws SQLException {
        String sql = "SELECT GuestID, Name, PhoneNumber, GuestCount, Spouse, Attendance, SubmittedAt, Notes " +
                     "FROM Guests WHERE GuestID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, guestId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt("GuestID"));
                guest.setName(rs.getString("Name"));
                guest.setPhoneNumber(rs.getString("PhoneNumber"));
                guest.setGuestCount(rs.getInt("GuestCount"));
                guest.setSpouse(rs.getString("Spouse"));
                guest.setAttendance(rs.getString("Attendance"));
                guest.setNotes(rs.getString("Notes"));
                return guest;
            }

        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }

        return null;
    }

    /**
     * Обновить гостя
     */
    public static boolean updateGuest(Guest guest) throws SQLException {
        String sql = "UPDATE Guests SET Name=?, PhoneNumber=?, GuestCount=?, Spouse=?, Attendance=?, Notes=?, UpdatedAt=GETDATE() " +
                     "WHERE GuestID=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getPhoneNumber());
            pstmt.setInt(3, guest.getGuestCount());
            pstmt.setString(4, guest.getSpouse());
            pstmt.setString(5, guest.getAttendance());
            pstmt.setString(6, guest.getNotes());
            pstmt.setInt(7, guest.getGuestId());

            return pstmt.executeUpdate() > 0;

        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }

    /**
     * Удалить гостя
     */
    public static boolean deleteGuest(int guestId) throws SQLException {
        String sql = "DELETE FROM Guests WHERE GuestID = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, guestId);

            return pstmt.executeUpdate() > 0;

        } finally {
            DatabaseConnection.closeResources(conn, pstmt, null);
        }
    }

    /**
     * Получить статистику гостей
     */
    public static GuestStatistics getStatistics() throws SQLException {
        String sql = "SELECT " +
                     "COUNT(*) as total, " +
                     "SUM(CASE WHEN Attendance='yes' THEN 1 ELSE 0 END) as confirmed, " +
                     "SUM(CASE WHEN Attendance='no' THEN 1 ELSE 0 END) as declined, " +
                     "SUM(CASE WHEN Attendance='pending' THEN 1 ELSE 0 END) as pending, " +
                     "SUM(GuestCount) as totalGuests " +
                     "FROM Guests";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return new GuestStatistics(
                    rs.getInt("total"),
                    rs.getInt("confirmed"),
                    rs.getInt("declined"),
                    rs.getInt("pending"),
                    rs.getInt("totalGuests")
                );
            }

        } finally {
            DatabaseConnection.closeResources(conn, stmt, rs);
        }

        return new GuestStatistics(0, 0, 0, 0, 0);
    }

    /**
     * Получить гостей по статусу
     */
    public static List<Guest> getGuestsByAttendance(String attendance) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT GuestID, Name, PhoneNumber, GuestCount, Spouse, Attendance, SubmittedAt, Notes " +
                     "FROM Guests WHERE Attendance = ? ORDER BY SubmittedAt DESC";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attendance);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Guest guest = new Guest();
                guest.setGuestId(rs.getInt("GuestID"));
                guest.setName(rs.getString("Name"));
                guest.setPhoneNumber(rs.getString("PhoneNumber"));
                guest.setGuestCount(rs.getInt("GuestCount"));
                guest.setSpouse(rs.getString("Spouse"));
                guest.setAttendance(rs.getString("Attendance"));
                guest.setNotes(rs.getString("Notes"));

                guests.add(guest);
            }

        } finally {
            DatabaseConnection.closeResources(conn, pstmt, rs);
        }

        return guests;
    }
}

