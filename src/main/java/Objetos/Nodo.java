/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class Nodo {

    private int tiempoVehiculo;
    private int tiempoPie;
    private int consumoGas;
    private int desgastePersona;
    private int distancia;
    private ArrayList<LocalTime> HoraInicio; // 10:30 HoraInicio = 0;
    private ArrayList<LocalTime> HoraFinal;
    private ArrayList<Integer> Trafico;
    
    public Nodo(int tiempoVehiculo, int tiempoPie, int consumoGas, int desgastePersona, int distancia) {
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoPie = tiempoPie;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
        this.distancia = distancia;
        HoraInicio = new ArrayList<>(); // 10:30 HoraInicio = 0;
        HoraFinal = new ArrayList<>();
        Trafico = new ArrayList<>();
    }

    public int getTiempoVehiculo() {
        return tiempoVehiculo;
    }

    public void setTiempoVehiculo(int tiempoVehiculo) {
        this.tiempoVehiculo = tiempoVehiculo;
    }

    public int getTiempoPie() {
        return tiempoPie;
    }

    public void setTiempoPie(int tiempoPie) {
        this.tiempoPie = tiempoPie;
    }

    public int getConsumoGas() {
        return consumoGas;
    }

    public void setConsumoGas(int consumoGas) {
        this.consumoGas = consumoGas;
    }

    public int getDesgastePersona() {
        return desgastePersona;
    }

    public void setDesgastePersona(int desgastePersona) {
        this.desgastePersona = desgastePersona;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public ArrayList<LocalTime> getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(ArrayList<LocalTime> HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public ArrayList<LocalTime> getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(ArrayList<LocalTime> HoraFinal) {
        this.HoraFinal = HoraFinal;
    }

    public ArrayList<Integer> getTrafico() {
        return Trafico;
    }

    public void setTrafico(ArrayList<Integer> Trafico) {
        this.Trafico = Trafico;
    }
  
}
