/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appestoque.dal;

import java.sql.*;

public class ModuloConexao {

    //conexao com o banco
    public static Connection conector() throws ClassNotFoundException {
        Connection conect;
        // Armazenando infos sobre o banco
        String url = "jdbc:mysql://localhost:3306/dbusers";
        String user = "root";
        String password = "Guga994426";
//Estabelecendo conexao
        try {
            conect = DriverManager.getConnection(url, user, password);
            return conect;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        /*
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        try {
            conect = DriverManager.getConnection(url, user, password);
        conect.createStatement().executeQuery("SELECT * FROM USERS;");            
        
        return conect;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if(conect != null){
            conect.close();
        }
        } 
        
        
        */

    }
}
