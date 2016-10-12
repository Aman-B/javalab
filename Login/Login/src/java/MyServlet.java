/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author student
 */
public class MyServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        try{
            
            pw.println("Welcome to my webpage!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            pw.close();
        }
    
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
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
        
       String uname=request.getParameter("username");
       String pwd= request.getParameter("password");
        // JDBC driver name and database URL
      String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      String DB_URL="jdbc:mysql://localhost/studentdb";

      //  Database credentials
      String USER = "root";
      String PASS = "password";
      
    
       
       if(pwd.equals("admin")&&uname.equals("admin"))
       {
           out.println("Welcome, "+ uname+"!");
           out.println("<br>");
           out.println("<br>");
           out.println("<br>");
           
          try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
          System.out.println("Success");
          Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("Select * from Student");
                    out.println("<table>");
                    while (rs.next()) {
                         out.println("<tr><td>"+rs.getString("Roll")+"</td><td>"+rs.getString("Name")+"</td><td>"+rs.getString("Branch")+"</td></tr>");
                    }
           
           
        out.println("<form action=\"http://localhost:8084/Login/\">\n" +
"    <input type=\"submit\" value=\"Logout\" />\n" +
"</form>\n");
          }
          catch(Exception e)
          {
              e.printStackTrace();
              
          }
           
       }
       else{
           out.println("Invalid username or password.");
           out.println("<br>");
           out.println("<br>");
           out.println("<br>");
           
           out.println("<form action=\"http://localhost:8084/Login/\">\n" +
"    <input type=\"submit\" value=\"Back\" />\n" +
"</form>\n");

       }
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
