/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Classe que gerencia a sessão do usuário atualmente logado no sistema.
 * Permite armazenar e recuperar o usuário que está utilizando o aplicativo no momento.
 * 
 * @author Leonardo Bezzi Elias
 */
public class Sessao {
    /**
     * Usuário que está logado na sessão atual.
     */
    private static Usuario usuarioLogado;

    /**
     * Define o usuário que está logado na sessão.
     * 
     * @param user o usuário a ser definido como logado
     */
    public static void setUsuario(Usuario user) {
        usuarioLogado = user;
    }

    /**
     * Retorna o usuário atualmente logado na sessão.
     * 
     * @return o usuário logado, ou null se ninguém estiver logado
     */
    public static Usuario getUsuario() {
        return usuarioLogado;
    }
}
