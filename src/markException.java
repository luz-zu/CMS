/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
public class markException extends RuntimeException {
    static int val;
    PreparedStatement prep_statement;
    ResultSet execute_query;

    public markException(int val) {
        super("The marking should lie from 0-100."); //super keyword to access method of class RuntimeException
        this.val = val;    
    }
    
    public Connection checkConnection() {
        db database = new db();
        Connection conn = database.checkConnection();
        return conn;
    }
    
    void printException(String result1, String result2, String result3){
         try {
            if (val < 0) {
                throw new markException(val);
            }
            try {
                Connection conn = checkConnection();
                prep_statement = conn.prepareStatement("Update enrolled_module SET Marks = '"+result1+"' where Student_Id= '"+result2+"' and Module_Id = '"+result3+"' ");
                int row = prep_statement.executeUpdate();
                if (row != 1) {
                    JOptionPane.showMessageDialog(null, "ERROR");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Result Updated!");
            } catch (Exception exp) {
                JOptionPane.showMessageDialog(null, exp);
            }
            
        } catch (markException e) { //declaring exception type
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        
    }
    
    public static void main(String[] args) {
        
       
    }
    
}
