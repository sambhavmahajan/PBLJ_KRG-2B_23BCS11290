package com.github.sambhavmahajan.experiment8;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String empId = request.getParameter("empId");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement ps;
            String sql;

            if (empId != null && !empId.isEmpty()) {
                sql = "SELECT * FROM Employee WHERE EmpID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(empId));
            } else {
                sql = "SELECT * FROM Employee";
                ps = con.prepareStatement(sql);
            }

            ResultSet rs = ps.executeQuery();
            out.println("<h3>Employee List:</h3>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>"
                        + rs.getString("Name") + "</td><td>"
                        + rs.getString("Department") + "</td></tr>");
            }
            out.println("</table>");
            con.close();

        } catch (Exception e) {
            out.println("<h4>Error: " + e.getMessage() + "</h4>");
        }
    }
}

