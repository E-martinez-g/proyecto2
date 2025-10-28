package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase para lectores desde la entrada estándar.
 */
public class LectorEstandar extends Lector {

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

    /**
     * Regresa una línea de la entrada estándar o <code>null</code> si ya
     * no hay qué leer.
     * @return una línea de la entrada estándar o <code>null</code> si ya
     *         no hay qué leer.
     */
    @Override public String lee() {
	try {
	    return lector.readLine();
	} catch (IOException ioe) {
	    System.err.println("\nOcurrió un problema al leer de la entrada estándar.\n");
	    System.exit(2);
	}
    }
}
