/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhanht.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhanht.utils.DBHelper;

/**
 *
 * @author Nhan
 */
public class RegistrationDAO implements Serializable {

    private List<RegistrationDTO> accounts;

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        // 1.Connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 2.Create SQL String
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select username "
                        + "FROM REGISTRATION "
                        + "WHERE username = ? AND password = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // 4.Execute Query
                rs = stm.executeQuery();
                // 5.Process Result
                if (rs.next()) {
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

    public boolean getRole(String username)
            throws SQLException, NamingException {
        // 1.Connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        boolean role = false;
        try {
            // 2.Create SQL String
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select isAdmin "
                        + "FROM REGISTRATION "
                        + "WHERE username = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                // 4.Execute Query
                rs = stm.executeQuery();
                // 5.Process Result
                if (rs.next()) {
                    role = rs.getBoolean("isAdmin");
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
        return role;
    }

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 2.Create SQL String
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "FROM REGISTRATION "
                        + "WHERE lastname Like ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 4.Execute Query
                rs = stm.executeQuery();
                // 5.Process Result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    } //end accounts are not existed
                    this.accounts.add(dto);
                } //end traverse ResultSet
            }//process when connection is existed

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

    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {
                // 2.Create SQL String
                String sql = "Delete From REGISTRATION "
                        + "Where username = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute Query
                int effectRows = stm.executeUpdate();
                System.out.println("Effect" + effectRows);
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean updateAccount(String username, String password,
            boolean checkAdmin)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "UPDATE REGISTRATION "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, checkAdmin);
                stm.setString(3, username);
                //4.Execute Query
                int effectRows = stm.executeUpdate();
                System.out.println("Effect" + effectRows);
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    public boolean createNewAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        if (dto == null) {
            return false; //end dto
        }
        Connection con = null;
        PreparedStatement stm = null;

        try {

            con = DBHelper.makeConnection();
            // 2.Create SQL String
            if (con != null) {
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values(?, ?, ?, ?)";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                // 4.Execute Query
                int effectRows = stm.executeUpdate();
                // 5.Process Result
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkExistedAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "SELECT from REGISTRATION "
                        + "WHERE username = ?";
                // 3.Create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4.Execute Query
                rs = stm.executeQuery();
                if (rs.next()) {
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
