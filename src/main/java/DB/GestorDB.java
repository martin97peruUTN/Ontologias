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
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import com.complexible.common.rdf.query.resultio.HTMLQueryResultWriter;

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

        //Pongo valores por defecto si no meten valores
        if(anio.isEmpty()){
            Date date = new Date();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Buenos Aires"));
            cal.setTime(date);
            Integer year = cal.get(Calendar.YEAR);
            anio=year.toString();
        }
        if(promedio.isEmpty()){
            promedio="6.0";
        }
        if(materias.isEmpty()){
            materias="3";
        }

        SelectQuery selectQuery = aConn.select(
        "SELECT DISTINCT ?Libreta ?Puntaje \n"+
        "WHERE{ \n"+
          "?Alumno a :PostulanteABecaAdmisible. \n"+
          "?Alumno :cantidadMateriasAprobadasCicloLectivoAnterior ?matAnterior. \n"+
          "?Alumno :promedioAlumno ?Promedio. \n"+
          "?Alumno :numeroLegajo ?Libreta. \n"+
          "?Alumno :medicionFinal ?Puntaje. \n"+
          "?Alumno :seInscribeAConvocatoria ?conv. \n"+
          "?conv :anioConvocatoria ?anioConv. \n"+
          "FILTER (?anioConv = "+anio+"). \n"+
          "FILTER (?Promedio >= "+promedio+"). \n"+
          "FILTER (?matAnterior >= "+materias+"). \n"+
        "} \n"+
        "ORDER BY DESC (?Puntaje)"
        );

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectQuery.limit(10);
        SelectQueryResult selectQueryResult = selectQuery.execute();

        try{
            QueryResultWriters.write(selectQueryResult, stream, HTMLQueryResultWriter.FORMAT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }

        String finalString = new String(stream.toByteArray()).replaceAll("\\^\\^&lt;http://www.w3.org/2001/XMLSchema#float&gt;","");

        //TODO Sacar este System.out
        System.out.println(finalString);

        return finalString;
    }

}
