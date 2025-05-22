/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * Representa informações de curtida associadas a uma música, 
 * utilizadas para exibição em histórico de curtidas.
 * 
 * Contém apenas o nome da música e o status da curtida.
 * 
 * @author Leonardo Bezzi Elias
 */
public class CurtidaInfo {
    /**
     * Nome da música associada à curtida.
     */
    private String nomeMusica;
    /**
     * Status da curtida: true se o usuário curtiu, false caso contrário.
     */
    private boolean curtida;
    
    /**
     * Construtor que inicializa o nome da música e o status da curtida.
     * 
     * @param nomeMusica Nome da música
     * @param curtida Status da curtida (true ou false)
     */
    public CurtidaInfo(String nomeMusica, boolean curtida) {
        this.nomeMusica = nomeMusica;
        this.curtida = curtida;
    }
    
     /**
     * Retorna o nome da música.
     * 
     * @return nome da música
     */
    public String getNomeMusica() {
        return nomeMusica;
    }
    
    /**
     * Retorna o status da curtida.
     * 
     * @return true se curtida, false se não curtida
     */
    public boolean isCurtida() {
        return curtida;
    }
}
