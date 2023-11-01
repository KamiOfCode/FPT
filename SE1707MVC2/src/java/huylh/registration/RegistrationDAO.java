/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylh.registration;

import huylh.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author LE HUU HUY
 */
public class RegistrationDAO implements Serializable{
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //create sql string
                String sql = "SELECT lastname, isAdmin FROM Registration WHERE username = ? AND password = ?";
                //create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //execute query
                rs = stm.executeQuery();
                //process
                if (rs.next()) {
                    //mapping
                    //5.1 get data from rs
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to properties of DTO
                    result = new RegistrationDTO(username, null, fullname, role);
                }//end username and password are existed
            }//end connection is available
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
        return result;
    }

    List<RegistrationDTO> accounts;

    //nhap getA + ctrl + space
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //create sql string
                String sql = "SELECT username, password, lastname, isAdmin FROM Registration WHERE lastname LIKE ?";
                //create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //execute query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    //5.1 map data
                    //5.1.1 get data from rs
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.1.2 set data into properties of DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    //5.1.3 add DTO into list
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts had not existed
                    this.accounts.add(dto);
                    //5.2 done
                }//end traverse rs
            }//end connection is available
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

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //create sql string
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ?";
                //create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //execute query
                int effectRows = stm.executeUpdate();
                //process
                if (effectRows > 0) {
                    result = true;
                }
            }//end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public RegistrationDTO updateAccount(String username, String password, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //create sql string
                String sql = "UPDATE Registration SET password = ?, isAdmin = ? WHERE username = ?";
                //create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //execute query
                int effectRows = stm.executeUpdate();
                //process
                if (effectRows > 0) {
                    result = new RegistrationDTO(username, password, username, role);
                }
            }//end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean createAccount(RegistrationDTO account) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //create connection
            con = DBHelper.createConnection();
            if (con != null) {
                //create sql string
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //create statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullname());
                stm.setBoolean(4, account.isRole());
                //execute query
                int effectRows = stm.executeUpdate();
                //process
                if (effectRows > 0) {
                    result = true;
                }
            }//end connection is available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
