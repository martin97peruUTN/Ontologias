package GUI;

import javax.swing.*;
import java.awt.*;

import DB.*;
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.*;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.query.SelectQueryResult;

public class Principal extends JFrame {
    private GestorDB gestor = new GestorDB();
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                new Principal();
            }
        });
    }

    public Principal() {
        this.setTitle("Ontologias");
        this.setPreferredSize(new Dimension(600, 370));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(new Consulta(this));
        this.pack();

      //Prueba de conexion a Stardog

        Connection aConn = ConnectionConfiguration
                .to("")
                .server("http://localhost:5820")
                .database("TP_OntologiasEjecutado")
                .credentials("admin", "admin")
                .connect()
                .as(Connection.class);

        SelectQuery selectQuery = aConn.select("SELECT DISTINCT ?x \n" +
                "WHERE{ \n" +
                "\t?x a :PostulanteABeca.\n" +
                "}");

        SelectQueryResult selectQueryResult = selectQuery.execute();
        System.out.println(selectQueryResult.toString());

    }

    public GestorDB getGestor() {
        return gestor;
    }

    public void setGestor(GestorDB gestor) {
        this.gestor = gestor;
    }
}