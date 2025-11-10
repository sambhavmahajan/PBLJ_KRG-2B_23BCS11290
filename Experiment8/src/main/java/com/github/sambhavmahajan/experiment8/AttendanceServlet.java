package com.github.sambhavmahajan.experiment8;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {

    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO Attendance(StudentID, Date, Status) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);

            int rows = ps.executeUpdate();

            if (rows > 0)
                request.setAttribute("message", "Attendance recorded successfully for Student ID: " + studentId);
            else
                request.setAttribute("message", "Error saving attendance!");

            con.close();


        } catch (Exception e) {
            request.setAttribute("message", "Database Error: " + e.getMessage());
        }

        RequestDispatcher rd = request.getRequestDispatcher("attendance.jsp");
        rd.forward(request, response);
    }
}
