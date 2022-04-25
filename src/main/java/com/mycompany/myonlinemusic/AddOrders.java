/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myonlinemusic;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;

/**
 *
 * @author yasminenajd
 */
@WebServlet(name = "AddOrders", urlPatterns = {"/addorders"})
public class AddOrders extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddOrders</title>");
            out.println("</head>");
            out.println("<body>");
            try {
                Context ctx = new InitialContext();
                if (ctx == null) {
                    throw new Exception("Error - No Context");
                }
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/postgres");
                if (ds != null) {
                    Connection conn = ds.getConnection();
                    if (conn != null) {
                        // the insert statement
                        Cookie id = request.getCookies()[0];
                        String qry = "SELECT * FROM Product WHERE ProductCode = ?; ";
                        // create the insert preparedstatement
                        PreparedStatement prepStmt = conn.prepareStatement(qry);
                        String orderId = request.getParameter("orderID");
                        String Pcode = request.getParameter("ProductCode");
                        //prepStmt.setString(1, OrderID);
                        prepStmt.setString(1, Pcode);
                       

                        ResultSet rs = prepStmt.executeQuery();
                        if (rs.next() == false) {
                            out.println("<br> The Product does not exist </br>");// correct balise 
                        } else {
                            String query = "INSERT INTO Orders (orderid, ProductCode, userId ) VALUES (?, ?, ?);";

                            prepStmt = conn.prepareStatement(query);
                            prepStmt.setString(1, orderId);
                            prepStmt.setString(2, Pcode);
                            prepStmt.setInt(3, Integer.parseInt(id.getValue()));
                            prepStmt.execute();
                            query = "Update Product SET quantityonhand = quantityonhand-1 WHERE productCode = ? AND quantityonhand > 0;";
                            prepStmt = conn.prepareStatement(query);
                            prepStmt.setString(1, Pcode);
                            prepStmt.execute();
                            out.println("Your code has been added ");

                        }

                    } // end of try
                }
            } catch (SQLException ex) {
                out.println("SQLException: " + ex);
            } catch (Exception e) {

                out.println("Exception caught");
                out.println(e.toString());
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
