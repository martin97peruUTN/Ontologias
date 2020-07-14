package GUI;

import GUI.Consulta;
import GUI.Principal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAlumnos extends JPanel {

    private Principal ventana;

    public ListaAlumnos(Principal ventana2) {
        this.ventana=ventana2;
        ventana.setContentPane(this);
        ventana.setPreferredSize(new Dimension(600, 600));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(230, 230, 250));
        this.setLayout(null);

        JButton volverBtn = new JButton("Volver");
        volverBtn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        volverBtn.setBounds(20, 515, 80, 30);
        this.add(volverBtn);

        volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setContentPane(new Consulta(ventana));
                ventana.pack();
            }
        });
    }
}
