/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import InterfazGrafica.MenuPrincipal;

/**
 *
 * @author branp
 */
public class Main {
    public static void main(String[] args){
       MenuPrincipal MenuInicial = new MenuPrincipal();
        MenuInicial.setVisible(true);
        MenuInicial.setLocationRelativeTo(null);
        MenuInicial.setTitle("Menu Principal");
    }
}
