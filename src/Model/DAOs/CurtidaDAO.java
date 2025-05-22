/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import Model.Conexao;
import java.sql.*;

/**
 * Classe responsável por manipular as curtidas no banco de dados.
 * Contém métodos para curtir, descurtir e verificar curtidas de músicas por usuários.
 * 
 * @author Leonardo Bezzi Elias
 */

public class CurtidaDAO {

    /**
     * Realiza a curtida de uma música por um usuário.
     * Se a curtida já existir, atualiza para true.
     * Também registra essa ação no histórico de curtidas.
     * 
     * @param idUsuario Identificador do usuário que está curtindo
     * @param idMusica Identificador da música a ser curtida
     * @return true se a operação foi bem sucedida, false caso contrário
     */
    public boolean curtirMusica(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, true) "
                   + "ON CONFLICT (id_usuario, id_musica) DO UPDATE SET curtida = true";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            boolean sucesso = pst.executeUpdate() > 0;
            if (sucesso) {
                Controller.HistoricoController.registrarCurtida(idUsuario, idMusica, true);
            }
            return sucesso;
        } catch (SQLException e) {
            System.out.println("Erro ao curtir música: " + e.getMessage());
            return false;
        }
    }

    /**
     * Realiza a descurtida de uma música por um usuário.
     * Atualiza a tabela de curtidas para false.
     * Também registra essa ação no histórico de curtidas.
     * 
     * @param idUsuario Identificador do usuário que está descurtindo
     * @param idMusica Identificador da música a ser descurtida
     * @return true se a operação foi bem sucedida, false caso contrário
     */
    public boolean descurtirMusica(int idUsuario, int idMusica) {
        String sql = "UPDATE curtidas SET curtida = false WHERE id_usuario = ? AND id_musica = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            boolean sucesso = pst.executeUpdate() > 0;
            if (sucesso) {
                Controller.HistoricoController.registrarCurtida(idUsuario, idMusica, false);
            }
            return sucesso;
        } catch (SQLException e) {
            System.out.println("Erro ao descurtir música: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica se uma música está curtida por um usuário.
     * 
     * @param idUsuario Identificador do usuário
     * @param idMusica Identificador da música
     * @return true se a música estiver curtida pelo usuário, false caso contrário
     */
    public boolean verificaCurtida(int idUsuario, int idMusica) {
        String sql = "SELECT curtida FROM curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getBoolean("curtida");
        } catch (SQLException e) {
            System.out.println("Erro ao verificar curtida: " + e.getMessage());
            return false;
        }
    }
}
