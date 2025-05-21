/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author uniflelias
 */
public class Sessao { //Usei para registrar o usuario que está usando o app no momento
    private static Usuario usuarioLogado;

    public static void setUsuario(Usuario user) {
        usuarioLogado = user;
    }

    public static Usuario getUsuario() {
        return usuarioLogado;
    }
}
