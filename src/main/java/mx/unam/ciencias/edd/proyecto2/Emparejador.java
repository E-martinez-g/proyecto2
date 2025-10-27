package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import java.text.Collator;

/**
 * Clase para emparejadores de palabras con líneas.
 */
public class Emparejador{

    /** La lista de los bloques a los que puede pertenecer una cadena. */
    private Lista<Bloque> bloques;

    /** El colador para comparar palabras. */
    private Collator colador;
    
    /**
     * Constructor para el emparejador.
     * @param palabras la lista de palabras para las que se crearán bloques.
     */
    public Emparejador(Lista<String> palabras) {
	bloques = new Lista<Bloque>();
	for (String palabra : palabras)
	    bloques.agrega(new Bloque(palabra));
	colador = Collator.getInstance();
	colador.setStrength(Collator.PRIMARY);
    }

    /**
     * Regresa una copia de la lista de bloques.
     * @return una copia de la lista de bloques.
     */
    public Lista<String> getBloques() {
	return bloques.copia();
    }

    /**
     * Intenta emparejar una cadena con todos los bloques posibles.
     * @param linea la cadena que intentará emparejar.
     */
    public void empareja(String linea) {
	String[] sucia = linea.split(" ");
	String[] limpia = linea.replaceAll("[\\p{Punct}¿¡]", "").split(" ");
	for (Bloque b : bloques) {
	    int i = 0;
	    for (int e = 0 ; e < sucia.length ; e++) {
		if (colador.compare(b.getPalabra(), limpia[e]) == 0) {
		    int iI = i + sucia[e].indexOf(limpia[e].charAt(0));
		    b.agrega(new LineaAsociada(b.getPalabra(), iI, linea));
		    break;
		}
	    }
	}
    }
}
