/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.book;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhanht.utils.DBHelper;

/**
 *
 * @author Nhan
 */
public class BookDAO implements Serializable {

    public BookDTO getBook(String bookName)
            throws SQLException, NamingException {
        // 1.Connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        int bookID = 0;
        String name = null;
        int price = 0;
        int availableQuantity = 0;
        BookDTO book = null;
        try {
            // 2.Create SQL String
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select bookID, name, price, availableQuantity "
                        + "FROM BOOK "
                        + "WHERE name = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, bookName);
                // 4.Execute Query
                rs = stm.executeQuery();
                // 5.Process Result
                if (rs.next()) {
                    bookID = rs.getInt("bookID");
                    name = rs.getString("name");
                    price = rs.getInt("price");
                    availableQuantity = rs.getInt("availableQuantity");
                    book = new BookDTO(bookID, name, price, availableQuantity);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return book;
    }
}
