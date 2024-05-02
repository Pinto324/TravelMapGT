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
public class NodoArbolB {
    private ArrayList<String> Recorrido;
    private int calculo;

    public NodoArbolB(int calculo) {
        this.Recorrido = new ArrayList<>();
        this.calculo = calculo;
    }
public NodoArbolB() {
        this.Recorrido = new ArrayList<>();
        this.calculo = 0;
    }
    public ArrayList<String> getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(ArrayList<String> Recorrido) {
        this.Recorrido = Recorrido;
    }

    public int getCalculo() {
        return calculo;
    }

    public void setCalculo(int calculo) {
        this.calculo = calculo;
    }
}
