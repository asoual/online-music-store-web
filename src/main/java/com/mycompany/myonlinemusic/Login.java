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
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
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

                        String qry = "SELECT Password FROM Users  WHERE UserId = ?";
                        PreparedStatement prepStmt = conn.prepareStatement(qry);
                        String ID = request.getParameter("uname");
                        String Password = request.getParameter("psw");
                        prepStmt.setInt (1, Integer.parseInt(ID));
                        
                        
                        ResultSet rs = prepStmt.executeQuery();

                        ResultSetMetaData rsmd = rs.getMetaData();

                       
                        if (rs.next() == false || !rs.getString("Password").equals(Password)){
                            out.println("<br> Wrong ID or Password </br>");// correct balise 
                        }
                        
                        else{
                            Cookie id = new Cookie("userid", ID);
                            response.addCookie(id);
                            response.sendRedirect("showproducts");
                        }   

                       
                    } // end of try

                }

            } catch (SQLException ex) {

                out.println("SQLException: " + ex);

            } catch (Exception e) {

                //e.printStackTrace();
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
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
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
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
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

