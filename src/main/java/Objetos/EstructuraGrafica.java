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
public class EstructuraGrafica {
    private String Recorrido;
    private String Rutas;
    private boolean EsFinal;

    public EstructuraGrafica(String Recorrido, String Rutas, boolean EsFinal) {
        this.Recorrido = Recorrido;
        this.Rutas = Rutas;
        this.EsFinal = EsFinal;
    }

    public String getRecorrido() {
        return Recorrido;
    }

    public void setRecorrido(String Recorrido) {
        this.Recorrido = Recorrido;
    }

    public String getRutas() {
        return Rutas;
    }

    public void setRutas(String Rutas) {
        this.Rutas = Rutas;
    }

    public boolean isEsFinal() {
        return EsFinal;
    }

    public void setEsFinal(boolean EsFinal) {
        this.EsFinal = EsFinal;
    }
    
}
