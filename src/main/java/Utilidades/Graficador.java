/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Objetos.Vertices;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author branp
 */
public class Graficador {

    public Graficador() {
    }
    
    public static void SacarCadenas(ArrayList<Vertices> Grafo){
        String Cadena = "";
        for (int i = 0; i < Grafo.size(); i++) {
            for (int j = 0; j < Grafo.get(i).getAristas().size(); j++) {
                Cadena += Grafo.get(i).getOrigen()+"->"+Grafo.get(i).getAristas().get(j).getLugar()+";\n";
            }
        }
        Graficar(Cadena);
    }
    
    public static void Graficar(String Cadenas){
        String Aux="digraph finite_state_machine {\n" +
"	fontname=\"Helvetica,Arial,sans-serif\"\n" +
"	node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
"	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
"	rankdir=LR;\n" +
"	node [shape = circle];\n"+Cadenas+"\n}";
        String dotFilePath = "Mapa.png";
        //String dotFilePath = "graph.dot";
        try{
            Parser parser = new Parser();
            MutableGraph graph = parser.read(Aux);
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(dotFilePath));
        }catch(IOException e){
        
        }

    
      // Crear el archivo DOT
       // generarArchivoDOT(dotFilePath, Aux);

        // Convertir el archivo DOT a una imagen usando Graphviz
        //convertirADOTAImagen(dotFilePath, "Mapa.png");
    }

    private static void generarArchivoDOT(String filePath, String subcadenas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(subcadenas);
            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertirADOTAImagen(String dotFilePath, String imageFilePath) {
        try {
            // Ejecutar el comando dot para convertir DOT a imagen
            String command = "dot -Tpng " + dotFilePath + " -o " + imageFilePath;
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            // Imprimir salida del proceso (útil para depuración)
            java.util.Scanner scanner = new java.util.Scanner(process.getInputStream()).useDelimiter("\\A");
            System.out.println(scanner.hasNext() ? scanner.next() : "");
            
            // Imprimir errores del proceso (útil para depuración)
            java.util.Scanner scannerError = new java.util.Scanner(process.getErrorStream()).useDelimiter("\\A");
            System.err.println(scannerError.hasNext() ? scannerError.next() : "");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
