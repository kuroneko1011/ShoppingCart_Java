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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = MyApplicationConstants.ErrorFeatures.INVALID_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPass");
        try {
            RegistrationDAO dao = new RegistrationDAO();
            boolean result = dao.checkLogin(username, password);

            if (result) {
                url = MyApplicationConstants.SearchFeatures.SEARCH_PAGE;
//                Cookie newCookie = new Cookie(username, password);
//                newCookie.setMaxAge(60 * 30);
//                response.addCookie(newCookie);
                //check authorization using session object
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                session.setAttribute("PASSWORD", password);
//                session.setAttribute("ROLE", role);
//                session.setAttribute("FULLNAME", fullname);
            }
        } catch (SQLException ex) {
            log("LoginServlet _ SQL" + ex.getMessage());
            url = MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500;
        } catch (NamingException ex) {
            log("LoginServlet _ Naming" + ex.getMessage());
            url = MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500;
        } finally {
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
