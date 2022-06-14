/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.orderdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhanht.book.BookDTO;
import nhanht.utils.DBHelper;

/**
 *
 * @author Nhan
 */
public class OrderDetailDAO implements Serializable {

    public void addOrderDetail(OrderDetailDTO dto)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            int bookID = 0;
            con = DBHelper.makeConnection();
            // 2.Create SQL String
            if (con != null) {
                //get bookID from book name
                String bookSQL = "SELECT bookID from BOOK "
                        + "WHERE name = ?";
                stm = con.prepareStatement(bookSQL);
                stm.setString(1, dto.getItem());
                rs = stm.executeQuery();
                if (rs.next()) {
                    bookID = rs.getInt("bookID");
                }

                String sql = "Insert Into ORDERDETAIL("
                        + "username, bookID, quantity, total"
                        + ") Values(?, ?, ?, ?)";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setInt(2, bookID);
                stm.setInt(3, dto.getQuantity());
                stm.setInt(4, dto.getTotal());

                // 4.Execute Query
                int effectRows = stm.executeUpdate();
                // 5.Process Result
                if (effectRows > 0) {

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
    }

    public boolean updateStorage(BookDTO book, int buyQuantity)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            // 2.Create SQL String
            if (con != null) {
                //get available quantity
                int availableQuantity = 0;
                String getAvailableQuantitySQL = "SELECT availableQuantity "
                        + "FROM BOOK "
                        + "WHERE name = ?";
                stm = con.prepareStatement(getAvailableQuantitySQL);
                stm.setString(1, book.getName());
                rs = stm.executeQuery();
                if (rs.next()) {
                    availableQuantity = rs.getInt("availableQuantity");
                }

                //check if buyQuanity is exceeed available quantity 
                String bookSQL = "Update BOOK "
                        + "SET availableQuantity = ? "
                        + "WHERE name = ? AND availableQuantity >= ? ";
                stm = con.prepareStatement(bookSQL);
                stm.setInt(1, availableQuantity - buyQuantity);
                stm.setString(2, book.getName());
                stm.setInt(3, buyQuantity);
                int effectRows = stm.executeUpdate();
                // 5.Process Result
                if (effectRows > 0) {
                    return true;
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
        return false;
    }
}
