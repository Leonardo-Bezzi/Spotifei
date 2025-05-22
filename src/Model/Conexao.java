/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Classe responsável por fornecer a conexão com o banco de dados PostgreSQL.
 * Utiliza os parâmetros de URL, usuário e senha definidos como constantes.
 * A conexão é utilizada em todo o sistema para interagir com o banco de dados.
 * 
 * Banco utilizado: PostgreSQL
 * 
 * URL padrão: jdbc:postgresql://localhost:5432/Spotifei
 * Usuário padrão: postgres
 * Senha padrão: fei
 * 
 * Certifique-se de que o PostgreSQL esteja em execução e configurado corretamente.
 * 
 * @author Leonardo Bezzi Elias
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/Spotifei";  // localhost e porta que eu configurei no p da sala e em casa
    private static final String USER = "postgres";  // Usuário do PostgreSQL
    private static final String PASSWORD = "fei";  // Senha do usuário postgres
    
    /**
     * Estabelece e retorna uma conexão com o banco de dados PostgreSQL.
     * 
     * @return Connection objeto de conexão se bem-sucedido, ou null em caso de erro.
     */
    public static Connection getConnection() { //Conecta no Postgres
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}

