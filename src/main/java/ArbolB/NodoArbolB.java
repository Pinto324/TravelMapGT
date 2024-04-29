/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import Objetos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class NodoArbolB {
    private ArrayList<String> Recorrido;
    private Nodo DatosTotales;
    private int CalcCompuesto;

    public NodoArbolB(Nodo DatosTotales, int CalcCompuesto) {
        this.Recorrido = new ArrayList<>();
        this.DatosTotales = DatosTotales;
        this.CalcCompuesto = CalcCompuesto;
    }

    public ArrayList<String> getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(ArrayList<String> Recorrido) {
        this.Recorrido = Recorrido;
    }

    public Nodo getDatosTotales() {
        return DatosTotales;
    }

    public void setDatosTotales(Nodo DatosTotales) {
        this.DatosTotales = DatosTotales;
    }

    public int getCalcCompuesto() {
        return CalcCompuesto;
    }

    public void setCalcCompuesto(int CalcCompuesto) {
        this.CalcCompuesto = CalcCompuesto;
    }
    
}
