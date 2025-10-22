package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.proyecto2.ExcepcionPalabraNoCoincide;

/**
 * Clase para cadenas que contienen una palabra específica.
 */
public class LineaAsociada implements Comparable<LineaAsociada> {

    /** La palabra que encontramos en la cadena */
    private String palabra;

    /** El índice del primer símbolo de la palabra dentro de la cadena */
    private int indiceInicial;

    /** La cadena original con la palabra buscada resaltada */
    private String cadena;

    /**
     * Constructor que recibe la palabra que se encontró, la cadena en que
     * se encontró y el índice en que esta palabra empieza dentro de la cadena.
     * @param palabra la palabra encontrada.
     * @param indiceInicial el índice en que la palabra comienza dentro de la
     *        cadena.
     * @param cadena la cadena en que se encontró la palabra.
     */
    public LineaAsociada(String palabra, int indiceInicial, String cadena) {
	this.palabra = palabra;
	this.indiceInicial = indiceInicial;
	this.cadena = resalta(cadena);
    }

    /**
     * Resalta en la cadena la palabra encontrada (La colorea de azul).
     * @param cadena la cadena en la que va a resaltar la palabra.
     * @return la cadena recibida con la palabra resaltada.
     */
    private String resalta(String cadena) {
	String s = cadena.substring(0, indiceInicial - 1) + "\u001B[34m";
	s += cadena.substring(indiceInicial, indiceInicial + palabra.length() -1);
	s += "\u001B[0m" + cadena.substring(indiceInicial + palabra.length());
	return s;
    }

    /**
     * Se compara con una línea asociada a la misma palabra y regresa 0 si la
     * palabra se encuentra en el mismo índice en la otra línea, un número menor
     * a 0 si la palabra se encuentra antes en esta y un número mayor a 0 si la
     * palabra se encuentra antes en la otra.
     * @param linea la linea con la que se comparará.
     * @return 0 si la palabra se encuentra en el mismo índice en ambas líneas, un
     *         número menor a 0 si se encuentra antes en esta y un número mayor a
     *         0 si se encuentra antes en la otra.
     */
    @Override public int compareTo(LineaAsociada la) {
	if (!palabra.equals(la.palabra))
	    throw new ExcepcionPalabraNoCoincide("Se intentaron comparar dos " +
						 "líneas asociadas con palabras" +
						 " diferentes");
	return indiceInicial - la.indiceInicial;
    }

    /**
     * Regresa la palabra asociada a la línea.
     * @return la palabra asociada a la línea.
     */
    public String getPalabra() {
	return palabra;
    }

    /**
     * Regresa la cadena con la palabra asociada resaltada.
     * @return la cadena con la palabra asociada resaltada.
     */
    public String getCadena() {
	return cadena;
    }
}
