package mx.unam.ciencias.edd.proyecto2;

/**
 * Clase para excepciones de palabras que no coinciden con la asociada.
 */
public class ExcepcionPalabraNoCoincide extends IllegalArgumentException {

    /**
     * Constructor vacío.
     */
    public ExcepcionPalabraNoCoincide() {}

    /**
     * Constructor que recibe un mensaje para el usuario.
     * @param mensaje un mensaje que verá el usuario cuando ocurra la excepción.
     */
    public ExcepcionPalabraNoCoincide(String mensaje) {
	super(mensaje);
    }
}
