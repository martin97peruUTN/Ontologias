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

public class GestorDB {

    public boolean consultarDB(String promedio, String materias, String anio){
        //No se que devuelve todavia

        //TODO terminar la consulta con los otros Strings
        String consulta="SELECT DISTINCT ?x ?lib ?med\n" +
                "WHERE{\n" +
                "  ?x a :PostulanteABecaAdmisible.\n" +
                "  ?x :numeroLegajo ?lib.\n" +
                "  ?x :medicionFinal ?med.\n" +
                "  ?x :seInscribeAConvocatoria ?conv.\n" +
                "  ?conv :anioConvocatoria ?anioConv.\n" +
                "  FILTER (?anioConv = "+anio+").\n" +
                "}\n" +
                "ORDER BY DESC (?med)";

        //Esto es todo de prueba nomas
        Stardog aStardog = Stardog.builder().create();
        try{
            try (AdminConnection aAdminConnection = AdminConnectionConfiguration.toEmbeddedServer().credentials("admin", "admin").connect()) {
                aAdminConnection.disk("testConnectionAPI").create();
                try (Connection aConn = ConnectionConfiguration.to("testConnectionAPI").credentials("admin", "admin").connect()) {

                    aConn.begin();


                }
            }
        }
        finally {
            aStardog.shutdown();
        }

        return true;
    }

    public GestorDB() {

    }
}
