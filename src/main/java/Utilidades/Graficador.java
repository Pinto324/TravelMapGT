/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import ArbolB.NodoArbolB;
import Objetos.EstructuraGrafica;
import Objetos.Vertices;
import guru.nidi.graphviz.attribute.Rank.RankDir;
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
import guru.nidi.graphviz.attribute.*;

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
                    Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                }
                Aux += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [label = \"Ruta" + contador + "\", color=blue];\n";
                Nodos += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                contador++;
            }
            for (int i = 0; i < Lista.get(0).getRecorrido().size() - 1; i++) {
                Aux += Lista.get(0).getRecorrido().get(i) + " -> ";
                if (Lista.get(0).getRecorrido().get(i).equals(inicio)) {
                    Nodos += Lista.get(0).getRecorrido().get(i) + " [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                } else {
                    Nodos += Lista.get(0).getRecorrido().get(i) + " [shape = circle, style=filled, fillcolor=white, color=green];";
                }
            }
            Aux += Lista.get(0).getRecorrido().get(Lista.get(0).getRecorrido().size() - 1) + "[label = \"Ruta" + contador + "\", color=green, style=\"bold\"]; \n";
            Nodos += Lista.get(0).getRecorrido().get(Lista.get(0).getRecorrido().size() - 1) + " [shape = circle, style=filled, fillcolor=white, color=green];";
        } else {
            for (int i = 0; i < Lista.size() - 1; i++) {
                for (int j = 0; j < Lista.get(i).getRecorrido().size() - 1; j++) {
                    Aux += Lista.get(i).getRecorrido().get(j) + " -> ";
                    Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                }
                Aux += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [label = \"Ruta" + contador + "\", color=blue];\n";
                Nodos += Lista.get(i).getRecorrido().get(Lista.get(i).getRecorrido().size() - 1) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                contador++;
            }
            for (int i = 0; i < Lista.get(Lista.size() - 1).getRecorrido().size() - 1; i++) {
                Aux += Lista.get(Lista.size() - 1).getRecorrido().get(i) + " -> ";
                if (Lista.get(Lista.size() - 1).getRecorrido().get(i).equals(inicio)) {
                    Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(i) + " [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                } else {
                    Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(i) + " [shape = circle, style=filled, fillcolor=white, color=green];";
                }
            }
            Aux += Lista.get(Lista.size() - 1).getRecorrido().get(Lista.get(Lista.size() - 1).getRecorrido().size() - 1) + "[label = \"Ruta" + contador + "\", color=green, style=\"bold\"]; \n";
            Nodos += Lista.get(Lista.size() - 1).getRecorrido().get(Lista.get(Lista.size() - 1).getRecorrido().size() - 1) + " [shape = circle, style=filled, fillcolor=white, color=green];";
        }
        Aux += Nodos;
        Aux += "}";
        System.out.println(Aux);
        Graficar(Aux);
    }

    public static void Graficar(String Cadenas) {
        String dotFilePath = "Mapa.png";
        try {
            Parser parser = new Parser();
            MutableGraph graph = parser.read(Cadenas);
            graph.graphAttrs().add("rankdir", "LR"); // Esto establece la dirección del rango de izquierda a derecha

            // Ajusta la separación entre los rangos para aumentar el espacio entre ellos verticalmente
            graph.graphAttrs().add("ranksep", "5"); // Ajusta la separación entre los rangos verticalmente

            // Ajusta el tamaño de los nodos (opcional)
            graph.nodeAttrs().add("height", "1"); // Altura de los nodos en pulgadas
            graph.nodeAttrs().add("width", "1"); // Ancho de los nodos en pulgadas

            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(dotFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
                // Ajusta la dirección del rango (opcional)
            //graph.graphAttrs().add("rankdir", "TB"); // Esto establece la dirección del rango de arriba hacia abajo
            // Ajusta el tamaño entre nodos (opcional)
            //graph.graphAttrs().add("nodesep", "4"); // Ajusta el espacio entre nodos verticalmente

    public static void SacarCadenarRutasPrueba(ArrayList<NodoArbolB> Lista, ArrayList<Vertices> Grafo, boolean positivo, String inicio) {
        int contador = 1;
        boolean existe = false;
        HashSet<String> visitados = new HashSet<String>();
        String Nodos = "";
        ArrayList<EstructuraGrafica> Ayudador = new ArrayList<>();
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
            for (int i = Lista.size() - 1; i > -1; i--) {
                Nodos += Lista.get(i).getRecorrido().get(0) + " [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                for (int j = 1; j < Lista.get(i).getRecorrido().size(); j++) {
                    for (int k = 0; k < Ayudador.size(); k++) {
                        if (Ayudador.get(k).getRecorrido().equals(Lista.get(i).getRecorrido().get(j - 1) + " -> " + Lista.get(i).getRecorrido().get(j))) {
                            existe = true;
                            Ayudador.get(k).setRutas(Ayudador.get(k).getRutas() + ", Ruta " + contador);
                            if (i == 0) {
                                Ayudador.get(k).setEsFinal(true);
                            }
                        }
                    }
                    if (existe) {
                        existe = false;
                    } else {
                        EstructuraGrafica nuevo = new EstructuraGrafica(Lista.get(i).getRecorrido().get(j - 1) + " -> " + Lista.get(i).getRecorrido().get(j), "Ruta " + contador, false);
                        if (i == 0) {
                            nuevo.setEsFinal(true);
                        }
                        Ayudador.add(nuevo);
                    }

                    if (i == 0) {
                        Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=green];";
                    } else {
                        Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                    }
                }
                contador++;
            }
        } else {
            for (int i = 0; i < Lista.size(); i++) {
                Nodos += Lista.get(i).getRecorrido().get(0) + " [shape = doublecircle, style=filled, fillcolor=white, color=green];";
                for (int j = 1; j < Lista.get(i).getRecorrido().size(); j++) {
                    for (int k = 0; k < Ayudador.size(); k++) {
                        if (Ayudador.get(k).getRecorrido().equals(Lista.get(i).getRecorrido().get(j - 1) + " -> " + Lista.get(i).getRecorrido().get(j))) {
                            existe = true;
                            Ayudador.get(k).setRutas(Ayudador.get(k).getRutas() + ", Ruta " + contador);
                            if (i == Lista.size()-1) {
                                Ayudador.get(k).setEsFinal(true);
                            }
                        }
                    }
                    if (existe) {
                        existe = false;
                    } else {
                        EstructuraGrafica nuevo = new EstructuraGrafica(Lista.get(i).getRecorrido().get(j - 1) + " -> " + Lista.get(i).getRecorrido().get(j), "Ruta " + contador, false);
                        if (i == Lista.size()-1) {
                            nuevo.setEsFinal(true);
                        }
                        Ayudador.add(nuevo);
                    }

                    if (i == Lista.size()-1) {
                        Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=green];"; 
                    } else {
                        Nodos += Lista.get(i).getRecorrido().get(j) + " [shape = circle, style=filled, fillcolor=white, color=blue];";
                    }
                }
                contador++;
            }
        }
        for (EstructuraGrafica estructuraGrafica : Ayudador) {
            String cadenaAux = estructuraGrafica.getRecorrido() + "[label = \"" + estructuraGrafica.getRutas();
            if (estructuraGrafica.isEsFinal()) {
                cadenaAux += "\", color=green];\n";
            } else {
                cadenaAux += "\", color=blue];\n";
            }
            Aux += cadenaAux;
        }
        Aux += Nodos;
        Aux += "}";
        Graficar(Aux);
    }
}
