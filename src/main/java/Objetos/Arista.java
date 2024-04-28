/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author branp
 */
public class Arista {
    private String Destino;
    private Nodo info;

    public Arista(String Lugar, Nodo info) {
        this.Destino = Lugar;
        this.info = info;
    }

    public String getLugar() {
        return Destino;
    }

    public void setLugar(String Lugar) {
        this.Destino = Lugar;
    }

    public Nodo getInfo() {
        return info;
    }

    public void setInfo(Nodo info) {
        this.info = info;
    }
    
}
