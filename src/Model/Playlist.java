/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Classe que representa uma playlist no sistema Spotifei.
 * Contém informações como ID, nome da playlist e ID do usuário dono.
 * 
 * @author Leonardo Bezzi Elias
 */
public class Playlist {
    /** Identificador da playlist */
    private int id;

    /** Nome da playlist */
    private String nome;

    /** Identificador do usuário dono da playlist */
    private int idUsuario;

    /**
     * Construtor padrão.
     */
    public Playlist() {
    }

    /**
     * Construtor com todos os atributos.
     * 
     * @param id identificador da playlist
     * @param nome nome da playlist
     * @param idUsuario identificador do usuário
     */
    public Playlist(int id, String nome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    /**
     * Construtor com ID e nome da playlist.
     * 
     * @param id identificador da playlist
     * @param nome nome da playlist
     */
    public Playlist(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return o ID do usuário dono da playlist
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario define o ID do usuário dono da playlist
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return o ID da playlist
     */
    public int getId() {
        return id;
    }

    /**
     * @param id define o ID da playlist
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return o nome da playlist
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome define o nome da playlist
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
