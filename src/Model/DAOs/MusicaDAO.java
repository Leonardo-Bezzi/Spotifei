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
    public List<Musica> buscarPorCampo(String campo, String termo, int idUsuario) {
        List<Musica> musicas = new ArrayList<>();

        if (!campo.matches("nome|artista|genero")) {
            return musicas;
        }

        // Usando função para remover acentos
        String sql = "SELECT * FROM musicas WHERE " 
                   + "unaccent(LOWER(" + campo + ")) LIKE unaccent(LOWER(?))";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + termo + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Musica m = new Musica(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("artista"),
                    rs.getString("genero"),
                    rs.getInt("duracao")
                );
                musicas.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }

        return musicas;
    }
}