/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Objetos.Arista;
import Objetos.Nodo;
import Objetos.Vertices;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class ManejadorDeArchivos {
    public ArrayList<Vertices> leerArchivo(String nombreArchivo) {
        ArrayList<Vertices> Grafo = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 7) {
                    //separamos los elementos
                    String origen = partes[0];
                    String destino = partes[1];
                    int tiempoVehiculo = Integer.parseInt(partes[2]);
                    int tiempoPie = Integer.parseInt(partes[3]);
                    int consumoGas = Integer.parseInt(partes[4]);
                    int desgastePersona = Integer.parseInt(partes[5]);
                    int distancia = Integer.parseInt(partes[6]);
                    //Primero creamos los nodos, luego las aristas y de ultimo los vertices y los agregamos a la lista a mandar
                    Nodo NuevoNodo = new Nodo(tiempoVehiculo, tiempoPie, consumoGas, desgastePersona, distancia);
                    Arista NuevaArista = new Arista(destino,NuevoNodo);
                    ArrayList<Arista> ExisteVertice = ManejadorDelGrafo.DevolverArregloArista(Grafo,origen); 
                    if(ExisteVertice!=null){
                        //Existen vertices
                        if(ManejadorDelGrafo.ExisteArista(Grafo,origen,destino)){
                            //actualizamos el nodo existente
                            ManejadorDelGrafo.ActualizarArista(Grafo,origen,destino,NuevoNodo);
                        }else{
                            //inseratamos una nueva arista en el vertice
                            ManejadorDelGrafo.InsertarArista(Grafo,origen,NuevaArista);
                        }
                    }else{
                        //no existe vertice, creamos el nuevo vertice
                        Vertices nuevo = new Vertices(origen);
                        nuevo.getAristas().add(NuevaArista);
                        Grafo.add(nuevo);
                    }
                } else {
                    // Manejar el caso de una línea con un formato incorrecto
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.err.println("Error de formato en el archivo.");
        }

        return Grafo;
    }
}
