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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class ManejadorDelCalculos {

    public ManejadorDelCalculos() {
    }

    public static void ConseguirNodosParaArbol(ArrayList<NodoArbolB> ListaDeArbol, ArrayList<NodoRecorridoDeGrafo> ListaDeNodos, int caso, LocalTime Hora) {
        int constante = 100;
        for (NodoRecorridoDeGrafo ListaDeNodo : ListaDeNodos) {
            int RapidezPie = 0, RapidezV = 0, ConsumoDeGas = 0, ConsumoDePersona = 0, DistanciaTotal = 0;
            LocalTime HI, HF, HoraEnviada, HoraAPatin;
            HoraEnviada = Hora;
            HoraAPatin = Hora;
            for (int i = 0; i < ListaDeNodo.getDatosTotales().size(); i++) {
                Nodo nodoAux = ListaDeNodo.getDatosTotales().get(i);
                RapidezPie += Math.round(nodoAux.getDistancia() / nodoAux.getTiempoPie()*constante);
                HoraAPatin = HoraAPatin.plusMinutes(nodoAux.getTiempoPie());
                try {
                    boolean llave = true, llaveInterna = true;
                    //arreglo para calcular la hora del traiquin
                    for (int j = 0; j < nodoAux.getHoraFinal().size(); j++) {
                        HI = nodoAux.getHoraInicio().get(j);
                        HF = nodoAux.getHoraFinal().get(j);
                        for (int k = 0; k < 4; k++) {
                            int minsAumento = Math.round(nodoAux.getTiempoVehiculo() / 4);
                            Hora = Hora.plusMinutes(minsAumento);
                            if (Hora.isAfter(HI) && Hora.isBefore(HF)) {
                                if (llaveInterna) {
                                    RapidezV += Math.round((nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo() * (1 + (nodoAux.getTrafico().get(j) / constante))) * constante));
                                    llave = false;
                                    llaveInterna = false;
                                }
                            }
                        }
                    }
                    if (llave) {
                        RapidezV += Math.round(nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo()) * constante);
                    }
                } catch (NullPointerException e) {
                    RapidezV += Math.round(nodoAux.getDistancia() / (nodoAux.getTiempoVehiculo()) * constante);
                    Hora = Hora.plusMinutes(nodoAux.getTiempoVehiculo());
                }
                ConsumoDeGas += nodoAux.getConsumoGas();
                ConsumoDePersona += nodoAux.getDesgastePersona();
                DistanciaTotal += nodoAux.getDistancia();
            }
            NodoArbolB NuevoNodo = new NodoArbolB();
            NuevoNodo.setRecorrido(ListaDeNodo.getRecorrido());
            long minutosDiferencia;
            int minsDeViaje, Promedio, Resultado;
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
                    // Calcular la diferencia en minutos
                    minutosDiferencia = HoraEnviada.until(Hora, ChronoUnit.MINUTES);
                    // Convertir la diferencia a un int
                    minsDeViaje = Math.round(minutosDiferencia);
                    if (minsDeViaje < 0) {
                        minsDeViaje += 24 * 60; // Sumar un día en minutos
                    }
                    Promedio = ListaDeNodo.getRecorrido().size() - 1;
                    Resultado = (RapidezV / Promedio) - minsDeViaje;
                    NuevoNodo.setCalculo(Resultado);
                    break;
                case 7:
                    //para cuando se busca en base a la rapidez a pie
                    minutosDiferencia = HoraEnviada.until(HoraAPatin, ChronoUnit.MINUTES);
                    // Convertir la diferencia a un int
                    minsDeViaje = Math.round(minutosDiferencia);
                    if (minsDeViaje < 0) {
                        minsDeViaje += 24 * 60; // Sumar un día en minutos
                    }
                    Promedio = ListaDeNodo.getRecorrido().size() - 1;
                    NuevoNodo.setCalculo((RapidezPie/Promedio)-minsDeViaje);
                    break;
                default:
                    throw new AssertionError();
            }
            ListaDeArbol.add(NuevoNodo);
        }
    }
}
