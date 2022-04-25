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
@WebServlet(name = "AddUser", urlPatterns = {"/adduser"})
public class AddUser extends HttpServlet {

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
            out.println("<title>Servlet AddUser</title>");            
            out.println("</head>");
            out.println("<body>");
           try {
                      Context ctx = new InitialContext();
                      if (ctx == null) {
                          throw new Exception("Error - No Context");
                      }
                  DataSource ds= (DataSource)    
                ctx.lookup("java:/comp/env/jdbc/postgres");
                if (ds != null) {
                    Connection conn = ds.getConnection();
                      if (conn != null) {
                        // the insert statement
                String qry = "INSERT INTO Users "
               + " (UserId, UserFName, UserLName, UserAddress, UserEmail, UserPhone, Password)"
                 + " VALUES (?, ?, ?, ?, ?, ?, ?);";
                 // create the insert preparedstatement
            PreparedStatement prepStmt = conn.prepareStatement(qry); 
            String ID = request.getParameter("UserID"); 
            String FName = request.getParameter("UserFName"); 
            String LName = request.getParameter("UserLName");
            String Address = request.getParameter("UserAddress");
            String Email = request.getParameter("UserEmail");
            String Phone = request.getParameter("UserPhone");
            String Pwd = request.getParameter("Password");
            
            prepStmt.setInt (1, Integer.parseInt(ID));
            prepStmt.setString (2, FName);
            prepStmt.setString (3, LName);
            prepStmt.setString (4, Address);
            prepStmt.setString (5, Email);
            prepStmt.setString (6, Phone);
            prepStmt.setString (7, Pwd);
            prepStmt.execute();
            out.println("Welcome to our website ");
            } // end of try
            } 
           }
            catch (SQLException ex) {
                out.println("SQLException: " + ex);
            }
            catch (Exception e) {

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
