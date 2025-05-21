/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import Model.Conexao;
import java.sql.*;

/**
 *
 * @author uniflelias
 */

public class CurtidaDAO {
    public boolean curtirMusica(int idUsuario, int idMusica) { //Funcao que reliza a curtida manipulando a tabela sql
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

    public boolean descurtirMusica(int idUsuario, int idMusica) { //Funcao que reliza a descurtida manipulando a tabela sql
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

    public boolean verificaCurtida(int idUsuario, int idMusica) { //Verifica as curtidas para uso
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
