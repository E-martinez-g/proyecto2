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
    private static final Collator colador = Proyecto2.getPrimaryCollator();
    
    /**
     * Constructor para el emparejador.
     * @param palabras la lista de palabras para las que se crearán bloques.
     */
    public Emparejador(Lista<String> palabras) {
	bloques = new Lista<Bloque>();
	for (String palabra : palabras)
	    bloques.agrega(new Bloque(palabra));
    }

    /**
     * Regresa una copia de la lista de bloques.
     * @return una copia de la lista de bloques.
     */
    public Lista<Bloque> getBloques() {
	return bloques.copia();
    }

    /**
     * Intenta emparejar una cadena con todos los bloques posibles.
     * @param linea la cadena que intentará emparejar.
     */
    public void empareja(String linea) {
	String[] sucia = linea.split(" ");
	String[] limpia = linea.replaceAll("[^\\p{L}\\p{N}\\s]", "").split(" ");
	for (Bloque b : bloques) {
	    boolean contienePalabra = false;
	    int iI = 0;
	    String s = "";
	    for (int e = 0; e < limpia.length; e++) {
		if (colador.compare(limpia[e], b.getPalabra()) == 0) {
		    int ini = sucia[e].indexOf(limpia[e].charAt(0));
		    int ll = limpia[e].length() - 1;
		    int fin = sucia[e].lastIndexOf(limpia[e].charAt(ll));
		    if (e > 0)
			s += " ";
		    try {
			s += sucia[e].substring(0, ini);
		    } catch (IndexOutOfBoundsException ioobe) {}
		    s += "\u001B[36m";
		    s += sucia[e].substring(ini, fin + 1);
		    s += "\u001B[0m";
		    try {
			s += sucia[e].substring(fin + 1);
		    } catch (IndexOutOfBoundsException ioobe) {}
		    if (!contienePalabra)
			iI += ini;
		    contienePalabra = true;
		} else {
		    if (e > 0)
			s += " ";
		    s += sucia[e];
		}
		if (!contienePalabra)
		    iI += sucia[e].length();
	    }
	    if (contienePalabra)
		b.agrega(new LineaAsociada(b.getPalabra(), s, iI));
	}
    }
}
