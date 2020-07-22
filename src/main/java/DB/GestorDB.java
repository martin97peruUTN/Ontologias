package DB;

import java.util.ArrayList;
import com.complexible.stardog.*;
import com.stardog.*;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorDB {

    public boolean conectarDB(){
        //No se que devuelve todavia
        //TODO conectarse a la DB
        return true;
    }

    public boolean consultarDB(String promedio, String materias, String anio){
        //No se que devuelve todavia

        //TODO hacer la consulta
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
        return true;
    }

    public GestorDB() {

    }
}
