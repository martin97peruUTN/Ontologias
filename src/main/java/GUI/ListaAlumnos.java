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

    public ListaAlumnos(Principal ventana2, String queryResult) {

        //Se arma la pantalla para mostrar los resultados.
        this.ventana=ventana2;
        ventana.setContentPane(this);
        ventana.setPreferredSize(new Dimension(440, 370));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(201,236,249));
        this.setLayout(null);

        JLabel tituloLbl = new JLabel("Ranking Postulantes a Becas");
        tituloLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        tituloLbl.setBounds(0,20,370,50);
        tituloLbl.setVerticalAlignment(JLabel.TOP);
        tituloLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(tituloLbl);

        //Se crea un label para mostrar el string resultado.
        JLabel resultadoLbl = new JLabel(queryResult);
        resultadoLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        resultadoLbl.setBounds(35,60,300,400);
        resultadoLbl.setVerticalAlignment(JLabel.TOP);
        resultadoLbl.setHorizontalAlignment(JLabel.CENTER);
        this.add(resultadoLbl);

        JButton volverBtn = new JButton("Volver");
        volverBtn.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        volverBtn.setBounds(20, 280, 80, 30);
        volverBtn.setVerticalAlignment(JLabel.BOTTOM);
        volverBtn.setHorizontalAlignment(JLabel.CENTER);
        this.add(volverBtn);

        //volver a la pantalla de la consulta
        volverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setContentPane(new Consulta(ventana));
                ventana.pack();
            }
        });
    }
}
