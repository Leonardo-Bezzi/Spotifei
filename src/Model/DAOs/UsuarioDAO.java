/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

/**
 *
 * @author uniflelias
 */

import Model.Conexao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getSenha());
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario autenticar(String email, String senha) {
    String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ? AND senha = ?";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        
        pst.setString(1, email);
        pst.setString(2, senha);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            // se encontrou, retorna um objeto Usuario
            return new Usuario(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha")
            );
        }
    } catch (SQLException e) {
        System.out.println("Erro na autenticação: " + e.getMessage());
    }
    return null;  // não encontrou ou erro
}

}

