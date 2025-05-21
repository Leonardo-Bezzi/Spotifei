/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gamer
 */
public class Playlist {
    private int id;
    private String nome;
    private int idUsuario;

    public Playlist() {
    }

    public Playlist(int id, String nome, int idUsuario) {
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }
    
    public Playlist(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
 
    public int getId() {
        return id;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
