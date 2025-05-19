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

    // Método genérico que busca por qualquer campo (nome, artista, gênero)
    public List<Musica> buscarPorCampo(String campo, String termo) {
        List<Musica> musicas = new ArrayList<>();

        // Verifica se o campo é seguro para evitar SQL injection
        if (!List.of("nome", "artista", "genero").contains(campo)) return musicas;

        // SQL dinamicamente gerado com base no campo passado
        String sql = "SELECT * FROM musicas WHERE " + campo + " ILIKE ?";

        System.out.println("SQL gerada: " + sql);  // Imprime a consulta para depuração

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + termo + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Musica m = new Musica();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setArtista(rs.getString("artista"));
                m.setGenero(rs.getString("genero"));
                m.setDuracao(rs.getInt("duracao"));
                musicas.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }

        return musicas;
    }
}
