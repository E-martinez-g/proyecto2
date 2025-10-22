package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Clase abstracta para leer de una entrada.
 */
public abstract class Lector {
 
    /** El {@link BufferedReader} que se conectará a la entrada elegida. */
    private BufferedReader lector;

    /**
     * Regresa una línea de la entrada elegida o <code>null</code> si ya
     * no hay qué leer.
     * @return una línea de la entrada elegida o <code>null</code> si ya
     *         no hay qué leer.
     */
    public String lee() {
	try {
	    return lector.readLine();
	} catch (IOException ioe) {
	    System.err.println("\nOcurrió un problema al leer de la entrada.\n");
	    System.exit(2);
	}
    }
}
