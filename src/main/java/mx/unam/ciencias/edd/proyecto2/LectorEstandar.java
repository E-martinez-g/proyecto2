package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase para lectores desde la entrada estándar.
 */
public class LectorEstandar {

    /**
     * Constructor para leer de la entrada estándar.
     */
    public LectorEstandar {
	try {
	    lector = new BufferedReader(
			 new InputStreamReader(System.in, "utf-8"));
	} catch (IOException ioe) {
	    System.err.println("\nOcurrió un error al conectarse " +
			       "a la entrada estándar.\n");
	    System.exit(1);
	}
    }
}
