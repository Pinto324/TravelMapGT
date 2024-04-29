/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class NodoB {
    NodoArbolB[] datos;
    int gradoMinimo;
    ArrayList<NodoB> hijos;
    boolean hoja;
    int numDatos;

    public NodoB(int gradoMinimo, boolean hoja) {
        this.gradoMinimo = gradoMinimo;
        this.hoja = hoja;
        this.datos = new NodoArbolB[2 * gradoMinimo - 1];
        this.hijos = new ArrayList<>();
        this.numDatos = 0;
    }
}
