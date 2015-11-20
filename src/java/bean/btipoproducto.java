/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import report.dbManager;
import static report.dbManager.OpenConection;

/**
 *
 * @author An
 */
public class btipoproducto extends dbManager{
      static private PreparedStatement pst = null;

    public static ResultSet getCliente() {
        ResultSet rs = null;
        try {
            String sql = "Select * from ctgtipoproducto";
            pst = OpenConection().prepareStatement(sql);
            rs = pst.executeQuery();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return rs;
    }
    
}
