/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

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
    private int HoraInicio = 0;
    private int HoraFinal = 0;
    private int Trafico = 0;
    
    public Nodo(int tiempoVehiculo, int tiempoPie, int consumoGas, int desgastePersona, int distancia) {
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoPie = tiempoPie;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
        this.distancia = distancia;
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

    public int getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(int HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public int getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(int HoraFinal) {
        this.HoraFinal = HoraFinal;
    }

    public int getTrafico() {
        return Trafico;
    }

    public void setTrafico(int Trafico) {
        this.Trafico = Trafico;
    }

    
}
