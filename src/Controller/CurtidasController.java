/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Conexao;
import Model.Curtida;
import Model.CurtidaInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controllador que obtém o histórico de curtidas de músicas do usuário
 * Utiliza o banco de dados para buscar informações da tabela de histórico
 * 
 * @author Leonardo Bezzi Elias
 */
public class CurtidasController {
    /**
     * Retorna o histórico de curtidas e descurtidas realizadas por um usuário.
     * Cada item do histórico inclui o nome da música e se foi curtida ou não.
     *
     * @param idUsuario o ID do usuário cujo histórico será recuperado
     * @return uma lista de objetos CurtidaInfo com o nome da música e o estado da curtida
     */
    public static List<CurtidaInfo> getHistoricoCurtidas(int idUsuario) { //Pega o historico de curtidas para uso
        List<CurtidaInfo> historicoCurtidas = new ArrayList<>();
        String sql = "SELECT m.nome, c.curtida FROM historico_curtidas c JOIN musicas m ON c.id_musica = m.id WHERE c.id_usuario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nomeMusica = rs.getString("nome"); // corrigido aqui
                boolean curtida = rs.getBoolean("curtida");
                historicoCurtidas.add(new CurtidaInfo(nomeMusica, curtida));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicoCurtidas;
    }
}
