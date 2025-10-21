package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.ArbolRojinegro;
import mx.unam.ciencias.edd.proyecto2.LineaAsociada;
import mx.unam.ciencias.edd.proyecto2.ExcepcionPalabraNoCoincide;

/**
 * Clase para bloques de líneas que contienen una palabra específica.
 */
public class Bloque {

    /** El árbol rojinegro que mantendrá al bloque ordenado todo el tiempo */
    private ArbolRojinegro<LineaAsociada> arbol;

    /** La palabra que contendrán todas las cadenas dentro del árbol */
    private String palabra;

    /**
     * Constructor que recibe una palabra.
     * @param palabra la palabra que contendrán todas las líneas en este.
     */
    public Bloque(String palabra) {
	this.palabra = palabra;
	arbol = new ArbolRojinegro<LineaAsociada>();
    }

    /**
     * Agrega una cadena al árbol interno.
     * @param linea la linea a agregar.
     */
    public void agrega(LineaAsociada linea) {
	if (!palabra.equals(linea.getPalabra()))
	    throw new ExcepcionPalabraNoCoincide();
	arbol.agrega(linea);
    }

    /**
     * Regresa la representación en cadena del bloque.
     * @return la representación en cadena del bloque.
     */
    @Override public String toString() {
	String s = "";
	for (LineaAsociada la : arbol)
	    s += la.getCadena() + "\n";
	s += "...\n";
    }
}
