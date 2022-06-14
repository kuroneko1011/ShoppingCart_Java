/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhanht.book.BookDAO;
import nhanht.book.BookDTO;
import nhanht.cart.CartObject;
import nhanht.orderdetail.OrderDetailDAO;
import nhanht.orderdetail.OrderDetailDTO;
import nhanht.utils.MyApplicationConstants;

/**
 *
 * @author Nhan
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITEMAP");
        String url = siteMap.getProperty(MyApplicationConstants.ShoppingFeatures.SHOPPING_PAGE);

        try {
            HttpSession session = request.getSession();
            String currentUser = (String) session.getAttribute("USERNAME");

            //Get book list from session
            ArrayList<BookDTO> books = (ArrayList<BookDTO>) session.getAttribute("BOOK");

            //Create cart to retrieve bookList data
            CartObject cart = (CartObject) session.getAttribute("CART");
//            if (cart == null) {
//                cart = new CartObject();
//            }
            Map<String, Integer> items = cart.getItems();
            Map<String, Integer> bookList = cart.getBookList();
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            boolean updateResult = false;
//            OrderDAO orderDAO = new OrderDAO();
//            ArrayList<OrderDetailsDTO> orderDetails = new ArrayList<OrderDetailsDTO>();

            for (Map.Entry<String, Integer> item : items.entrySet()) {
                //Create order detail
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(currentUser, item.getKey(),
                        bookList.get(item.getKey()), item.getValue(),
                        bookList.get(item.getKey()) * item.getValue());

                orderDetailDAO.addOrderDetail(orderDetailDTO);
                BookDTO currentBook = null;
                //get the specific book with given name
                for (BookDTO book : books) {
                    if (item.getKey().trim().equals(book.getName().trim())) {
                        currentBook = book;
                    }
                }
//                orderDAO.addOrder(orderDTO);
                //update Database after check out
                updateResult = orderDetailDAO.updateStorage(currentBook, item.getValue());
            }

//            url = siteMap.getProperty(MyApplicationConstants.ShoppingFeatures.SHOPPING_PAGE);
            // after success checkout, remove CART from session
            session.removeAttribute("CART");

            //get new Book list from updated database
            BookDAO bookDAO = new BookDAO();
            ArrayList<BookDTO> newBooks = new ArrayList<BookDTO>();
            for (Map.Entry<String, Integer> book : bookList.entrySet()) {
                String name = book.getKey();
                BookDTO bookDTO = bookDAO.getBook(name);
                newBooks.add(bookDTO);
            }
//
//            ArrayList<BookDTO> currentBookList = (ArrayList<BookDTO>) session.getAttribute("BOOK");

            //updated bookList in session from updated database
            session.setAttribute("BOOK", newBooks);
            if (updateResult) {
                String successfullMesg = "Purchase successfully!";
                request.setAttribute("SUCCESSFULL", successfullMesg);
            } else {
                String failedMesg = "Purchase failed!!! Insufficient product!";
                request.setAttribute("FAILED", failedMesg);
            }
        } catch (SQLException ex) {
            log("CheckoutServlet _ SQL" + ex.getMessage());
            url = siteMap.getProperty(MyApplicationConstants.ErrorFeatures.ERROR_PAGE_500);
        } catch (NamingException ex) {
            log("CheckoutServlet _ Naming" + ex.getMessage());
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
