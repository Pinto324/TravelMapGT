/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

/**
 *
 * @author branp
 */
public class ArbolB {

    NodoB raiz;
    int gradoMinimo;

    public ArbolB(int gradoMinimo) {
        this.gradoMinimo = gradoMinimo;
        this.raiz = null;
    }

    public NodoB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoB raiz) {
        this.raiz = raiz;
    }

    //se inserta según el entero distintos parametros necesitados, 1)gasolina/DesgasteFisico, 2:Distancia, 3:Gasolina/Desgaste distancia, 4: Compuesta.
    public void insertar(NodoArbolB Recorrido, int Caso, boolean EsVehiculo) {
        if (raiz == null) {
            raiz = new NodoB(gradoMinimo, true);
            raiz.datos[0] = Recorrido;
            raiz.numDatos = 1;
        } else {
            if (raiz.numDatos == 2 * gradoMinimo - 1) {
                NodoB nuevaRaiz = new NodoB(gradoMinimo, false);
                nuevaRaiz.hijos.add(raiz);
                switch (Caso) {
                    case 1:
                        dividirHijoGasolina(nuevaRaiz, 0, raiz);
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                raiz = nuevaRaiz;
            }
            switch (Caso) {
                case 1:
                    insertarNoLlenoGasolina(raiz, Recorrido, EsVehiculo);
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }
    //inserción caso 1 La mejor ruta en base a la gasolina si es vehículo.

    private void dividirHijoGasolina(NodoB padre, int indice, NodoB hijo) {
        NodoB nuevoHijo = new NodoB(hijo.gradoMinimo, hijo.hoja);
        nuevoHijo.numDatos = gradoMinimo - 1;

        for (int i = 0; i < gradoMinimo - 1; i++) {
            nuevoHijo.datos[i] = hijo.datos[i + gradoMinimo];
        }

        if (!hijo.hoja) {
            for (int i = 0; i < gradoMinimo; i++) {
                nuevoHijo.hijos.add(hijo.hijos.get(i + gradoMinimo));
            }
            for (int i = 0; i < gradoMinimo; i++) {
                hijo.hijos.remove(gradoMinimo);
            }
        }

        for (int i = padre.numDatos; i > indice; i--) {
            padre.hijos.add(i + 1, padre.hijos.get(i));
        }

        padre.hijos.add(indice + 1, nuevoHijo);

        for (int i = padre.numDatos - 1; i >= indice; i--) {
            padre.datos[i + 1] = padre.datos[i];
        }

        padre.datos[indice] = hijo.datos[gradoMinimo - 1];
        hijo.numDatos = gradoMinimo - 1;
        padre.numDatos++;
    }

    private void insertarNoLlenoGasolina(NodoB nodo, NodoArbolB clave, boolean EsVehiculo) {
        int i = nodo.numDatos - 1;
        int Actual, Nuevo;
        //condicional para cambiar el parametro si va en vehiculo o pie
        if (EsVehiculo) {
            Actual = nodo.datos[i].getDatosTotales().getConsumoGas();
            Nuevo = clave.getDatosTotales().getConsumoGas();
        } else {
            Actual = nodo.datos[i].getDatosTotales().getDesgastePersona();
            Nuevo = clave.getDatosTotales().getDesgastePersona();
        }
        if (nodo.hoja) {
            while (i >= 0 && Nuevo < Actual) {
                i--;
                if (i >= 0) { // Verifica que i sea mayor o igual a 0 antes de acceder a nodo.datos[i]
                    if (EsVehiculo) {
                        Actual = nodo.datos[i].getDatosTotales().getConsumoGas();
                    } else {
                        Actual = nodo.datos[i].getDatosTotales().getDesgastePersona();
                    }
                }
            }
            nodo.datos[i + 1] = clave;
            nodo.numDatos++;
        } else {
            while (i >= 0 && Nuevo < Actual) {
                i--;
                if (EsVehiculo) {
                    Actual = nodo.datos[i].getDatosTotales().getConsumoGas();
                } else {
                    Actual = nodo.datos[i].getDatosTotales().getDesgastePersona();
                }
            }
            i++;
            if (nodo.hijos.get(i).numDatos == 2 * gradoMinimo - 1) {
                dividirHijoGasolina(nodo, i, nodo.hijos.get(i));
                if (EsVehiculo) {
                    Actual = nodo.datos[i].getDatosTotales().getConsumoGas();
                } else {
                    Actual = nodo.datos[i].getDatosTotales().getDesgastePersona();
                }
                if (Nuevo > Actual) {
                    i++;
                }
            }
            insertarNoLlenoGasolina(nodo.hijos.get(i), clave, EsVehiculo);
        }
    }
    // Método para recorrer el árbol B e imprimir sus claves en orden

    public void recorrerArbol(NodoB nodo) {
        if (nodo != null) {
            int i;
            for (i = 0; i < nodo.numDatos; i++) {
                // Recorrer el subárbol izquierdo si no es una hoja
                if (!nodo.hoja) {
                    recorrerArbol(nodo.hijos.get(i));
                }
                // Imprimir la clave
                System.out.print("Recorrido " + i);
                for (int j = 0; j < nodo.datos[i].getRecorrido().size(); j++) {
                    System.out.print(nodo.datos[i].getRecorrido().get(j) + "\n");
                }

                // Recorrer el subárbol derecho si no es una hoja y no es la última clave
                if (!nodo.hoja && i == nodo.numDatos - 1) {
                    recorrerArbol(nodo.hijos.get(i + 1));
                }
            }
        }
    }

    // Método de prueba para recorrer y mostrar el contenido del árbol B
    public void mostrarContenido() {
        recorrerArbol(raiz);
        System.out.println(); // Agregar un salto de línea al final para mayor claridad
    }

}
