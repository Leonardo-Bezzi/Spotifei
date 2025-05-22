/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import Model.Conexao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe DAO responsável pelas operações no banco de dados relacionadas ao usuário.
 * Inclui métodos para cadastro e autenticação de usuários.
 * 
 * Os métodos utilizam a classe Conexao para obter a conexão com o banco.
 * 
 * @author Leonardo Bezzi Elias
 */
public class UsuarioDAO {

    /**
     * Cadastra um novo usuário no banco de dados.
     * 
     * @param usuario Objeto {@link Usuario} contendo nome, email e senha
     * @return true se o usuário foi cadastrado com sucesso, false caso contrário
     */
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

    /**
     * Autentica um usuário com base em seu email e senha.
     * 
     * @param email Email do usuário
     * @param senha Senha do usuário
     * @return Objeto {@link Usuario} se a autenticação for bem-sucedida, ou null caso contrário
     */
    public Usuario autenticar(String email, String senha) {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
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

        return null;
    }
}


