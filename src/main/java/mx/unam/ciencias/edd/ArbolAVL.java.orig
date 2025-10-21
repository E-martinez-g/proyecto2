package mx.unam.ciencias.edd;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus vértices, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeAVL extends Vertice {

        /** La altura del vértice. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
            super(elemento);
        }

        /**
         * Regresa la altura del vértice.
         * @return la altura del vértice.
         */
        @Override public int altura() {
            return altura;
        }

        /**
         * Regresa una representación en cadena del vértice AVL.
         * @return una representación en cadena del vértice AVL.
         */
        @Override public String toString() {
            return elemento.toString() + " " +  altura + "/" + balance(); 
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeAVL}, su elemento es igual al elemento de éste
         *         vértice, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeAVL vertice = (VerticeAVL)objeto;
            return altura == vertice.altura && super.equals(objeto);
        }

	/**
	 * Regresa al hijo izquierdo como instancia de {@link VerticeAVL}.
	 * @return el hijo izquierdo como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL izquierdoAVL() {
	    return (VerticeAVL) izquierdo;
	}

	/**
	 * Regresa al hijo derecho como instancia de {@link VerticeAVL}.
	 * @return el hijo derecho como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL derechoAVL() {
	    return (VerticeAVL) derecho;
	}
	
	/**
	 * Regresa al padre como instancia de {@link VerticeAVL}.
	 * @return el padre como instancia de {@link VerticeAVL}.
	 */
	private VerticeAVL padreAVL() {
	    return (VerticeAVL) padre;
	}
	
	/**
	 * Regresa el balance del vértice.
	 * @return el balance del vértice.
	 */
	private int balance() {
	    return alturaAVL(izquierdoAVL()) - alturaAVL(derechoAVL());
	}

	/**
	 * Actualiza la altura del vértice.
	 */
	private void actualizaAltura() {
	    altura = Math.max(alturaAVL(izquierdoAVL()), alturaAVL(derechoAVL())) + 1;
	}
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolAVL() { super(); }

    /**
     * Construye un árbol AVL a partir de una colección. El árbol AVL tiene los
     * mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol AVL.
     */
    public ArbolAVL(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link VerticeAVL}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol girándolo como
     * sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        super.agrega(elemento);
	rebalancea((VerticeAVL) ultimoAgregado.padre);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        VerticeAVL v = (VerticeAVL) busca(elemento);
	if (v == null)
	    return;
	if (v.izquierdo != null && v.derecho != null)
	    v = (VerticeAVL) intercambiaEliminable(v);
	eliminaVertice(v);
	rebalancea(v.padreAVL());
    }

    /**
     * Rebalancea el árbol después de agregar o eliminar.
     * @param vertice el vertice sobre el que se ejecutará el algoritmo.
     */
    private void rebalancea(VerticeAVL vertice) {
	if (vertice == null)
	    return;
	vertice.actualizaAltura();
	if (vertice.balance() == 2) {
	    VerticeAVL izquierdo = vertice.izquierdoAVL();
	    if (izquierdo.balance() == -1) {
		super.giraIzquierda(izquierdo);
		izquierdo.actualizaAltura();
		izquierdo.padreAVL().actualizaAltura();
		izquierdo = izquierdo.padreAVL();
	    }
	    super.giraDerecha(vertice);
	    vertice.actualizaAltura();
	    izquierdo.actualizaAltura();
	} else if (vertice.balance() == -2) {
	    VerticeAVL derecho = vertice.derechoAVL();
	    if (derecho.balance() == 1) {
		super.giraDerecha(derecho);
		derecho.actualizaAltura();
		derecho.padreAVL().actualizaAltura();
		derecho = derecho.padreAVL();
	    }
	    super.giraIzquierda(vertice);
	    vertice.actualizaAltura();
	    derecho.actualizaAltura();
	}
	rebalancea(vertice.padreAVL());
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la derecha por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la izquierda por el " +
                                                "usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la izquierda por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la derecha por el " +
                                                "usuario.");
    }

    /**
     * Regresa la altura del vértice recibido aunque este sea <code>null</code>.
     * @param vertice el vértice cuya altura estamos buscando.
     * @return la altura del vértice recibido o -1 si este es <code>null</code>.
     */
    private int alturaAVL(VerticeAVL vertice) {
	return vertice == null ? -1 : vertice.altura;
    }
}
