/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.prj301.shopping;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.prj301.utils.DBUtils;

/**
 *
 * @author hd
 */
public class DAO implements Serializable{
    List<Products> accounts;

    public List<Products> getAccounts() {
        return accounts;
    }
    
    public void searchLastname() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //create connection
            con = DBUtils.getConnection();
            if (con != null) {
                //create sql string
                String sql = "SELECT productID, productName, description, price, status FROM tblProducts";
                //create statement obj
                stm = con.prepareStatement(sql);
                //execute query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    //5.1 map data
                    //5.1.1 get data from rs
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    boolean role = rs.getBoolean("status");
                    //5.1.2 set data into properties of DTO
                    Products dto = new Products(productID, productName, description, price, role);
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
}
