/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanht.book.BookDTO;
import nhanht.cart.CartObject;
import nhanht.utils.MyApplicationConstants;

/**
 *
 * @author Nhan
 */
@WebServlet(name = "AddBookToCartServlet", urlPatterns = {"/AddBookToCartServlet"})
public class AddBookToCartServlet extends HttpServlet {

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

        String url = MyApplicationConstants.ShoppingFeatures.SHOPPING_PAGE;
        try {
            //1. Cust goes cart place
            HttpSession session = request.getSession();
            //2. Cust takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }
            //3. Cart is available, cust chooses item
            String rawString = request.getParameter("cboItem");
            int parenthesesIndex = rawString.indexOf("(");
            String id = rawString.substring(0, parenthesesIndex).trim();
            
            //Get the book list 
            ArrayList<BookDTO> bookList = (ArrayList<BookDTO>) session.getAttribute("BOOK");
            BookDTO currentBook = null;
            for (BookDTO book : bookList) {
                if (id.trim().equals(book.getName().trim())) {
                    currentBook = book;
                }
            }
            //Check if the available quantity is enough to add
            if (currentBook != null
                    && cart.getItems() != null
                    && cart.getItems().get(id) != null
                    && cart.getItems().get(id) + 1 > currentBook.getAvailableQuantity()) {
                url = "checkOutError";
            } else {
                //4. Cust drops item to cart
                cart.addItemToCart(id);
                session.setAttribute("CART", cart);
            }
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
