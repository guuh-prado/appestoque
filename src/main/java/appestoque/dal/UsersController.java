/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appestoque.dal;

import static appestoque.dal.ModuloConexao.conector;
import appestoque.telas.HomeFrame;
import appestoque.telas.RegisterFrame;
import ion.messito.appestoque.Users;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsersController {

    Connection conect;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Users p2;
    EstoqueController e1 = new EstoqueController();

    public void ArmazenaUser(int id, String fullName, String login, String pass, String email, int acessType) {
        Users p1 = new Users(id, fullName, login, pass, email, acessType);
        p2 = p1;
    }
    //----------------------------------------------------------------------------
    //---------------------CONTROLES REGISTER FRAME----------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

    /**
     *
     * @param fullName
     * @param login
     * @param pass
     * @param confirmaPass
     * @param email
     * @throws SQLException
     */
    public void Register(String fullName, String login, String pass, String confirmaPass, String email) throws SQLException {
        String sql = "insert into User(fullName,login,pass,email,acess_type) values (?,?,?,?,?);";
        if (!confirmaPass.equals(pass)) {
            JOptionPane.showMessageDialog(null, "Senhas não coincidem.");
        } else {
            //pegando dados para validar no banco "?" é substituido pelas variaveis
            try {
                conect = conector();
                pst = conect.prepareStatement(sql);
                pst.setString(1, fullName);
                pst.setString(2, login);
                pst.setString(3, pass);
                pst.setString(4, email);
                pst.setString(5, "1");
                //executa a query
                pst.executeUpdate();
                String select = "SELECT * FROM User WHERE login=? AND pass=?";
                pst = conect.prepareStatement(select);
                pst.setString(1, login);
                pst.setString(2, pass);
                rs = pst.executeQuery();
//validação user
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Sucesso ao cadastrar");
                    ArmazenaUser(Integer.parseInt(rs.getString("id")), rs.getString("fullName"), rs.getString("login"), rs.getString("pass"), rs.getString("email"), Integer.parseInt(rs.getString("acess_type")));
                    Home(p2);
                } else {
                    JOptionPane.showMessageDialog(null, "Cadastro com erro");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
            } finally {
                pst.close();
                rs.close();
                conect.close();
            }
        }
    }

    //----------------------------------------------------------------------------
    //---------------------CONTROLES LOGIN FRAME----------------------------------
    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------
    public boolean Logar(String login, String pass) throws SQLException {
        String sql = "SELECT * FROM User WHERE login=? AND pass=?";
        Boolean logado = false;
        try {
            conect = ModuloConexao.conector();
            //pegando dados para validar no banco "?" é substituido pelas variaveis
            pst = conect.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, pass);
            //executa a query

            rs = pst.executeQuery();
            //validação user
            if (rs.next()) {
                ArmazenaUser(Integer.parseInt(rs.getString("id")), rs.getString("fullName"), rs.getString("login"), rs.getString("pass"), rs.getString("email"), Integer.parseInt(rs.getString("acess_type")));
                JOptionPane.showMessageDialog(null, rs.getString("id"));
                logado = true;
                Home(p2);
                return logado;
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não encontrado");
            }
        } catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            conect.close();
            pst.close();
            rs.close();
        }
        return logado;
    }

    public void NewRegisterFrame() {
        RegisterFrame r1 = new RegisterFrame();
        r1.setVisible(true);
    }

    //-----------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------
    //----------------------------AQUI COMEÇA CONTROLES HOME FRAME ----------------------
    //-----------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------
    public void Home(Users u) {
        HomeFrame h1 = new HomeFrame();
        h1.setVisible(true);
        h1.lblNome.setText(u.getFullName());
        h1.lblLogin.setText(u.getLogin());
        h1.lblId.setText(Integer.toString(u.getId()));
        h1.lblEmail.setText(u.getEmail());
        h1.UserLogged(p2);
    }

}
