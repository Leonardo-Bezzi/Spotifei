/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gamer
 */
public class HistoricoDAO {

    private Connection conn;

    public HistoricoDAO(Connection conn) {
        this.conn = conn;
    }

    public void registrarBusca(int idUsuario, String termoBusca) throws SQLException { //Registra a buca
        String sqlInsert = "INSERT INTO historico_buscas (id_usuario, termo_busca) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
            ps.setInt(1, idUsuario);
            ps.setString(2, termoBusca);
            ps.executeUpdate();
        }
    }


    public List<String> listarUltimasBuscas(int idUsuario) throws SQLException { //Pega o historico recente de buscas
        List<String> buscas = new ArrayList<>();
        String sql = "SELECT termo_busca FROM historico_buscas WHERE id_usuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                buscas.add(rs.getString("termo_busca"));
            }
        }
        return buscas;
    }



    public void registrarCurtida(int idUsuario, int idMusica, boolean curtida) throws SQLException { //Registra a curtida
        String sqlSelect = "SELECT id FROM historico_curtidas WHERE id_usuario = ? AND id_musica = ?";
        try (PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {
            psSelect.setInt(1, idUsuario);
            psSelect.setInt(2, idMusica);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
            int idRegistro = rs.getInt("id");
            String sqlUpdate = "UPDATE historico_curtidas SET curtida = ? WHERE id = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                psUpdate.setBoolean(1, curtida);
                psUpdate.setInt(2, idRegistro);
                psUpdate.executeUpdate();
            }
        } else {
            String sqlInsert = "INSERT INTO historico_curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, ?)";
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setInt(1, idUsuario);
                psInsert.setInt(2, idMusica);
                psInsert.setBoolean(3, curtida);
                psInsert.executeUpdate();
            }
        }

        }
    }

    public List<Integer> listarMusicasPorCurtida(int idUsuario, boolean curtida) throws SQLException { //Captura as musicas curtidas
        List<Integer> musicas = new ArrayList<>();
        String sql = "SELECT id_musica FROM historico_curtidas WHERE id_usuario = ? AND curtida = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setBoolean(2, curtida);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                musicas.add(rs.getInt("id_musica"));
            }
        }
        return musicas;
    }
}
