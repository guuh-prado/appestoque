/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appestoque.dal;

import appestoque.telas.EstoqueTela;
import ion.messito.appestoque.Users;
import java.sql.*;
import javax.swing.JOptionPane;

public class EstoqueController {

    Connection conect;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Users user1;

    public void EstoqueFrame(Users u1) throws SQLException {
        user1 = u1;
        EstoqueTela telaEstoque;
        telaEstoque = new EstoqueTela();
        EstoqueSelector(user1, telaEstoque);
        telaEstoque.UserLogged(user1);
        telaEstoque.setVisible(true);
    }

    public void EstoqueSelector(Users u1, EstoqueTela e1) throws SQLException {
        String sql = "SELECT * FROM user u JOIN estoque e WHERE u.id =? AND u.id = e.fk_userid;";
        try {
            conect = ModuloConexao.conector();
            pst = conect.prepareStatement(sql);
            pst.setString(1, Integer.toString(u1.getId()));
            rs = pst.executeQuery();
            while (rs.next()) {
                String item = rs.getString("nome");
                e1.FrameVinculator(item, u1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no seletor de estoques" + e.getMessage());
        } finally {
            conect.close();
        }
    }

    public void NewEstoque(Users u1, EstoqueTela e1) throws SQLException {
        try {
            conect = ModuloConexao.conector();
            String sql = "INSERT INTO estoque(nome,fk_userid) values (?,?);";
            pst = conect.prepareStatement(sql);
            pst.setString(1, e1.txtNewEstoque.getText());
            pst.setString(2, Integer.toString(u1.id));
            pst.executeUpdate();
            e1.cbEstoque.addItem(e1.txtNewEstoque.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            conect.close();
        }
    }
}
