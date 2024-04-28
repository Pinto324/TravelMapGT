/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilidadesGUI;

import java.io.File;
import javax.swing.Icon;
import javax.swing.filechooser.FileView;

/**
 *
 * @author branp
 */
public class Personalizacion extends FileView{
     @Override
        public Icon getIcon(File f) {
            // Personaliza el icono de los archivos (opcional)
            return super.getIcon(f);
        }

        @Override
        public String getName(File f) {
            // Personaliza el nombre de los archivos (opcional)
            return super.getName(f);
        }

        @Override
        public String getDescription(File f) {
            // Personaliza la descripción de los archivos (opcional)
            return super.getDescription(f);
        }

        @Override
        public String getTypeDescription(File f) {
            // Personaliza el tipo de descripción de los archivos (opcional)
            return super.getTypeDescription(f);
        }

        @Override
        public Boolean isTraversable(File f) {
            // Personaliza la opción de navegación en los archivos (opcional)
            return super.isTraversable(f);
        }
}
