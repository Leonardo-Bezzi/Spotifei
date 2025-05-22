/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Representa a curtida feita por um usuário
 * Armazena o id do uuário, da música, e o estado da curtida
 * @author Leonardo Bezzi Elias
 */
public class Curtida {
    /**
    *
    * Id do usuário
    */
    private int idUsuario;
    /**
    *
    * Id da música
    */
    private int idMusica;
    /**
    *
    * Valor da curtida
    */
    private boolean curtida;

    /**
    *
    * Construtor vazio da curtida
    */
    public Curtida() {
    }

    /**
     * Construtor com todos os atributos.
     *
     * @param idUsuario o ID do usuário
     * @param idMusica o ID da música
     * @param curtida true se curtiu, false se descurtiu
     */
    public Curtida(int idUsuario, int idMusica, boolean curtida) {
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
        this.curtida = curtida;
    }
    
    /**
     * Retorna o ID do usuário.
     *
     * @return o ID do usuário
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    /**
     * Define o ID do usuário.
     *
     * @param idUsuario o novo ID do usuário
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Retorna o ID da música.
     *
     * @return o ID da música
     */
    public int getIdMusica() {
        return idMusica;
    }
    
    /**
     * Define o ID da música.
     *
     * @param idMusica o novo ID da música
     */
    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
    
    /**
     * Retorna o estado da curtida.
     *
     * @return true se curtiu, false se descurtiu
     */
    public boolean isCurtida() {
        return curtida;
    }
    
    /**
     * Define o estado da curtida.
     *
     * @param curtida true para curtir, false para descurtir
     */
    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }
}
