/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesb;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;

/**
 *
 * @author Usuario
 */
public class Imagen extends javax.swing.JPanel{
    private String Nombre;
    private int ancho;
    private int alto;
    public Imagen(String Name, int ancho, int alto) {
        this.setSize(ancho, alto); 
        this.Nombre = Name;
    }
    
    public void paint(Graphics grafico) {
        Dimension height = getSize();
        ImageIcon Img = new ImageIcon(getClass().getResource(Nombre)); 
        grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
        setOpaque(false);
        super.paintComponent(grafico);
    }
    
}
