package appestoque.dal;
import java.sql.*;

public class ModuloConexao {
    //conexao com o banco
    public static Connection conector() throws ClassNotFoundException {
        Connection conect;
        // Armazenando infos sobre o banco
        String url = "jdbc:mysql://localhost:3306/dbusers";
        String user = "gustavo";
        String password = "pradogu";
//Estabelecendo conexao
        try {
            conect = DriverManager.getConnection(url, user, password);
            return conect;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}