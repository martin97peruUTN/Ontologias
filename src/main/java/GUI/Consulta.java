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
        ventana.setPreferredSize(new Dimension(600, 300));
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

        JLabel materiasLbl = new JLabel("Minimo de materias cursadas el año anterior");
        materiasLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        materiasLbl.setBounds(20, 120, 380, 45);
        this.add(materiasLbl);

        JTextField promedioTxt = new JTextField();
        promedioTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        promedioTxt.setBounds(20,85,380,25);
        this.add(promedioTxt);

        JTextField materiasTxt = new JTextField();
        materiasTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        materiasTxt.setBounds(20,155,380,25);
        this.add(materiasTxt);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnSalir.setBounds(20, 215, 80, 30);
        this.add(btnSalir);

        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        btnContinuar.setBounds(465, 215, 100, 30);
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
                ventana.gestor.consultarDB(promedioTxt.getText(),materiasTxt.getText());
                //Aca se podria mandar el resultado de la consulta mediante new ListaAlumnos(ventana, resultadoConsulta)
                //No lo hice todavia porque no se que devuelve la BD ni que tendriamos que usar

                //Otra opcion es que el mismo gestor tenga una variable para guardar el resultado
                ventana.setContentPane(new ListaAlumnos(ventana));
                ventana.pack();
            }
        });
    }

}