package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import java.text.Collator;

/**
 * Clase principal para el proyecto 2.
 */
public class Proyecto2 {

    public static void main(String[] args) {
	ManejadorArgumentos m = new ManejadorArgumentos(args);
	if (m.getAyuda()) {
	    banderaH();
	    return;
	}
	Aplicacion a;
	if (m.getArchivos().esVacia())
	    a = new Aplicacion(m.getPalabras());
	else
	    a = new Aplicacion(m.getPalabras(), m.getArchivos());
	a.funciona();
    }

    public static Collator getPrimaryCollator() {
	Collator colador = Collator.getInstance();
	colador.setStrength(Collator.PRIMARY);
	return colador;
    }

    private static void banderaH() {
	System.out.println("\nUso: java -jar proyecto2.jar [-h] [-p PALABRA] [ARCHIVO1 ARCHIVO2 ...]\n");
	System.out.println("Descripción:");
	System.out.println("   Busca las palabras recibidas en los archivos recibidos o en el texto recibido en la entrada");
	System.out.println("   estándar y muestra las coincidencias en la consola.\n");
	System.out.println("Por cada palabra buscada se imprime en la consola:\n");
	System.out.println("\u001B[33m<PALABRA>:\u001B[0m");
	System.out.println("<LINEA1_1>\u001B[36m<PALABRA>\u001B[0m<LINEA1_2>");
	System.out.println("<LINEA2_1>\u001B[36m<PALABRA>\u001B[0m<LINEA2_2>");
	System.out.println("...etc...\n");
	System.out.println("Notas:");
	System.out.println("   • Una palabra debe contener al menos una letra o número para ser considerada válida para buscar.");
	System.out.println("   • Las coincidencias se buscan ignorando los símbolos, ya sea que los contenga la palabra");
	System.out.println("     recibida en los argumentos o las palabras en el texto.");
	System.out.println("   • El programa busca palabras que coincidan por completo con las recibidas en los argumentos, no");
	System.out.println("     que las contengan como subcadena.");
	System.out.println("   • Las líneas emparejadas con una palabra se imprimen en el orden en que la palabra aparece dentro");
	System.out.println("     dentro de estas, si aparecen en la misma posición se ordenan lexicográficamente.");
	System.out.println("   • Si la palabra buscada aparece más de una vez en una línea solo se considera la primera");
	System.out.println("     aparición al ordenar, pero se resaltan todas las apariciones.\n");
    }
}
