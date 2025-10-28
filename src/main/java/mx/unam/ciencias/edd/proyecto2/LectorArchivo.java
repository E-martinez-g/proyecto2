package mx.unam.ciencias.edd.proyecto2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import mx.unam.ciencias.edd.Lista;

/**
 * Clase para leer de archivos.
 */
public class LectorArchivo extends Lector {

    /** La lista de archivos de los que se leerá. */
    private Lista<String> archivos;
    
    /** El nombre del archivo que se está leyendo actualmente. */
    private String archivoActual;

    /**
     * Constructor para leer de archivos.
     * @param archivos la lista de archivos de los que se tiene que leer.
     */
    public LectorArchivos(Lista<String> archivos) {
	archivoActual = archivos.eliminaPrimero();
	this.archivos = archivos;
	try {
	    lector = new BufferedReader(
	 	         new InputStreamReader(
			     new FileInputStream(archivoActual), "utf-8"));
	} catch (IOException ioe) {
	    System.err.println("\nOcurrió un error al intentar conectarse "
			       + "al archivo: " + archivoActual + ".\n");
	    System.exit(1);
	}
    }

    /**
     * Regresa una línea de los archivos recibidos o <code>null</code> si ya
     * no hay qué leer.
     * @return una línea de los archivos recibidos o <code>null</code> si ya
     *         no hay qué leer.
     */
    @Override public String lee() {
	String s = null;
	try {
	    s = lector.readLine();
	} catch (IOException ioe) {
	    System.err.println("Ocurrió un error al leer del archivo: " +
			       archivoActual + ".\n");
	    System.exit(2);
	}
	while (s == null) {
	    if (!archivos.esVacia())
		break;
	    actualizaLector(archivos.eliminaPrimero());
	    String s = lector.readLine();
	}
	return s;
    }

    /**
     * Actualiza al lector de archivos para leer el siguiente archivo.
     * @param el siguiente archivo a leer.
     */
    private void actualizaLector(String archivo) {
	archivoActual = archivo;
        try {
	    lector = new BufferedReader(
	 	         new InputStreamReader(
			     new FileInputStream(archivoActual), "utf-8"));
	} catch (IOException ioe) {
	    System.err.println("\nOcurrió un error al intentar conectarse "
			       + "al archivo: " + archivoActual + ".\n");
	    System.exit(1);
	}
    }
}
