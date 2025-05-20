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
    
    public void adicionarCurtida(int idUsuario, int idMusica) {
        String sql = "INSERT INTO curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, true) ON CONFLICT DO NOTHING";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar curtida: " + e.getMessage());
        }
    }


    public void removerCurtida(int idUsuario, int idMusica) {
        String sql = "DELETE FROM curtidas WHERE id_usuario = ? AND id_musica = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            pst.setInt(2, idMusica);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover curtida: " + e.getMessage());
        }
    }
    
}
