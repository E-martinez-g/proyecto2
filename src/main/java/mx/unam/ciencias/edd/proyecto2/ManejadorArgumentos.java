package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
* Clase para manejadores de banderas.
*/
public class ManejadorArgumentos {

    /** La lista que contendrá los archivos obtenidos de los argumentos.*/
    private Lista<String> archivos;

    /** La lista que contendrá las palabras obtenidas de los argumentos.*/
    private Lista<String> palabras;

    /**
     * Constructor del Manejador de argumentos.
     * @param args el arreglo del que se obtendrán los archivos y las palabras.
     */
    public ManejadorArgumentos(String[] args) {
	archivos = new Lista<String>();
	palabras = new Lista<String>();
	exploraArgumentos(args);
    }

    /**
     * Regresa una copia de la lista de archivos.
     * @return una copia de la lista de archivos.
     */
    public Lista<String> getArchivos() {
	return archivos.copia();
    }

    /**
     * Regresa una copia de la lista de palabras.
     * @return una copia de la lista de palabras.
     */
    public Lista<String> getPalabras() {
	return palabras.copia();
    }

    /**
     * Obtiene de los argumentos los archivos y las palabras.
     * @param args el arreglo del que se obtendrán los archivos y las palabras.
     */
    private void exploraArgumentos(String[] args) {
	boolean palabra = false;
	for (String s : args) {
	    if (palabra) {
		palabra = false;
		if (s.replaceAll("[^\\p{L}\\p{N}\\s]", "").trim() == "")
		    noHayPalabra();
		palabras.agrega(s);
	    } else if (s.equals("-p"))
		palabra = true;
	    else
		archivos.agrega(s);
	}
	if (palabra)
	    noHayPalabra();
    }

    /**
     * Se queja de no haber encontrado una palabra después de la bandera -p
     * y termina el programa.
     */
    private void noHayPalabra() {
	System.err.println("\nLa bandera -p debe ser seguida por una palabra.");
	System.exit(1);
    }
}
