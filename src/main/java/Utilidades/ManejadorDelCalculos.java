/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import ArbolB.NodoArbolB;
import Grafos.NodoRecorridoDeGrafo;
import Objetos.Nodo;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class ManejadorDelCalculos {

    public ManejadorDelCalculos() {
    }

    public static void ConseguirNodosParaArbol(ArrayList<NodoArbolB> ListaDeArbol, ArrayList<NodoRecorridoDeGrafo> ListaDeNodos, int caso, LocalTime Hora) {
        for (NodoRecorridoDeGrafo ListaDeNodo : ListaDeNodos) {
            int RapidezPie = 0, RapidezV = 0, ConsumoDeGas = 0, ConsumoDePersona = 0, DistanciaTotal = 0;
            LocalTime HI, HF;
            for (int i = 0; i < ListaDeNodo.getDatosTotales().size(); i++) {
                Nodo nodoAux = ListaDeNodo.getDatosTotales().get(i);
                RapidezPie += (int) nodoAux.getDistancia() / nodoAux.getTiempoPie();
                try {
                    boolean llave = true, llaveInterna = true;
                    //arreglo para calcular la hora del traiquin
                    for (int j = 0; j < nodoAux.getHoraFinal().size(); j++) {
                        HI = nodoAux.getHoraInicio().get(j);
                        HF = nodoAux.getHoraFinal().get(j);
                        for (int k = 0; k < 4; k++) {
                            Hora = Hora.plusMinutes(nodoAux.getTiempoVehiculo() / 4);
                            if (Hora.isAfter(HI) && Hora.isBefore(HF)) {
                                if (llaveInterna) {
                                    RapidezV += Math.round(nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo() * (1 + (nodoAux.getTrafico().get(j) / 100.0))) * 100);
                                    llave = false;
                                    llaveInterna = false;
                                }
                            }
                        }
                    }
                    if (llave) {
                        RapidezV += Math.round(nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo()) * 100.00);
                    }
                } catch (NullPointerException e) {
                    RapidezV += Math.round(nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo()) * 100.00);
                }
                ConsumoDeGas += nodoAux.getConsumoGas();
                ConsumoDePersona += nodoAux.getDesgastePersona();
                DistanciaTotal += nodoAux.getDistancia();
            }
            NodoArbolB NuevoNodo = new NodoArbolB();
            NuevoNodo.setRecorrido(ListaDeNodo.getRecorrido());
            switch (caso) {
                //para cuando se busca en base la gasolina
                case 1:
                    NuevoNodo.setCalculo(ConsumoDeGas);
                    break;
                //para cuando se busca en base al desgaste fisico
                case 2:
                    NuevoNodo.setCalculo(ConsumoDePersona);
                    break;
                //para cuando se busca en base a la distancia
                case 3:
                    NuevoNodo.setCalculo(DistanciaTotal);
                    break;
                case 4:
                    //para cuando se busca en base a la gasolina y distancia
                    NuevoNodo.setCalculo(ConsumoDeGas + DistanciaTotal);
                    break;
                case 5:
                    //para cuando se busca en base al desgaste fisico y gasolina
                    NuevoNodo.setCalculo(ConsumoDePersona + DistanciaTotal);
                    break;
                case 6:
                    //para cuando se busca en base a la rapidez del vehiculo
                    NuevoNodo.setCalculo(RapidezV);
                    break;
                case 7:
                    //para cuando se busca en base a la rapidez a pie
                    NuevoNodo.setCalculo(RapidezPie);
                    break;
                default:
                    throw new AssertionError();
            }
            ListaDeArbol.add(NuevoNodo);
        }
    }
}
