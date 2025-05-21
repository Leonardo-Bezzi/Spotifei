/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gamer
 */
public class CurtidaInfo {
    private String nomeMusica;
    private boolean curtida;

    public CurtidaInfo(String nomeMusica, boolean curtida) {
        this.nomeMusica = nomeMusica;
        this.curtida = curtida;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public boolean isCurtida() {
        return curtida;
    }
}
