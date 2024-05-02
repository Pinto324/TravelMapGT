/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import ArbolB.NodoArbolB;
import Objetos.Vertices;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author branp
 */
public class Graficador {

    public Graficador() {
    }

    public static void SacarCadenas(ArrayList<Vertices> Grafo) {
        String Cadena = "";
        for (int i = 0; i < Grafo.size(); i++) {
            for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                Cadena += Grafo.get(i).getOrigen() + "->" + Grafo.get(i).getAristas().get(j).getLugar() + ";\n";
            }
        }
        String Aux = "digraph finite_state_machine {\n"
                + "	fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "	node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	rankdir=LR;\n"
                + "	node [shape = circle];\n" + Cadena + "\n}";
        Graficar(Aux);
    }

    public static void SacarCadenarRutas(ArrayList<NodoArbolB> Lista, ArrayList<Vertices> Grafo, boolean positivo, String inicio) {
        int contador = 1;
        HashSet<String> visitados = new HashSet<String>();
        String Nodos = "";
        String Aux = "digraph finite_state_machine {\n"
                + "	fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "	node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	rankdir=LR;\n"
                + "	node [shape = circle];";
        // Agregar los nodos de las rutas especiales al conjunto de visitados
        for (NodoArbolB nodo : Lista) {
            for (String lugar : nodo.getRecorrido()) {
                visitados.add(lugar);
            }
        }
        // Agregar las aristas del grafo completo
        for (int i = 0; i < Grafo.size(); i++) {
            String origen = Grafo.get(i).getOrigen();
            for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                String destino = Grafo.get(i).getAristas().get(j).getLugar();
                // Verificar si tanto el nodo de origen como el de destino están en el conjunto de visitados
                if (visitados.contains(origen) && visitados.contains(destino)) {
                } else {
                    Aux += origen + "->" + destino + ";\n";
                }
            }
        }
        if (positivo) {
            for (int i = Lista.size() - 1; i > 0; i--) {
                for (int j = 0; j < Lista.get(i).getRecorrido().size() - 1; j++) {
                    Aux += Lista.get(i).getRecorrido().get(j) + " -> ";
                    Nodos += Lista.get(i).getRecorrido().get(j) +" [shape = circle, style=filled, fillcolor=white, color=blue];";
                }
                Aux += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [label = \"Ruta" + contador + "\", color=blue];\n";
                Nodos += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) +" [shape = circle, style=filled, fillcolor=white, color=blue];";
                contador++;
            }
            for (int i = 0; i < Lista.get(0).getRecorrido().size() - 1; i++) {
                Aux += Lista.get(0).getRecorrido().get(i) + " -> ";
                if (Lista.get(0).getRecorrido().get(i).equals(inicio)) {
                    Nodos += Lista.get(0).getRecorrido().get(i) +" [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                }else{
                    Nodos += Lista.get(0).getRecorrido().get(i) +" [shape = circle, style=filled, fillcolor=white, color=green];";
                }
            }
            Aux += Lista.get(0).getRecorrido().get(Lista.get(0).getRecorrido().size() - 1) + "[label = \"Ruta" + contador + "\", color=green, style=\"bold\"]; \n";
            Nodos += Lista.get(0).getRecorrido().get(Lista.get(0).getRecorrido().size() - 1) +" [shape = circle, style=filled, fillcolor=white, color=green];";
        } else {
            for (int i = 0; i < Lista.size() - 1; i++) {
                for (int j = 0; j < Lista.get(i).getRecorrido().size() - 1; j++) {
                    Aux += Lista.get(i).getRecorrido().get(j) + " -> ";
                    Nodos += Lista.get(i).getRecorrido().get(j) +" [shape = circle, style=filled, fillcolor=white, color=blue];";
                }
                Aux += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [label = \"Ruta" + contador + "\", color=blue];\n";
                Nodos += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) +" [shape = circle, style=filled, fillcolor=white, color=blue];";
                contador++;
            }
            for (int i = 0; i < Lista.get(Lista.size() - 1).getRecorrido().size() - 1; i++) {
                Aux += Lista.get(Lista.size() - 1).getRecorrido().get(i) + " -> ";
                if (Lista.get(Lista.size() - 1).getRecorrido().get(i).equals(inicio)) {
                    Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(i) +" [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                }else{
                    Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(i) +" [shape = circle, style=filled, fillcolor=white, color=green];";
                }
            }
            Aux += Lista.get(Lista.size() - 1).getRecorrido().get(Lista.get(Lista.size() - 1).getRecorrido().size() - 1) + "[label = \"Ruta" + contador + "\", color=green, style=\"bold\"]; \n";
            Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(Lista.get(Lista.size() - 1).getRecorrido().size() - 1) +" [shape = circle, style=filled, fillcolor=white, color=green];";
        }
        Aux += Nodos;
        Aux += "}";
        Graficar(Aux);
    }

    public static void Graficar(String Cadenas) {
        String dotFilePath = "Mapa.png";
        //String dotFilePath = "graph.dot";
        try {
            Parser parser = new Parser();
            MutableGraph graph = parser.read(Cadenas);
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(dotFilePath));
        } catch (IOException e) {

        }
    }
}
