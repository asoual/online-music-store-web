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
@WebServlet(name = "Orders", urlPatterns = {"/orders"})
public class Orders extends HttpServlet {

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
             String outpage = "<html>\n"
                    + "    <head>\n"
                    + "        <title>Catalog</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n"
                    + "        <link rel=\"stylesheet\" type=\"text/css\" href=\"project.css\">\n"
                    +"         <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    </head>\n"
                    + "    <body style= \"background-color:#FFFAF0\">\n"
                    + "        <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\" >\n"
                    + "\n"
                    + "            <a class=\"navbar-brand\" href=\"#\" id=\"Music\"> <svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-music-note-list\" viewBox=\"0 0 16 16\">\n"
                    + "                <path d=\"M12 13c0 1.105-1.12 2-2.5 2S7 14.105 7 13s1.12-2 2.5-2 2.5.895 2.5 2z\"/>\n"
                    + "                <path fill-rule=\"evenodd\" d=\"M12 3v10h-1V3h1z\"/>\n"
                    + "                <path d=\"M11 2.82a1 1 0 0 1 .804-.98l3-.6A1 1 0 0 1 16 2.22V4l-5 1V2.82z\"/>\n"
                    + "                <path fill-rule=\"evenodd\" d=\"M0 11.5a.5.5 0 0 1 .5-.5H4a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 .5 7H8a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 .5 3H8a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5z\"/>\n"
                    + "                </svg>My Online Music Store</a>\n"
                    + "            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n"
                    + "                <span class=\"navbar-toggler-icon\"></span>\n"
                    + "            </button>\n"
                    + "\n"
                    + "            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n"
                    + "                <ul class=\"navbar-nav mr-auto\">\n"
                    + "                    <li class=\"nav-item active\">\n"
                    + "                        <a class=\"nav-link\" href=\"showproducts\"> Home </a>\n"
                    + "                    </li>\n"
                    + "                    <li class=\"nav-item\">\n"
                    + "                        <a class=\"nav-link\" href=\"orders\"> cart <span class=\"sr-only\"></span></a>\n"
                    + "                    </li>\n"
                    + "                    <li class=\"nav-item dropdown\">\n"
                    + "                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                    + "                            Dropdown\n"
                    + "                        </a>\n"
                    + "                        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n"
              
                    + "                            <div class=\"dropdown-divider\"></div>\n"
                    + "                            <a class=\"dropdown-item\" href=\"index.html\">logout</a>\n"
                    + "                        </div>\n"
                    + "                    </li>\n"
                    + "\n"
                    + "                </ul>\n"
                    + "                <form class=\"form-inline my-2 my-lg-0\">\n"
                    + "                    <input class=\"form-control mr-sm-2\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n"
                    + "                    <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">Search</button>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "        </nav> \n"
                    + "        <div class=\"t\">\n"
                    + "            <center>\n"
                    + "                <br>\n"
                    + "                <h1><FONT color=\"Black\" size=\"36pt\"  > Your Orders: </h1></FONT>\n"
                    + "            </center>\n"
                    + "        </div>\n"
                    + "        <br>";
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

                        Statement stmt = conn.createStatement();
                        Cookie id = request.getCookies()[0];
                        String qry = "SELECT orderid, Orders.productCode, pname, pprice FROM Orders Join Product\n"
                                + "ON Orders.productcode= Product.productcode WHERE UserId = ?;";
                        PreparedStatement prepStmt = conn.prepareStatement(qry);

                        prepStmt.setInt(1, Integer.parseInt(id.getValue()));
                        ResultSet rs = prepStmt.executeQuery();

                        outpage += "<table border =\"1\" class=\"table table-bordered table-dark\">";
                        outpage += "<tr><th>OrderId</th > <br> <th>ProductCode</th > <br> <th>ProductName</th > <br> <th>ProductPrice</th ></tr>";
                        while (rs.next()) {
                            outpage += "<tr>";
                            outpage += "<td>" + rs.getString("orderid") + "</td>";
                            outpage += "<td>" + rs.getString("productcode") + "</td>";
                            outpage += "<td>" + rs.getString("pname") + "</td>";
                            outpage += "<td>" + rs.getFloat("pprice") + "</td>";
                            outpage += "</tr>\n";
                        }
                        conn.close();
                        out.println("</table>");
                    } else {
                        out.println("Error: conn is null ");
                    }
                }
            } catch (Exception e) {
                //e.printStackTrace();
                out.println("Exception caught");
                out.println(e.toString());
            }
            outpage += "</body>\n"
                    + "</html>";
            out.println(outpage);
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
