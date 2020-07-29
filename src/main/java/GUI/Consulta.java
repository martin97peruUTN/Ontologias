package GUI;

import GUI.Principal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consulta extends JPanel {

    private Principal ventana;

    //TODO en el TP pusimos que iba a tener un LogIn, no se si tenemos que hacerlo jaja

    public Consulta(Principal ventana2) {
        this.ventana=ventana2;
        ventana.setContentPane(this);
        ventana.setPreferredSize(new Dimension(440, 370));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(230, 230, 250));
        this.setLayout(null);

        JLabel tituloLbl = new JLabel("Ontologias");
        tituloLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        tituloLbl.setBounds(20, 0, 437, 62);
        this.add(tituloLbl);

        JLabel promedioLbl = new JLabel("Promedio minimo");
        promedioLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        promedioLbl.setBounds(20, 50, 380, 45);
        this.add(promedioLbl);

        JTextField promedioTxt = new JTextField();
        promedioTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        promedioTxt.setBounds(20,85,380,25);
        this.add(promedioTxt);

        JLabel materiasLbl = new JLabel("Minimo de materias cursadas el año anterior");
        materiasLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        materiasLbl.setBounds(20, 120, 380, 45);
        this.add(materiasLbl);

        JTextField materiasTxt = new JTextField();
        materiasTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        materiasTxt.setBounds(20,155,380,25);
        this.add(materiasTxt);

        JLabel anioLbl = new JLabel("Año de la pasantia");
        anioLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        anioLbl.setBounds(20, 190, 380, 45);
        this.add(anioLbl);

        JTextField anioTxt = new JTextField();
        anioTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        anioTxt.setBounds(20,225,380,25);
        this.add(anioTxt);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnSalir.setBounds(20, 280, 80, 30);
        this.add(btnSalir);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnContinuar.setBounds(300, 280, 100, 30);
        this.add(btnContinuar);

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO ver si ingresaron numeros
                String queryResult = ventana.getGestor().consultarDB(promedioTxt.getText(),materiasTxt.getText(),anioTxt.getText());

                ventana.setContentPane(new ListaAlumnos(ventana, queryResult));
                ventana.pack();
            }
        });
    }

}