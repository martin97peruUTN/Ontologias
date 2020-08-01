package GUI;

import GUI.Principal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consulta extends JPanel {

    private Principal ventana;

    public Consulta(Principal ventana2) {
        //estos dos strings son para escribir enies y acentos.
        String caracterEnie = "\u00f1";
        String acentoI = "\u00ed";

        //se crea la pantalla de la consulta
        this.ventana=ventana2;
        ventana.setContentPane(this);
        ventana.setPreferredSize(new Dimension(440, 370));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setBackground(new Color(201,236,249));
        this.setLayout(null);

        JLabel tituloLbl = new JLabel("Ontolog"+acentoI+"as");
        tituloLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        tituloLbl.setBounds(20, 0, 437, 62);
        this.add(tituloLbl);

        JLabel promedioLbl = new JLabel("Promedio m"+acentoI+"nimo");
        promedioLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        promedioLbl.setBounds(20, 50, 380, 45);
        this.add(promedioLbl);

        JTextField promedioTxt = new JTextField();
        promedioTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        promedioTxt.setBounds(20,85,380,25);
        this.add(promedioTxt);

        JLabel materiasLbl = new JLabel("M"+acentoI+"nimo de materias cursadas el a"+caracterEnie+"o anterior");
        materiasLbl.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        materiasLbl.setBounds(20, 120, 380, 45);
        this.add(materiasLbl);

        JTextField materiasTxt = new JTextField();
        materiasTxt.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        materiasTxt.setBounds(20,155,380,25);
        this.add(materiasTxt);

        JLabel anioLbl = new JLabel("A"+caracterEnie+"o de la pasant"+acentoI+"a");
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
                //ver si ingresaron numeros
                if(validarStringIngresados(promedioTxt.getText(), anioTxt.getText(), materiasTxt.getText())){
                    //Se llama al metodo para que realice la consulta en la base de datos y la devuelve en un string
                    String queryResult = ventana.getGestor().consultarDB(promedioTxt.getText(),materiasTxt.getText(),anioTxt.getText());

                    //Llamamos una nueva pantalla para mostrar los resultados, que se envian en el string queryResult.
                    ventana.setContentPane(new ListaAlumnos(ventana, queryResult));
                    ventana.pack();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Valores no v\u00e1lidos.");
                }

            }
        });
    }

        private boolean validarStringIngresados(String promedio, String anio, String materias){

             boolean datosValidos = true;

             if (!promedio.isEmpty()){
                 try{
                     Float.parseFloat(promedio);
                 }
                 catch (Exception e){
                     datosValidos = false;
                 }
             }

             if(!anio.isEmpty()){
                try{
                    Integer.parseInt(anio);
                }
                catch (Exception e){
                    datosValidos = false;
                  }
                }

             if(!materias.isEmpty()){
                 try{
                   Integer.parseInt(materias);
                 }
                 catch (Exception e){
                     datosValidos = false;
                 }
             }
             return datosValidos;
        }

}