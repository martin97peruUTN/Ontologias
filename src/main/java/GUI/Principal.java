package GUI;

import javax.swing.*;
import java.awt.*;
import DB.*;

public class Principal extends JFrame {
    GestorDB gestor = new GestorDB();
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new Principal();
            }
        });
    }

    public Principal(){
        this.setTitle("Ontologias");
        this.setPreferredSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(new Consulta(this));
        this.pack();

    }
}