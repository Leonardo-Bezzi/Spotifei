/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Conexao;
import Model.CurtidaInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gamer
 */
public class HistoricoController {
    
    public static void registrarBusca(int idUsuario, String termoBusca) {
        String sql = "INSERT INTO historico_buscas (id_usuario, termo_busca) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, termoBusca);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void registrarCurtida(int idUsuario, int idMusica, boolean curtida) {
        String sql = "INSERT INTO historico_curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, ?)";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.setBoolean(3, curtida);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static List<CurtidaInfo> getHistoricoCurtidas(int idUsuario) {
    List<CurtidaInfo> historicoCurtidas = new ArrayList<>();
        String sql = "SELECT m.nome, c.curtida FROM historico_curtidas c JOIN musicas m ON c.id_musica = m.id WHERE c.id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nomeMusica = rs.getString("nome");  // <--- aqui está o ajuste
                boolean curtida = rs.getBoolean("curtida");
                historicoCurtidas.add(new CurtidaInfo(nomeMusica, curtida));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicoCurtidas;
    }


    public static List<String> getHistoricoBuscas(int idUsuario) {
        List<String> historicoBuscas = new ArrayList<>();
        String sql = "SELECT termo_busca FROM historico_buscas WHERE id_usuario = ?";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                historicoBuscas.add(rs.getString("termo_busca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicoBuscas;
    }
    
    
    
}
