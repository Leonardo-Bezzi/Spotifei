package Model.DAOs;

import Model.Conexao;
import Model.Musica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para gerenciamento de músicas, incluindo buscas, curtidas e manipulação de playlists.
 * 
 * Permite buscar músicas por campo, adicionar/remover curtidas, registrar histórico de buscas e curtidas,
 * listar músicas em playlists e gerenciar músicas nas playlists dos usuários.
 * 
 * @author Leonardo Bezzi
 */
public class MusicaDAO {

    /**
     * Busca músicas por um campo específico (nome, artista ou gênero) e termo,
     * incluindo informação se o usuário curtiu cada música.
     * Também registra o termo da busca no histórico.
     * 
     * @param campo Campo para busca ("nome", "artista" ou "genero")
     * @param termo Termo a ser buscado (pesquisa parcial)
     * @param idUsuario ID do usuário que realizou a busca
     * @return Lista de músicas que correspondem à busca
     */
    public List<Musica> buscarPorCampo(String campo, String termo, int idUsuario) {
        List<Musica> musicas = new ArrayList<>();

        if (!List.of("nome", "artista", "genero").contains(campo)) return musicas;

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

    /**
     * Adiciona um termo de busca ao histórico de buscas do usuário.
     * 
     * @param idUsuario ID do usuário
     * @param termo Termo buscado
     */
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

    /**
     * Adiciona uma curtida para uma música por um usuário,
     * e registra a ação no histórico de curtidas.
     * 
     * @param idUsuario ID do usuário
     * @param idMusica ID da música
     */
    public void adicionarCurtida(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, true) ON CONFLICT DO NOTHING";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();

            adicionarHistoricoCurtida(idUsuario, idMusica, true);

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar curtida: " + e.getMessage());
        }
    }

    /**
     * Registra uma ação de curtida ou descurtida no histórico.
     * 
     * @param idUsuario ID do usuário
     * @param idMusica ID da música
     * @param curtida true para curtida, false para descurtida
     */
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

    /**
     * Remove a curtida de uma música para um usuário,
     * e registra a ação no histórico como descurtida.
     * 
     * @param idUsuario ID do usuário
     * @param idMusica ID da música
     */
    public void removerCurtida(int idUsuario, int idMusica) {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_musica = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();

            adicionarHistoricoCurtida(idUsuario, idMusica, false);

        } catch (SQLException e) {
            System.out.println("Erro ao remover curtida: " + e.getMessage());
        }
    }

    /**
     * Lista as músicas de uma determinada playlist,
     * incluindo informação de curtida para o usuário.
     * 
     * @param idPlaylist ID da playlist
     * @param idUsuario ID do usuário
     * @return Lista de músicas da playlist
     */
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

    /**
     * Adiciona uma música a uma playlist do usuário.
     * 
     * @param idMusica ID da música
     * @param idPlaylist ID da playlist
     * @param idUsuario ID do usuário
     * @return true se adicionou com sucesso, false caso contrário
     */
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

    /**
     * Remove uma música de uma playlist do usuário.
     * 
     * @param idMusica ID da música
     * @param idPlaylist ID da playlist
     * @param idUsuario ID do usuário
     * @return true se removeu com sucesso, false caso contrário
     */
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
