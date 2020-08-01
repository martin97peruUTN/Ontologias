package GUI;

import javax.swing.*;
import java.awt.*;

import DB.*;

public class Principal extends JFrame {
    private GestorDB gestor = GestorDB.getInstance();
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new Principal();
            }
        });
    }

    public Principal() {
        //Se crea el frame principal y se setea la primer pantalla de consulta
        this.setTitle("Ontolog\u00edas");
        this.setPreferredSize(new Dimension(440, 370));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(new Consulta(this));
        this.pack();
    }

    public GestorDB getGestor() {
        return gestor;
    }

    public void setGestor(GestorDB gestor) {
        this.gestor = gestor;
    }
}