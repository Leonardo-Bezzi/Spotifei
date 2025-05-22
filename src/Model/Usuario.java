/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Representa um usuário do sistema.
 * Contém informações básicas como id, nome, email e senha.
 * 
 * @author Leonardo Bezzi Elias
 */

public class Usuario {
    /**
     * Identificador único do usuário.
     */
    private int id;
    
    /**
     * Nome completo do usuário.
     */
    private String nome;
    
    /**
     * Email do usuário.
     */
    private String email;
    
    /**
     * Senha do usuário.
     */
    private String senha;

    /**
     * Construtor que inicializa o usuário com seus dados.
     * 
     * @param id Identificador do usuário
     * @param nome Nome completo do usuário
     * @param email Email do usuário
     * @param senha Senha do usuário
     */
    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /**
     * Define o identificador do usuário.
     * 
     * @param id Novo id do usuário
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Define o nome do usuário.
     * 
     * @param nome Novo nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o email do usuário.
     * 
     * @param email Novo email do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Define a senha do usuário.
     * 
     * @param senha Nova senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    /**
     * Retorna o identificador do usuário.
     * 
     * @return id do usuário
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o email do usuário.
     * 
     * @return email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retorna a senha do usuário.
     * 
     * @return senha do usuário
     */
    public String getSenha() {
        return senha;
    }
}

