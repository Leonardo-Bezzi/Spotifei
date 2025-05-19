/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author uniflelias
 */
public class Curtida {
    private int idUsuario;
    private int idMusica;
    private boolean curtida;

    public Curtida() {
    }

    public Curtida(int idUsuario, int idMusica, boolean curtida) {
        this.idUsuario = idUsuario;
        this.idMusica = idMusica;
        this.curtida = curtida;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }

    public boolean isCurtida() {
        return curtida;
    }

    public void setCurtida(boolean curtida) {
        this.curtida = curtida;
    }
}
