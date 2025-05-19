/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author uniflelias
 */
public class Musica {
    private int id;
    private String nome;
    private String artista;
    private String genero;
    private int duracao; 
    private boolean curtida;

    public Musica(int id, String nome, String artista, String genero, int duracao) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }

    public Musica() {
    this.id = 0;  
    this.nome = "";
    this.artista = "";
    this.genero = "";
    this.duracao = 0;  
}


    public int getId() {
        return id;
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public boolean isCurtida() {
        return curtida;
    }

    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }
    
    
}
