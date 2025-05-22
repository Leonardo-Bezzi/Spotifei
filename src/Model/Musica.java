/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Classe que representa uma música no sistema Spotifei.
 * Contém informações como nome, artista, gênero, duração e se foi curtida.
 * 
 * @author Leonardo Bezzi Elias
 */
public class Musica {
    /** Identificador da música */
    private int id;
    /** Nome da música */
    private String nome;
    /** Nome do artista */
    private String artista;
    /** Gênero da música */
    private String genero;
    /** Duração da música em segundos */
    private int duracao; 
    /** Indica se a música foi curtida pelo usuário */
    private boolean curtida;
    
    /**
     * Construtor com todos os atributos, exceto curtida.
     * 
     * @param id identificador da música
     * @param nome nome da música
     * @param artista nome do artista
     * @param genero gênero da música
     * @param duracao duração da música em segundos
     */
    public Musica(int id, String nome, String artista, String genero, int duracao) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
    }
    /**
     * Construtor padrão que inicializa com valores padrão.
     */
    public Musica() {
        this.id = 0;  
        this.nome = "";
        this.artista = "";
        this.genero = "";
        this.duracao = 0;  
    }
    /**
     * Construtor com todos os atributos.
     * 
     * @param id identificador da música
     * @param nome nome da música
     * @param artista nome do artista
     * @param genero gênero da música
     * @param duracao duração da música em segundos
     * @param curtida indica se a música foi curtida
     */
    public Musica(int id, String nome, String artista, String genero, int duracao, boolean curtida) {
        this.id = id;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
        this.duracao = duracao;
        this.curtida = curtida;
    }

    /** @return o id da música */
    public int getId() {
        return id;
    }
    /** @param id define o id da música */
    public void setId(int id) {
        this.id = id;
    }
    /** @return o nome da música */
    public String getNome() {
        return nome;
    }
    /** @param nome define o nome da música */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /** @return o nome do artista */
    public String getArtista() {
        return artista;
    }
    /** @param artista define o nome do artista */
    public void setArtista(String artista) {
        this.artista = artista;
    }
    /** @return o gênero da música */
    public String getGenero() {
        return genero;
    }
    /** @param genero define o gênero da música */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    /** @return a duração da música em segundos */
    public int getDuracao() {
        return duracao;
    }
    /** @param duracao define a duração da música em segundos */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    /** @return true se a música foi curtida, false caso contrário */
    public boolean isCurtida() {
        return curtida;
    }
    /** @param curtida define se a música foi curtida */
    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }
    
    
}
