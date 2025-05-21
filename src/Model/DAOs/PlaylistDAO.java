/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAOs;

import Model.Conexao;
import Model.Musica;
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
        String sql = "SELECT id, nome FROM playlists WHERE usuario_id = ?";

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
    
    public boolean criarPlaylist(Playlist playlist) {
        String sql = "INSERT INTO playlists (nome, usuario_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, playlist.getNome());
            pst.setInt(2, playlist.getIdUsuario());

            int linhasAfetadas = pst.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    playlist.setId(rs.getInt(1)); // opcional: pega o ID gerado
                }
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirPlaylist(int idPlaylist) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPlaylist);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir playlist: " + e.getMessage());
            return false;
        }
    }
    
    public boolean renomearPlaylist(Playlist playlist) {
        String sql = "UPDATE playlists SET nome = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, playlist.getNome());
            ps.setInt(2, playlist.getId());
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao renomear playlist: " + e.getMessage());
            return false;
        }
    }

    public List<Musica> listarMusicasPorPlaylist(int idPlaylist) {
        List<Musica> musicas = new ArrayList<>();
        String sql = "SELECT m.id, m.nome, m.duracao, m.genero FROM musicas m "
                   + "JOIN playlist_musicas pm ON m.id = pm.id_musica WHERE pm.id_playlist = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPlaylist);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setDuracao(rs.getInt("duracao"));
                m.setGenero(rs.getString("genero"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar músicas da playlist: " + e.getMessage());
        }
        return musicas;
    }

    
}
