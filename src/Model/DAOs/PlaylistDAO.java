/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import Model.Conexao;
import Model.Playlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gamer
 */
public class PlaylistDAO {
    public List<Playlist> listarPorUsuario(int idUsuario) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT id, nome FROM playlists WHERE id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                playlists.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar playlists: " + e.getMessage());
        }

        return playlists;
    }
}
