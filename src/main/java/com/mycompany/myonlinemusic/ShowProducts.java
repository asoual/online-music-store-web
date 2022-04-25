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
@WebServlet(name = "ShowProducts", urlPatterns = {"/showproducts"})
public class ShowProducts extends HttpServlet {

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
 /* TODO output your page here. You may use following sample code. */
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
                    + "                        <a class=\"nav-link\" href=\"#\"> Home </a>\n"
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
                    + "                <form class=\"form-inline my-2 my-lg-0\" \" action=\"searchproducts\">\n"
                    + "                    <input class=\"form-control mr-sm-2\" name = \"Recherche\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\">\n"
                    + "                    <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">Search</button>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "        </nav> \n"
                    + "        <style>\n"
                    + "            .t{\n"
                    + "                font-family:cursive;\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "        <div class=\"t\">\n"
                    + "<br>\n"
                    + "            <h1><FONT color=\"SaddleBrown\" size=\"60pt\" > Available Products </h1></FONT>\n"
                    + "        </div>\n"
                    + "        <style>\n"
                    + "            .gallery {\n"
                    + "                margin: 100px;\n"
                    + "                border: 2px solid #ccc;\n"
                    + "                float: left;\n"
                    + "                width: 180px;\n"
                    + "                background-color: white;\n"
                    + "            }\n"
                    + "\n"
                    + "            .gallery:hover {\n"
                    + "                border: 1px solid #777;\n"
                    + "                color: gray;\n"
                    + "                border-color: black;\n"
                    + "                box-shadow: 0 0.5em 0.5em -0.4em;\n"
                    + "                transform: translateY(1em);\n"
                    + "            }\n"
                    + "\n"
                    + "            .gallery img {\n"
                    + "                width: 345px;\n"
                    + "                height: 250px;\n"
                    + "                background-color: white;\n"
                    + "                border-bottom: 4px solid #e1f1ff;\n"
                    + "            }\n"
                    + "\n"
                    + "            .desc {\n"
                    + "                padding: 15px;\n"
                    + "                text-align: center;\n"
                    + "                background-color: white;\n"
                    + "                width: 345px;\n"
                    + "                height: 200px;\n"
                    + "            }\n"
                    + "\n"
                    + "            #imagee{\n"
                    + "                width: 200px;\n"
                    + "                height: 400px;\n"
                    + "                margin: 50px;\n"
                    + "                background-color: white;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "<style>\n"
                    + "\n"
                    + ".cart{\n"
                    + "background: #fdbb2d;\n"
                    + "border-radius: 10px;\n"
                    + "color: black;\n"
                    + "font-size: 15px;\n"
                    + "font-weight: bolder;\n"
                    + "border: 2px solid black;\n"
                    + "}\n"
                    + ".cart i{\n"
                    + "position: absolute;\n"
                    + "margin-left: 90px;\n"
                    + "column-fill: transparent;\n"
                    + "transition: 400ms;\n"
                    + "color: transparent;\n"
                    + "}\n"
                    + ".cart span{\n"
                    + "transition: 400ms;\n"
                    + "}\n"
                    + ".cart:hover span{\n"
                    + "margin-right: 30px;\n"
                    + "}\n"
                    + ".cart:hover i{\n"
                    + "margin-left: 45px;\n"
                    + "column-fill: white;\n"
                    + "color: black;\n"
                    + "}\n"
                    + "/*button:focus{\n"
                    + "outline: none;\n"
                    + "}*/\n"
                    + "</style>\n"
                    + "\n"
                    + " <button class=\"cart\">\n"
                    + "<a href =\"AddOrders.html\"> Add to Cart <span ></span></a>\n"
                    + "<i class=\"fa fa-shopping-basket\" aria-hidden=\"true\"></i>\n"
                    + "</button>"
                    + "\n";
            try {

                Context ctx = new InitialContext();

                if (ctx == null) {

                    throw new Exception("Error - No Context");

                }
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/postgres");
                if (ds != null) {

                    Connection conn = ds.getConnection();

                    if (conn != null) {
                        Statement stmt = conn.createStatement();
                        ResultSet rst = stmt.executeQuery("SELECT * FROM product;");

                        while (rst.next()) {
                            outpage += "<div class=\"gallery\" id=\"" + rst.getString("ProductCode") + "\">\n"
                                    + "            <a target=\"_blank\" href=\"" + rst.getString("Images") + "\">\n"
                                    + "                <img src=\"" + rst.getString("Images") + "\" alt=\"" + rst.getString("pname") + "\" width=\"600\" height=\"400\">\n"
                                    + "            </a>\n"
                                    + "            <div class=\"desc\">"
                                    + "                 <h4>" + rst.getString("pname") + "</h4>"
                                    + "                  Color: " + rst.getString("pcolor") + "<br> Price: " + rst.getString("pprice") + "\n <br> Qty: " + rst.getString("quantityonhand")
                                    + "              </div>\n"
                                    + "        </div>";

                        }
                    }
                }
            } catch (Exception ex) {
                outpage += ex.toString();
            }

            outpage += " <br><br><br>\n"
                    + "        <!-- test -->\n"
                    + "        <style >\n"
                    + "            .prev {\n"
                    + "                text-decoration: none;\n"
                    + "                display: inline-block;\n"
                    + "                padding: 1px 14px;\n"
                    + "                margin-left: 82%;\n"
                    + "               \n"
                    + "            }\n"
                    + "            .next {\n"
                    + "                text-decoration: none;\n"
                    + "                display: inline-block;\n"
                    + "                padding: 1px 10px;\n"
                    + "                margin-left: 0.001%;\n"
                    + "            }\n"
                    + "\n"
                    + "            .prev:hover {\n"
                    + "                background-color: #ddd;\n"
                    + "                color: black;\n"
                    + "            }\n"
                    + "            .next:hover {\n"
                    + "                background-color: #ddd;\n"
                    + "                color: black;\n"
                    + "            }\n"
                    + "\n"
                    + "            .previous {\n"
                    + "                background-color: none;\n"
                    + "                color: black;\n"
                    + "            }\n"
                    + "\n"
                    + "            .next {\n"
                    + "                background-color: none;\n"
                    + "                color: black;\n"
                    + "            }\n"
                    + "\n"
                    + "            .round {\n"
                    + "                border-radius: 50%;\n"
                    + "            }\n"
                    + "           \n"
                    + "        </style>\n"
                    + "        \n"
                    + "            <div class=\"prev\">\n"
                    + "                <a href=\"#\" class=\"previous\">&laquo; Previous</a>\n"
                    + "            </div>\n"
                    + "            <div class=\"next\">\n"
                    + "                <a href=\"#\" class=\"next\">Next&raquo;</a>\n"
                    + "            </div>\n"
                    + "        \n"
                    + "        <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n"
                    + "        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n"
                    + "        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n"
                    + "\n"
                    + "    </body>\n"
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
