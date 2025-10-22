package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.IOException;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase abstracta para leer de una entrada;
 */
public abstract class Lector {
 
    /** El {@link BufferedReader} que se conectará a la entrada elegida */
    BufferedReader lector;

    /**
     * Regresa una línea de la entrada elegida o <code>null</code> si ya
     * no hay qué leer.
     * @return una línea de la entrada elegida o <code>null</code> si ya
     *         no hay qué leer.
     */
    public String lee() {
	return lector.readLine();
    }
}
