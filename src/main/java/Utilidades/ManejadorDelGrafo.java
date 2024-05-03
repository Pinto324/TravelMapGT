/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import ArbolB.NodoArbolB;
import Grafos.NodoRecorridoDeGrafo;
import Objetos.Arista;
import Objetos.Nodo;
import Objetos.Vertices;
import static Utilidades.Graficador.Graficar;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public static void encontrarCaminos(String Origen, Vertices inicio, String fin, ArrayList<NodoRecorridoDeGrafo> Nodos, HashSet<String> visitados, ArrayList<Vertices> Grafo, Vertices Anterior, boolean vehiculo) {
        if (vehiculo) {
            encontrarCaminosRecursivo(Origen, inicio, fin, Nodos, visitados, Grafo, Anterior, new NodoRecorridoDeGrafo());
        } else {
            ArrayList<NodoRecorridoDeGrafo> NodosInversos = new ArrayList<>();
            ArrayList<NodoRecorridoDeGrafo> Nodosnormales = new ArrayList<>();
            encontrarCaminosRecursivo(Origen, inicio, fin, Nodosnormales, visitados, Grafo, Anterior, new NodoRecorridoDeGrafo());
            encontrarCaminosRecursivoInversos(fin, obtenerVertice(fin,Grafo), Origen, NodosInversos, visitados, Grafo, Anterior, new NodoRecorridoDeGrafo());
            // Eliminar elementos duplicados de nodosInversos que también están en nodosNormales
            for (NodoRecorridoDeGrafo nodoInverso : NodosInversos) {
                if (Nodosnormales.contains(nodoInverso)) {
                    NodosInversos.remove(nodoInverso);
                }
            }
            Nodosnormales.addAll(NodosInversos);
            Nodos.addAll(Nodosnormales);   
        }
    }

//busqueda tomando en cuenta las aristas
    private static void encontrarCaminosRecursivo(String Origen, Vertices inicio, String fin, ArrayList<NodoRecorridoDeGrafo> Nodos, HashSet<String> visitados, ArrayList<Vertices> Grafo, Vertices Anterior, NodoRecorridoDeGrafo nuevoNodo) {
        if (inicio == null) {
            // Si el vértice de inicio es nulo, salimos de la recursión
            return;
        }
        if (inicio.getOrigen().equals(fin)) {
            // Si hemos llegado al nodo de destino, agregamos el camino actual a la lista de caminos
            nuevoNodo.getRecorrido().add(inicio.getOrigen());
            Nodos.add(nuevoNodo);
            return;
        }
        visitados.add(inicio.getOrigen());
        for (Arista arista : inicio.getAristas()) {
            if (!visitados.contains(arista.getLugar())) {
                NodoRecorridoDeGrafo nuevoNodoRecursivo = new NodoRecorridoDeGrafo();
                nuevoNodoRecursivo.getRecorrido().addAll(nuevoNodo.getRecorrido());
                nuevoNodoRecursivo.getDatosTotales().addAll(nuevoNodo.getDatosTotales());
                nuevoNodoRecursivo.getCalcCompuesto().addAll(nuevoNodo.getCalcCompuesto());
                nuevoNodoRecursivo.getRecorrido().add(inicio.getOrigen());
                nuevoNodoRecursivo.getDatosTotales().add(arista.getInfo());
                encontrarCaminosRecursivo(Origen, obtenerVertice(arista.getLugar(), Grafo), fin, Nodos, visitados, Grafo, Anterior, nuevoNodoRecursivo);
            }
        }
        visitados.remove(inicio.getOrigen());
    }

    private static void encontrarCaminosRecursivoInversos(String Origen, Vertices inicio, String fin, ArrayList<NodoRecorridoDeGrafo> Nodos, HashSet<String> visitados, ArrayList<Vertices> Grafo, Vertices Anterior, NodoRecorridoDeGrafo nuevoNodo) {
        if (inicio == null) {
            // Si el vértice de inicio es nulo, salimos de la recursión
            return;
        }
        if (inicio.getOrigen().equals(fin)) {
            // Si hemos llegado al nodo de destino, agregamos el camino actual a la lista de caminos
            nuevoNodo.getRecorrido().add(inicio.getOrigen());
            Collections.reverse(nuevoNodo.getRecorrido());
            Collections.reverse(nuevoNodo.getDatosTotales());
            Nodos.add(nuevoNodo);
            return;
        }
        visitados.add(inicio.getOrigen());
        for (Arista arista : inicio.getAristas()) {
            if (!visitados.contains(arista.getLugar())) {
                NodoRecorridoDeGrafo nuevoNodoRecursivo = new NodoRecorridoDeGrafo();
                nuevoNodoRecursivo.getRecorrido().addAll(nuevoNodo.getRecorrido());
                nuevoNodoRecursivo.getDatosTotales().addAll(nuevoNodo.getDatosTotales());
                nuevoNodoRecursivo.getCalcCompuesto().addAll(nuevoNodo.getCalcCompuesto());
                nuevoNodoRecursivo.getRecorrido().add(inicio.getOrigen());
                nuevoNodoRecursivo.getDatosTotales().add(arista.getInfo());
                encontrarCaminosRecursivoInversos(Origen, obtenerVertice(arista.getLugar(), Grafo), fin, Nodos, visitados, Grafo, Anterior, nuevoNodoRecursivo);
            }
        }
        visitados.remove(inicio.getOrigen());
    }

    private static Nodo ObtenerNodo(ArrayList<Arista> Aristas, String Destino) {
        for (int i = 0; i < Aristas.size(); i++) {
            if (Aristas.get(i).getLugar().equals(Destino)) {
                return Aristas.get(i).getInfo();
            }
        }
        return null;
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

    public static void ActualizarTrafico(ArrayList<Vertices> Grafo, String Origen, String Destino, LocalTime horaI, LocalTime horaF, int trafico) {
        for (int i = 0; i < Grafo.size(); i++) {
            if (Grafo.get(i).getOrigen().equals(Origen)) {
                for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                    if (Grafo.get(i).getAristas().get(j).getLugar().equals(Destino)) {
                        Grafo.get(i).getAristas().get(j).getInfo().getHoraInicio().add(horaI);
                        Grafo.get(i).getAristas().get(j).getInfo().getHoraFinal().add(horaF);
                        Grafo.get(i).getAristas().get(j).getInfo().getTrafico().add(trafico);
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

    public static HashSet<String> SacarCadenas(ArrayList<Vertices> Grafo) {
        HashSet<String> Cadenas = new HashSet<String>();
        for (int i = 0; i < Grafo.size(); i++) {
            for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                Cadenas.add(Grafo.get(i).getOrigen());
                Cadenas.add(Grafo.get(i).getAristas().get(j).getLugar());
            }
        }
        return Cadenas;
    }
}
