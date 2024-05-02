/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolB;

import java.util.ArrayList;

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
    public void insertar(NodoArbolB Recorrido) {
        if (raiz == null) {
            raiz = new NodoB(gradoMinimo, true);
            raiz.datos[0] = Recorrido;
            raiz.numDatos = 1;
        } else {
            if (raiz.numDatos == 2 * gradoMinimo - 1) {
                NodoB nuevaRaiz = new NodoB(gradoMinimo, false);
                nuevaRaiz.hijos.add(raiz);
                dividirHijo(nuevaRaiz, 0, raiz);
                raiz = nuevaRaiz; // Actualizamos la raíz con la nueva raíz creada
                insertar(raiz, Recorrido); // Insertamos en la nueva raíz
            } else {
                insertar(raiz, Recorrido); // Insertamos en la raíz existente
            }
        }
    }
    //inserción caso 1 La mejor ruta en base a la gasolina si es vehículo.

    private void dividirHijo(NodoB padre, int indice, NodoB hijo) {
        NodoB nuevoHijo = new NodoB(hijo.gradoMinimo, hijo.hoja);
        nuevoHijo.numDatos = gradoMinimo - 1;
        // Copiar datos al nuevo hijo
        for (int i = 0; i < gradoMinimo - 1; i++) {
            nuevoHijo.datos[i] = hijo.datos[i + gradoMinimo];
            hijo.datos[i + gradoMinimo] = null; // Limpiar datos del hijo original
        }
        // Copiar hijos al nuevo hijo si no es hoja
        if (!hijo.hoja) {
            for (int i = 0; i < gradoMinimo; i++) {
                nuevoHijo.hijos.add(hijo.hijos.get(i + gradoMinimo));
                hijo.hijos.set(i + gradoMinimo, null); // Limpiar hijos del hijo original
            }
        }
        // Ajustar los datos y los hijos del padre
        for (int i = padre.numDatos; i > indice; i--) {
            padre.hijos.add(i + 1, padre.hijos.get(i));
        }
        padre.hijos.add(indice + 1, nuevoHijo);

        for (int i = padre.numDatos - 1; i >= indice; i--) {
            padre.datos[i + 1] = padre.datos[i];
        }
        padre.datos[indice] = hijo.datos[gradoMinimo - 1];
        hijo.datos[gradoMinimo - 1] = null; // Limpiar el dato copiado al padre
        hijo.numDatos = gradoMinimo - 1;
        padre.numDatos++;
    }

    private void insertar(NodoB nodo, NodoArbolB clave) {
        int i = nodo.numDatos - 1;
        if (nodo.hoja) {
            // Si es una hoja, simplemente insertamos el dato en la posición correcta
            while (i >= 0 && clave.getCalculo() < nodo.datos[i].getCalculo()) {
                nodo.datos[i + 1] = nodo.datos[i];
                i--;
            }
            nodo.datos[i + 1] = clave;
            nodo.numDatos++;
        } else {
            while (i >= 0 && clave.getCalculo() < nodo.datos[i].getCalculo()) {
                i--;
            }
            i++; // Aumentamos i después del bucle
            if (nodo.hijos.get(i).numDatos == 2 * gradoMinimo - 1) {
                dividirHijo(nodo, i, nodo.hijos.get(i));
                // Después de dividir, si la clave es mayor que el dato en la posición i,
                // necesitamos mover a la siguiente posición para insertarla en el hijo adecuado
                if (clave.getCalculo() > nodo.datos[i].getCalculo()) {
                    i++;
                }
            }
            // Luego, insertamos en el hijo correspondiente después de la posible división
            insertar(nodo.hijos.get(i), clave);
        }
    }

    // Método para recorrer el árbol B e imprimir sus claves en orden
    public void recorrerArbol(NodoB nodo) {
        if (nodo != null) {
            int i;
            for (i = 0; i < nodo.numDatos; i++) {
                try {
                    // Recorrer el subárbol izquierdo si no es una hoja
                    if (!nodo.hoja) {
                        recorrerArbol(nodo.hijos.get(i));
                    }
                    // Imprimir la clave
                    System.out.print("Recorrido: " + i + "\n");
                    for (int j = 0; j < nodo.datos[i].getRecorrido().size(); j++) {
                        System.out.print(nodo.datos[i].getRecorrido().get(j) + "|");
                    }
                    System.out.println();
                    // Recorrer el subárbol derecho si no es una hoja y no es la última clave
                    if (!nodo.hoja && i == nodo.numDatos - 1) {
                        recorrerArbol(nodo.hijos.get(i + 1));
                    }
                } catch (NullPointerException e) {

                }
            }
        }
    }
    
public void MeterOrdenado(NodoB nodo, ArrayList<NodoArbolB> ListaOrdenada) {
    if (nodo != null) {
        int i;
        // Recorrer todos los hijos
        for (i = 0; i < nodo.numDatos; i++) {
            try {
                // Recorrer el subárbol izquierdo si no es una hoja
                if (!nodo.hoja) {
                    MeterOrdenado(nodo.hijos.get(i), ListaOrdenada);
                }
                
                // Agregar el nodo actual a la lista
                ListaOrdenada.add(nodo.datos[i]);
                
                // Recorrer el subárbol derecho si no es una hoja y no es la última clave
                if (!nodo.hoja && i == nodo.numDatos - 1) {
                    MeterOrdenado(nodo.hijos.get(i + 1), ListaOrdenada);
                }
            } catch (NullPointerException e) {
                // Manejar la excepción si es necesario
            }
        }
    }
}


    // Método de prueba para recorrer y mostrar el contenido del árbol B
    public void mostrarContenido() {
        recorrerArbol(raiz);
        System.out.println(); // Agregar un salto de línea al final para mayor claridad
    }
        // Método de prueba para recorrer y mostrar el contenido del árbol B
    public void meterOrdenado(ArrayList<NodoArbolB> ListaOrdenada) {
        MeterOrdenado(raiz,ListaOrdenada);
        System.out.println(); // Agregar un salto de línea al final para mayor claridad
    }

}
