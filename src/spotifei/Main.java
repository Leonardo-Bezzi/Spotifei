/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package spotifei;

/**
 * Classe principal do sistema Spotifei.
 * Responsável por iniciar a aplicação exibindo a tela de login.
 * 
 * Músicas no banco de dados (duração em segundos):
 * <ul>
 *   <li>"Blinding Lights" - The Weeknd (200s)</li>
 *   <li>"Shape of You" - Ed Sheeran (233s)</li>
 *   <li>"Levitating" - Dua Lipa (203s)</li>
 *   <li>"Stay" - The Kid LAROI (137s)</li>
 *   <li>"Peaches" - Justin Bieber (201s)</li>
 *   <li>"Good 4 U" - Olivia Rodrigo (210s)</li>
 *   <li>"Industry Baby" - Lil Nas X (223s)</li>
 *   <li>"Save Your Tears" - The Weeknd (215s)</li>
 *   <li>"Kiss Me More" - Doja Cat (209s)</li>
 *   <li>"Montero" - Lil Nas X (200s)</li>
 *   <li>"Deja Vu" - Olivia Rodrigo (215s)</li>
 *   <li>"Bad Habits" - Ed Sheeran (222s)</li>
 *   <li>"Happier Than Ever" - Billie Eilish (237s)</li>
 *   <li>"Watermelon Sugar" - Harry Styles (174s)</li>
 *   <li>"Butter" - BTS (249s)</li>
 *   <li>"Take My Breath" - The Weeknd (226s)</li>
 *   <li>"Stay" - Kid LAROI & Justin Bieber (137s)</li>
 * </ul>
 * 
 * @author Leonardo Bezzi Elias
 */

import View.TelaLogin;

public class Main { 
    /**
     * Método principal que inicia a aplicação exibindo a tela de login.
     * Executado na thread de eventos do Swing.
     * 
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}

