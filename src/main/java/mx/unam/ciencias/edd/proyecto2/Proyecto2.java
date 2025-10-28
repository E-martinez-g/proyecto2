package mx.unam.ciencias.edd.proyecto2;

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
}
