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
    private Connection aConn;

    private GestorDB() {

        //Conexion a la base de datos que corre en localhost
        aConn = ConnectionConfiguration
            .to("")
            .server("http://localhost:5820")
            .database("TP_OntologiasEjecutado")
            .credentials("admin", "admin")
            .connect()
            .as(Connection.class);

    }

    public static GestorDB getInstance(){

        //Patron singleton para que exista un unico GestorBD.
        if (single_instance == null) {
            single_instance = new GestorDB();
        }

        return single_instance;
    }

    public String consultarDB(String promedio, String materias, String anio){

        //Valores por defecto si no se insertan valores
        if(anio.isEmpty()){
            //Se muestran los postulantes del anio actual
            Date date = new Date();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Buenos Aires"));
            cal.setTime(date);
            Integer year = cal.get(Calendar.YEAR);
            anio=year.toString();
        }
        if(promedio.isEmpty()){
            //Promedio minimo por defecto es 6.
            promedio="6.0";
        }
        if(materias.isEmpty()){
            //Materias aprobadas el anio anterior por defecto es 3.
            materias="3";
        }

        //Se crea el string de la consulta a la base de datos.
        SelectQuery selectQuery = aConn.select(
        "SELECT DISTINCT ?LibretaUniversitaria ?Puntaje \n"+
        "WHERE{ \n"+
          "?Alumno a :PostulanteABecaAdmisible. \n"+
          "?Alumno :cantidadMateriasAprobadasCicloLectivoAnterior ?matAnterior. \n"+
          "?Alumno :promedioAlumno ?Promedio. \n"+
          "?Alumno :numeroLegajo ?LibretaUniversitaria. \n"+
          "?Alumno :medicionFinal ?Puntaje. \n"+
          "?Alumno :seInscribeAConvocatoria ?conv. \n"+
          "?conv :anioConvocatoria ?anioConv. \n"+
          "FILTER (?anioConv = "+anio+"). \n"+
          "FILTER (?Promedio >= "+promedio+"). \n"+
          "FILTER (?matAnterior >= "+materias+"). \n"+
        "} \n"+
        "ORDER BY DESC (?Puntaje)"
        );

        //Se crea esta varible porque la mejor forma de mostrar los resultados de la consulta es con QueryResultWriters.write que necesita un ByteArrayOutputStream como parametro.
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        //Se ejecuta la consulta a la base de datos.
        SelectQueryResult selectQueryResult = selectQuery.execute();

        //Aqui escribimos el resultado de la consulta en la varible ByteArrayOutputStream y con un formato de tabla con codigo HTML
        try{
            //este metodo viene con las librerias de stardog
            QueryResultWriters.write(selectQueryResult, stream, HTMLQueryResultWriter.FORMAT);
        } catch (IOException e) {
            System.out.println("ERROR");
        }

        //Se crea el String final a partir del ByteArrayOutputStream que se mostrara en el label del ranking de alumno. A dicho string le aplicamos el replaceAll para que quede "lindo" para el usuario y no muestre to do el IRI.
        //Lo mas dificil fue sacar los ^ y los > ya que HTML los considera caracteres especiales.
        String finalString = new String(stream.toByteArray()).replaceAll("\\^\\^&lt;http://www.w3.org/2001/XMLSchema#float&gt;","");

        return finalString;
    }

}
