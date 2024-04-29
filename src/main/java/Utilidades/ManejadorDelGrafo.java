/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import ArbolB.NodoArbolB;
import Objetos.Arista;
import Objetos.Nodo;
import Objetos.Vertices;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author branp
 */
public class ManejadorDelGrafo {

    public ManejadorDelGrafo() {
    }

    public ArrayList<String> SacarDatos(ArrayList<Vertices> Grafo) {
        ArrayList<String> Cadena = new ArrayList<>();
        for (int i = 0; i < Grafo.size(); i++) {
            Cadena.add(Grafo.get(i).getOrigen());
        }
        return Cadena;
    }
    // Función para encontrar todos los caminos entre dos vértices en un grafo

    public static NodoArbolB encontrarCaminos(Vertices inicio, String fin, ArrayList<NodoArbolB> Nodos, HashSet<String> visitados, ArrayList<Vertices> Grafo) {
        NodoArbolB nuevoNodo = new NodoArbolB(null, 0);
        if (inicio.getOrigen().equals(fin)) {
            nuevoNodo.getRecorrido().add(inicio.getOrigen());
            Nodos.add(nuevoNodo);
            return nuevoNodo;
        }
        visitados.add(inicio.getOrigen());
        for (Arista arista : inicio.getAristas()) {
            if (!visitados.contains(arista.getLugar())) {
                NodoArbolB nodoHijo = encontrarCaminos(obtenerVertice(arista.getLugar(), Grafo), fin, Nodos, visitados, Grafo);
                // Agregar el recorrido y el cálculo compuesto del nodo hijo al nodo actual
                nuevoNodo.getRecorrido().addAll(nodoHijo.getRecorrido());
                // Acumular los valores de los atributos relevantes del nodo hijo al nuevo nodo
                acumularValores(nuevoNodo.getDatosTotales(), nodoHijo.getDatosTotales());
            }
        }
        visitados.remove(inicio.getOrigen());
        return nuevoNodo;
    }
// Método para acumular los valores de los atributos relevantes del nodo hijo al nodo padre

    private static void acumularValores(Nodo nodoPadre, Nodo nodoHijo) {
        nodoPadre.setTiempoVehiculo(nodoPadre.getTiempoVehiculo() + nodoHijo.getTiempoVehiculo());
        nodoPadre.setTiempoPie(nodoPadre.getTiempoPie() + nodoHijo.getTiempoPie());
        nodoPadre.setConsumoGas(nodoPadre.getConsumoGas() + nodoHijo.getConsumoGas());
        nodoPadre.setDesgastePersona(nodoPadre.getDesgastePersona() + nodoHijo.getDesgastePersona());
    }

    // Función auxiliar para obtener un vértice dado su nombre
    public static Vertices obtenerVertice(String nombre, ArrayList<Vertices> Grafo) {
        for (int i = 0; i < Grafo.size(); i++) {
            if (nombre.equals(Grafo.get(i).getOrigen())) {
                return Grafo.get(i);
            }
        }
        return null;
    }

    public static boolean ExisteArista(ArrayList<Vertices> Grafo, String Origen, String Destino) {
        for (int i = 0; i < Grafo.size(); i++) {
            if (Grafo.get(i).getOrigen().equals(Origen)) {
                for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                    if (Grafo.get(i).getAristas().get(j).getLugar().equals(Destino)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void ActualizarArista(ArrayList<Vertices> Grafo, String Origen, String Destino, Nodo nodo) {
        for (int i = 0; i < Grafo.size(); i++) {
            if (Grafo.get(i).getOrigen().equals(Origen)) {
                for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                    if (Grafo.get(i).getAristas().get(j).getLugar().equals(Destino)) {
                        Grafo.get(i).getAristas().get(j).setInfo(nodo);
                    }
                }
            }
        }
    }

    public static void InsertarArista(ArrayList<Vertices> Grafo, String Origen, Arista arista) {
        for (int i = 0; i < Grafo.size(); i++) {
            if (Grafo.get(i).getOrigen().equals(Origen)) {
                Grafo.get(i).getAristas().add(arista);
            }
        }
    }

    public static ArrayList<Arista> DevolverArregloArista(ArrayList<Vertices> Grafo, String Origen) {
        if (Grafo.isEmpty()) {
            return null;
        }
        for (int i = 0; i < Grafo.size(); i++) {
            if (Grafo.get(i).getOrigen().equals(Origen)) {
                return Grafo.get(i).getAristas();
            }
        }
        return null;
    }

}
