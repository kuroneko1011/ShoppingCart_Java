/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhanht.registration.RegistrationCreateError;
import nhanht.registration.RegistrationDAO;
import nhanht.registration.RegistrationDTO;
import nhanht.utils.MyApplicationConstants;

/**
 *
 * @author Nhan
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

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

        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErrs = false;

        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        String url = siteMap.getProperty(MyApplicationConstants.ErrorFeatures.CREATE_ACCOUNT_ERROR_PAGE);

        try {
            //1. Check all user's errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundErrs = true;
                errors.setUsernameLengthErr("Username is required with 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErrs = true;
                errors.setPasswordLengthErr("Password is required with 6 to 30 characters");
            } else if (!((password.trim()).equals(confirm.trim()))) {
                foundErrs = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErrs = true;
                errors.setFullNameLengthErr("Full name is required with 2 to 50 characters");
            }
            //2. process
            if (foundErrs) {
                //2.1 forward errors to user
                request.setAttribute("CREATEERRORS", errors);
            } else {
                //2.2 Insert DB --> call DAO
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createNewAccount(dto);
                if (result) {
                    url = siteMap.getProperty(MyApplicationConstants.DispatchFeatures.LOGIN_PAGE);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateAccountServlet _ SQL " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATEERRORS", errors);
            }
        } catch (NamingException ex) {
            log("CreateAccountServlet _ Naming" + ex.getMessage());
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
