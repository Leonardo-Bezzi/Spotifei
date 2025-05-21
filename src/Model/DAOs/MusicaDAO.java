package Model.DAOs;

import Model.Conexao;
import Model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author uniflelias
 */
public class MusicaDAO {

    // Método que busca por qualquer campo (nome, artista, gênero)
    public List<Musica> buscarPorCampo(String campo, String termo, int idUsuario) {
        List<Musica> musicas = new ArrayList<>();

        if (!List.of("nome", "artista", "genero").contains(campo)) return musicas;

        // Chama o método para gravar o termo buscado no histórico
        adicionarHistoricoBusca(idUsuario, termo);

        String sql = """
        SELECT m.*, 
               CASE WHEN c.id_musica IS NOT NULL THEN true ELSE false END AS curtida
        FROM musicas m
        LEFT JOIN curtidas c ON m.id = c.id_musica AND c.id_usuario = ?
        WHERE m.""" + campo + " ILIKE ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idUsuario);
            pst.setString(2, "%" + termo + "%");

            ResultSet rs = pst.executeQuery();

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
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }

        return musicas;
    }

    
    public void adicionarHistoricoBusca(int idUsuario, String termo) {
        String sql = "INSERT INTO historico_buscas (id_usuario, termo_busca) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setString(2, termo);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar histórico de busca: " + e.getMessage());
        }
    }

    
    public void adicionarCurtida(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, true) ON CONFLICT DO NOTHING";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();

            // Aqui adiciona registro no histórico de curtidas
            adicionarHistoricoCurtida(idUsuario, idMusica, true);

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar curtida: " + e.getMessage());
        }
    }

    public void adicionarHistoricoCurtida(int idUsuario, int idMusica, boolean curtida) {
        String sql = "INSERT INTO historico_curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.setBoolean(3, curtida);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar histórico de curtida: " + e.getMessage());
        }
    }

    
    public void removerCurtida(int idUsuario, int idMusica) {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_musica = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();

            // Aqui adiciona registro no histórico de descurtidas
            adicionarHistoricoCurtida(idUsuario, idMusica, false);

        } catch (SQLException e) {
            System.out.println("Erro ao remover curtida: " + e.getMessage());
        }
    }

    
    public List<Musica> listarPorPlaylist(int idPlaylist, int idUsuario) {
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
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idUsuario);
            pst.setInt(2, idPlaylist);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Musica m = new Musica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("artista"),
                    rs.getString("genero"),
                    rs.getInt("duracao"),
                    rs.getBoolean("curtida")
                );
                musicas.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar músicas da playlist: " + e.getMessage());
        }
        return musicas;
    }
    
    public boolean adicionarMusicaNaPlaylist(int idMusica, int idPlaylist, int idUsuario) {
        String sql = "INSERT INTO playlist_musicas (playlist_id, musica_id, usuario_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idPlaylist);
            pst.setInt(2, idMusica);
            pst.setInt(3, idUsuario);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar música na playlist: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerMusicaDaPlaylist(int idMusica, int idPlaylist, int idUsuario) {
        String sql = "DELETE FROM playlist_musicas WHERE playlist_id = ? AND musica_id = ? AND usuario_id = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idPlaylist);
            pst.setInt(2, idMusica);
            pst.setInt(3, idUsuario);
            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover música da playlist: " + e.getMessage());
            return false;
        }
    }
    
}
