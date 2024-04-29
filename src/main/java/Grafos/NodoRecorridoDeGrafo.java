/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import Objetos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class NodoRecorridoDeGrafo {
    private ArrayList<String> Recorrido;
    private ArrayList<Nodo> DatosTotales;
    private ArrayList<Double> CalcCompuesto;

    public NodoRecorridoDeGrafo() {
        this.Recorrido = new ArrayList<>();
        this.DatosTotales = new ArrayList<>();
        this.CalcCompuesto = new ArrayList<>();
    }
    
    public ArrayList<String> getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(ArrayList<String> Recorrido) {
        this.Recorrido = Recorrido;
    }

    public ArrayList<Nodo> getDatosTotales() {
        return DatosTotales;
    }

    public void setDatosTotales(ArrayList<Nodo> DatosTotales) {
        this.DatosTotales = DatosTotales;
    }

    public ArrayList<Double> getCalcCompuesto() {
        return CalcCompuesto;
    }

    public void setCalcCompuesto(ArrayList<Double> CalcCompuesto) {
        this.CalcCompuesto = CalcCompuesto;
    }
}
