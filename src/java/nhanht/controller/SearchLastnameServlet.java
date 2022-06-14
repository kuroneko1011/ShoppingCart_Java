/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanht.registration.RegistrationDAO;
import nhanht.registration.RegistrationDTO;
import nhanht.utils.MyApplicationConstants;

/**
 *
 * @author Nhan
 */
@WebServlet(name = "SearchLastnameServlet", urlPatterns = {"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws java.sql.SQLException
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String searchValue = request.getParameter("txtSearchValue");
        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        String url = siteMap.getProperty(MyApplicationConstants.SearchFeatures.SEARCH_PAGE);
        try {
            if (searchValue.trim().length() > 0) {
                //call DAO
                RegistrationDAO dao = new RegistrationDAO();
                dao.searchLastname(searchValue);
                List<RegistrationDTO> result = dao.getAccounts();
                request.setAttribute("SEARCHRESULT", result);
            }
        } catch (NamingException ex) {
            log("SearchLastNameServlet _ Naming" + ex.getMessage());
            url = siteMap.getProperty(MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500);
        } catch (SQLException ex) {
            log("SearchLastNameServlet _ SQL" + ex.getMessage());
            url = siteMap.getProperty(MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchLastnameServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchLastnameServlet.class.getName()).log(Level.SEVERE, null, ex);
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
