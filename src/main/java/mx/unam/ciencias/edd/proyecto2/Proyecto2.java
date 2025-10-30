package mx.unam.ciencias.edd.proyecto2;

import mx.unam.ciencias.edd.Lista;
import java.text.Collator;

/**
 * Clase principal para el proyecto 2.
 */
public class Proyecto2 {

    public static void main(String[] args) {
	ManejadorArgumentos m = new ManejadorArgumentos(args);
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
}
