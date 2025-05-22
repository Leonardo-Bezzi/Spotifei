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
 * DAO para operações relacionadas a Playlists.
 * Fornece métodos para criar, listar, renomear e excluir playlists,
 * além de listar músicas dentro de uma playlist, com informação de curtidas do usuário.
 * 
 * @author Leonardo Bezzi Elias
 */
public class PlaylistDAO {

    /**
     * Lista todas as playlists de um usuário.
     * 
     * @param idUsuario ID do usuário dono das playlists
     * @return Lista de playlists pertencentes ao usuário
     */
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

    /**
     * Cria uma nova playlist para o usuário.
     * 
     * @param playlist Objeto Playlist com nome e usuário setados
     * @return true se a criação foi bem sucedida, false caso contrário
     */
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
                    playlist.setId(rs.getInt(1)); // captura o ID gerado
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

    /**
     * Exclui uma playlist pelo seu ID.
     * 
     * @param idPlaylist ID da playlist a ser excluída
     * @return true se a exclusão foi bem sucedida, false caso contrário
     */
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

    /**
     * Renomeia uma playlist existente.
     * 
     * @param playlist Objeto Playlist com o ID e novo nome setados
     * @return true se o renomear foi bem sucedido, false caso contrário
     */
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

    /**
     * Lista as músicas de uma playlist, incluindo se cada música está curtida pelo usuário.
     * 
     * @param idPlaylist ID da playlist
     * @param idUsuario  ID do usuário para verificar curtidas
     * @return Lista de músicas pertencentes à playlist com estado de curtida
     */
    public List<Musica> listarMusicasPorPlaylist(int idPlaylist, int idUsuario) {
        List<Musica> musicas = new ArrayList<>();
        String sql = """
            SELECT m.*, 
                   CASE WHEN c.id_musica IS NOT NULL THEN true ELSE false END AS curtida
            FROM musicas m
            JOIN playlist_musicas pm ON m.id = pm.musica_id
            LEFT JOIN curtidas c ON m.id = c.id_musica AND c.id_usuario = ?
            WHERE pm.playlist_id = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPlaylist);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setArtista(rs.getString("artista"));
                m.setGenero(rs.getString("genero"));
                m.setDuracao(rs.getInt("duracao"));
                m.setCurtida(rs.getBoolean("curtida"));
                musicas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar músicas da playlist: " + e.getMessage());
        }
        return musicas;
    }
}
