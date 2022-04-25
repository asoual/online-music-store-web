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
@WebServlet(name = "SearchProducts", urlPatterns = {"/searchproducts"})
public class SearchProducts extends HttpServlet {

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
            out.println("<title>Servlet SearchProducts</title>");
            out.println("</head>");
            out.println("<body>");
            String ProductName = request.getParameter("Recherche");
            try {
                Context ctx = new InitialContext();
                if (ctx == null) {
                    throw new Exception("Error - No Context");
                }
                // /jdbc/postgres is the name of the resource incontext.xml 
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/postgres");
                if (ds != null) {
                    Connection conn = ds.getConnection();
                    if (conn != null) {

                        String qry = "SELECT Productcode, pname, pprice, quantityonhand,pcolor FROM Product  WHERE pname LIKE ? ;";
                        PreparedStatement prepStmt = conn.prepareStatement(qry);
                        prepStmt.setString(1, "%"+ProductName+"%");
                        
// Result set get the result of the SQL query
                        ResultSet rs = prepStmt.executeQuery();
                        
                        
                        out.println("<table border=1 width=50% height=50%>");

                        out.println("<tr><th>Product code</th><th>Product name</th><th>Price</th > <th>Quantity</th ><th>Color</th ><tr>");
                        while (rs.next()) {
                            String n = rs.getString("ProductCode");
                            String nm = rs.getString("pname");
                            String nmm = rs.getString("pprice");
                            int nmmmm = rs.getInt("quantityonhand");
                            String nn = rs.getString("pcolor");

                            
                            out.println("<tr><td>" + n + "</td><td>" + nm
                                    + "</td><td>" + nmm + "</td><td>" + nmmmm + "</td><td>" + nn + "</td> </tr>");
                        }
                        out.println("</table>");
//stmt.execute();
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
