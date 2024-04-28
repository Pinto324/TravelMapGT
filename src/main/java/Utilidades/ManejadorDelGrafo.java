/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Objetos.Vertices;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class ManejadorDelGrafo {

    public ManejadorDelGrafo() {
    }
    
    public ArrayList<String> SacarDatos(ArrayList<Vertices> Grafo){
        ArrayList<String> Cadena = new ArrayList<>();
        for (int i = 0; i < Grafo.size(); i++) {
            Cadena.add(Grafo.get(i).getOrigen());
        }
        return Cadena;
    }
}
