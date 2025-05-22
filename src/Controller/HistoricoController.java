/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Conexao;
import Model.CurtidaInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador responsável por gerenciar o histórico de interações do usuário,
 * como buscas realizadas e músicas curtidas.
 * Conecta-se ao banco de dados para registrar e recuperar informações.
 * 
 * @author Leonardo Bezzi Elias
 */
public class HistoricoController {
    /**
     * Registra uma busca realizada pelo usuário no banco de dados.
     *
     * @param idUsuario   o ID do usuário que realizou a busca
     * @param termoBusca  o termo buscado pelo usuário
     */
    public static void registrarBusca(int idUsuario, String termoBusca) { //Registra na table a pesquisa feita
        String sql = "INSERT INTO historico_buscas (id_usuario, termo_busca) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setString(2, termoBusca);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Registra uma curtida ou descurtida de uma música por um usuário.
     *
     * @param idUsuario  o ID do usuário
     * @param idMusica   o ID da música
     * @param curtida    valor booleano indicando se foi curtida (true) ou descurtida (false)
     */
    public static void registrarCurtida(int idUsuario, int idMusica, boolean curtida) { //Registra na table as curtidas
        String sql = "INSERT INTO historico_curtidas (id_usuario, id_musica, curtida) VALUES (?, ?, ?)";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idMusica);
            stmt.setBoolean(3, curtida);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o histórico de buscas realizadas por um usuário.
     *
     * @param idUsuario  o ID do usuário
     * @return uma lista de termos de busca registrados
     */
    public static List<String> getHistoricoBuscas(int idUsuario) { //Pega historico de buscas para uso
        List<String> historicoBuscas = new ArrayList<>();
        String sql = "SELECT termo_busca FROM historico_buscas WHERE id_usuario = ?";


        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                historicoBuscas.add(rs.getString("termo_busca"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historicoBuscas;
    }
    
    
    
}
