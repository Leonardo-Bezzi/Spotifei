/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotifei;

/**
 *
 * @author uniflelias
 */

import Model.Conexao;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = Conexao.getConnection();
        if (connection != null) {
            System.out.println("Conexão bem-sucedida ao banco de dados!");
        } else {
            System.out.println("Falha na conexão!");
        }
    }
}

/*funfou*/