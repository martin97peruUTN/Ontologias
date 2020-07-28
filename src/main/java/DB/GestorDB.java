package DB;

import java.util.ArrayList;
import com.complexible.stardog.*;
import com.stardog.*;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.Getter;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;

import com.stardog.stark.IRI;
import com.stardog.stark.Statement;
import com.stardog.stark.Values;
import com.stardog.stark.io.RDFFormats;
import com.stardog.stark.io.RDFWriters;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultFormats;
import com.stardog.stark.query.io.QueryResultWriters;
import com.stardog.stark.vocabs.RDF;
import com.stardog.stark.query.SelectQueryResult;
import java.io.*;

/*
imports sacados de Principal cuando movi todo lo de la DB aca
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.*;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.stardog.stark.query.SelectQueryResult;
import com.stardog.stark.query.io.QueryResultFormats;
import com.stardog.stark.query.io.QueryResultWriters;
import com.complexible.common.rdf.query.resultio.TextTableQueryResultWriter;
 */

public class GestorDB {

    private static GestorDB single_instance = null;

    private GestorDB() {

    }

    public static GestorDB getInstance(){

        if (single_instance == null) {
            single_instance = new GestorDB();
        }
        return single_instance;
    }

    public String consultarDB(String promedio, String materias, String anio){

        Connection aConn = ConnectionConfiguration
                .to("")
                .server("http://localhost:5820")
                .database("TP_OntologiasEjecutado")
                .credentials("admin", "admin")
                .connect()
                .as(Connection.class);

        /*
        SELECT DISTINCT ?x ?lib ?med
        WHERE{
          ?x a :PostulanteABecaAdmisible.
          ?x :numeroLegajo ?lib.
          ?x :medicionFinal ?med.
          ?x :seInscribeAConvocatoria ?conv.
          ?conv :anioConvocatoria ?anioConv.
          FILTER (?anioConv = 2020).
        }
        ORDER BY DESC (?med)
         */
        //TODO Hacer bien la query, esta es de prueba
        SelectQuery selectQuery = aConn.select("SELECT DISTINCT ?x \n" +
                "WHERE{ \n" +
                "\t?x a :PostulanteABeca.\n" +
                "}");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        SelectQueryResult selectQueryResult = selectQuery.execute();

        try{
            QueryResultWriters.write(selectQueryResult, stream, TextTableQueryResultWriter.FORMAT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }

        //TODO Sacar este System.out
        String finalString = new String(stream.toByteArray());
        System.out.println(finalString);

        return finalString;
    }

}
