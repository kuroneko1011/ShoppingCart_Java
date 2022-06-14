
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanht.registration.RegistrationDAO;
import nhanht.utils.MyApplicationConstants;

/**
 *
 * @author Nhan
 */
@WebServlet(name = "StartUpServlet", urlPatterns = {"/StartUpServlet"})
public class StartUpServlet extends HttpServlet {

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
        String url = MyApplicationConstants.DispatchFeatures.LOGIN_PAGE;
        try {
            HttpSession session = request.getSession();
            
            // create a new cart when start up     
            //get username and password which are stored in session
            String username = (String) session.getAttribute("USERNAME");
            String password = (String) session.getAttribute("PASSWORD");
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    String username = cookie.getName();
//                    System.out.println(username);
//                    String password = cookie.getValue();
//                    System.out.println(password);
            //2. checkLogin
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);
            if (result) {
                url = MyApplicationConstants.SearchFeatures.SEARCH_PAGE;
            }
        } catch (NamingException ex) {
            log("StartUpServlet _ Naming" + ex.getMessage());
            url = MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500;
        } catch (SQLException ex) {
            log("StartUpServlet _ SQL" + ex.getMessage());
            url = MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500;
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
