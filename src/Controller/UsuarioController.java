/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DAOs.UsuarioDAO;
import Model.Usuario;

/**
 *
 * @author uniflelias
 */


public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario(0, nome, email, senha);
        return usuarioDAO.cadastrarUsuario(novoUsuario);
    }

    public Usuario autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }
}
