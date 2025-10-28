package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para aplicaciones de emparejamiento.
 */
public class Aplicacion {
    
    /** El emparejador de la aplicación. */
    private Emparejador emparejador;

    /** El lector de la aplicación. */
    private Lector lector;

    /**
     * Constructor de aplicaciones que leen de la entrada estándar.
     * @param palabras la lista de palabras para el emparejador.
     */
    public Aplicacion(Lista<String> palabras) {
	emparejador = new Emparejador(palabras);
	lector = new LectorEstandar();
    }

    /**
     * Constructor de aplicaciones que leen de archivos.
     * @param palabras la lista de palabras para el emparejador.
     * @param archivos la lista de archivos para el lector.
     */
    public Aplicacion(Lista<String> palabras, Lista<String> archivos) {
	emparejador = new Emparejador(palabras);
	lector = new LectorArchivo(archivos);
    }
    
}
