/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class Vertices {
    private String Origen;
    private ArrayList<Arista> Aristas;

    public Vertices(String Origen, ArrayList<Arista> Aristas) {
        this.Origen = Origen;
        this.Aristas = Aristas;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public ArrayList<Arista> getAristas() {
        return Aristas;
    }

    public void setAristas(ArrayList<Arista> Aristas) {
        this.Aristas = Aristas;
    }
    
}
