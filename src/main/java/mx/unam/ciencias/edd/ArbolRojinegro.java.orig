package mx.unam.ciencias.edd;

/**
 * Clase para árboles rojinegros. Un árbol rojinegro cumple las siguientes
 * propiedades:
 *
 * <ol>
 *  <li>Todos los vértices son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<code>null</code>) son NEGRAS (al igual que la raíz).</li>
 *  <li>Un vértice ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un vértice a alguna de sus hojas descendientes tiene el
 *      mismo número de vértices NEGROS.</li>
 * </ol>
 *
 * Los árboles rojinegros se autobalancean.
 */
public class ArbolRojinegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeRojinegro extends Vertice {

        /** El color del vértice. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeRojinegro(T elemento) {
            super(elemento);
	    color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del vértice rojinegro.
         * @return una representación en cadena del vértice rojinegro.
         */
        @Override public String toString() {
	    return (color == Color.ROJO ? "R{" : "N{") + elemento.toString() + "}";
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeRojinegro}, su elemento es igual al elemento de
         *         éste vértice, los descendientes de ambos son recursivamente
         *         iguales, y los colores son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked")
                VerticeRojinegro vertice = (VerticeRojinegro)objeto;
            return color == vertice.color && super.equals(objeto);
        }

	/**
	 * Verifica si un vértice es hijo derecho. Solo se usa cuando se sabe que
	 * hay un padre diferente de <code>null</code>.
	 * @return <code>true</code> si el vértice es el hijo derecho de su padre,
	 *         <code>false</code> en otro caso.
	 */
	private boolean esDerecho() {
	    return this == padre.derecho;
	}
	
	/**
	 * Verifica si un vértice es hijo izquierdo. Solo se usa cuando se sabe que
	 * hay un padre diferente de <code>null</code>.
	 * @return <code>true</code> si el vértice es el hijo izquierdo de su padre,
	 *         <code>false</code> en otro caso.
	 */
	private boolean esIzquierdo() {
	    return this == padre.izquierdo;
	}

	/**
	 * Verifica si un vértice y su padre están cruzados. Solo se usa cuando
	 * se sabe que hay un padre diferente de <code>null</code>.
	 * @return <code>true</code> si el vértice y su padre tienen diferente
	 *         dirección <code>false</code> en otro caso.
	 */
	private boolean estaCruzado() {
	    return esDerecho() ? padreRN().esIzquierdo() : padreRN().esDerecho();
	}

	/**
	 * Únicamente realiza una audición para obtener al padre como instancia de
	 * {@link VerticeRojinegro}. Solo se utiliza cuando se sabe que el padre es
	 * diferente de <code>null</code>.
	 * @return el padre como instancia de {@link VerticeRojinegro}
	 */
	private VerticeRojinegro padreRN() {
	    return (VerticeRojinegro) padre;
	}

	/**
	 * Realiza una audición para obtener al hijo izquierdo como instancia de
	 * {@link VerticeRojinegro}. Solo se utiliza cuando se sabe que el hijo
	 * izquierdo es diferente de <code>null</code>.
	 * @return el hijo izquierdo como instancia de {@linkVerticeRojinegro}
	 */
	private VerticeRojinegro izquierdoRN() {
	    return (VerticeRojinegro) izquierdo;
	}

	/**
	 * Realiza una audición para obtener al hijo derecho como instancia de
	 * {@link VerticeRojinegro}. Solo se utiliza cuando se sabe que el hijo
	 * derecho es diferente de <code>null</code>.
	 * @return el hijo derecho como instancia de {@linkVerticeRojinegro}
	 */
	private VerticeRojinegro derechoRN() {
	    return (VerticeRojinegro) derecho;
	}

	/**
	 * Regresa al único hijo diferente de <code>null</code> de un vértice.
	 * Solo se utiliza cuando un vértice tiene un único hijo.
	 * @return el único hijo diferente de <code>null</code> de un vértice.
	 */
	private VerticeRojinegro hijoUnico() {
	    return izquierdo == null ? derechoRN() : izquierdoRN();
	}

	/**
	 * Regresa al hermano del vértice. Solo se utiliza cuando se sabe que el
	 * padre es diferente de <code>null</code>.
	 * @return el hermano del vértice.
	 */
	private VerticeRojinegro hermano() {
	    return esDerecho() ? padreRN().izquierdoRN() : padreRN().derechoRN();
	}

	/**
	 * Regresa al abuelo de un vértice. Solo se utiliza cuando se sabe que
	 * este es diferente de <code>null</code>.
	 * @return el abuelo del vértice.
	 */
	private VerticeRojinegro abuelo() {
	    return padreRN().padreRN(); 
	}

	/**
	 * Regresa al hermano del padre del vértice. Solo se utiliza cuando
	 * se sabe que el padre y el abuelo son diferentes de <code>null</code>.
	 * @return el hermano del padre del vértice.
	 */
	private VerticeRojinegro tio() {
	    return padreRN().hermano();
	}
	 
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolRojinegro() { super(); }

    /**
     * Construye un árbol rojinegro a partir de una colección. El árbol
     * rojinegro tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        rojinegro.
     */
    public ArbolRojinegro(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link
     * VerticeRojinegro}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice rojinegro con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeRojinegro(elemento);
    }

    /**
     * Regresa el color del vértice rojinegro.
     * @param vertice el vértice del que queremos el color.
     * @return el color del vértice rojinegro.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         VerticeRojinegro}.
     */
    public Color getColor(VerticeArbolBinario<T> vertice) {
	return ((VerticeRojinegro) vertice).color;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol recoloreando
     * vértices y girando el árbol como sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    return;
	super.agrega(elemento);
	VerticeRojinegro vertice = (VerticeRojinegro) ultimoAgregado;
	vertice.color = Color.ROJO;
	rebalanceaAgrega(vertice);
    }
 
    /**
     * Rebalancea el árbol después de agregar recoloreando vértices y girando
     * el árbol como sea necesario.
     * @param vertice el vértice sobre el cuál se encuentra el algoritmo de
     *        rebalanceo. Este vértice siempre es la raíz de un árbol rojinegro
     *        válido excepto porque es rojo.
     */
    private void rebalanceaAgrega(VerticeRojinegro vertice) {
	if (vertice.padre == null) {
	    vertice.color = Color.NEGRO;
	    return;
	}
	if (esNegro(vertice.padreRN()))
	    return;
	if (esRojo(vertice.tio())) {
	    vertice.tio().color = Color.NEGRO;
	    vertice.padreRN().color = Color.NEGRO;
	    vertice.abuelo().color = Color.ROJO;
	    rebalanceaAgrega(vertice.abuelo());
	    return;
	}
	if (vertice.estaCruzado()) {
	    VerticeRojinegro padre = vertice.padreRN();
	    if (vertice.esDerecho())
		super.giraIzquierda(vertice.padre);
	    else
		super.giraDerecha(vertice.padre);
	    vertice = padre;
	}
	vertice.padreRN().color = Color.NEGRO;
	vertice.abuelo().color = Color.ROJO;
	if (vertice.esDerecho())
	    super.giraIzquierda(vertice.padre.padre);
	else super.giraDerecha(vertice.padre.padre);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y recolorea y gira el árbol como sea necesario para
     * rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        VerticeRojinegro eliminar = (VerticeRojinegro) busca(elemento);
	if (eliminar == null)
	    return;
	if (eliminar.izquierdo != null && eliminar.derecho != null)
	    eliminar = (VerticeRojinegro) intercambiaEliminable(eliminar);
	VerticeRojinegro fantasma = null;
	if (eliminar.izquierdo == null && eliminar.derecho == null) {
	    fantasma = new VerticeRojinegro(null);
	    fantasma.padre = eliminar;
	    eliminar.izquierdo = fantasma;
	    fantasma.color = Color.NEGRO;
	}
	VerticeRojinegro subido = eliminar.hijoUnico();
	eliminaVertice(eliminar);
	if (esRojo(subido) || esRojo(eliminar))
	    subido.color = Color.NEGRO;
	else if (esNegro(subido) && esNegro(eliminar))
	    rebalanceaElimina(subido);
	if (fantasma != null)
	    if (fantasma == raiz)
		raiz = null;
	    else if (fantasma.esDerecho())
		fantasma.padre.derecho = null;
	    else
		fantasma.padre.izquierdo = null;
    }

    /**
     * Rebalancea el árbol después de eliminar un elemento, el vértice sobre
     * el que se ejecuta el algoritmo siempre es la raíz de un árbol rojinegro
     * válido.
     * @param vertice el vértice sobre el que actualmente se ejecuta el
     *        algoritmo de rebalanceo.
     */
    private void rebalanceaElimina(VerticeRojinegro vertice) {
	if (vertice.padre == null)
	    return;
	VerticeRojinegro h = vertice.hermano();
	VerticeRojinegro p = vertice.padreRN();
	if (esRojo(h)) {
	    p.color = Color.ROJO;
	    h.color = Color.NEGRO;
	    if (vertice.esDerecho())
		super.giraDerecha(p);
	    else
		super.giraIzquierda(p);
	    h = vertice.hermano();
	}
	VerticeRojinegro sI = h.izquierdoRN();
	VerticeRojinegro sD = h.derechoRN();
	if (esNegro(p) && esNegro(h) && esNegro(sI) && esNegro(sD)) {
	    h.color = Color.ROJO;
	    rebalanceaElimina(p);
	    return;
	}
	if (esRojo(p) && esNegro(h) && esNegro(sI) && esNegro(sD)) {
	    h.color = Color.ROJO;
	    p.color = Color.NEGRO;
	    return;
	}
	if (sonBicolores(sI, sD)) {
	    if (vertice.esDerecho() && esRojo(sD)) {
		h.color = Color.ROJO;
		sD.color = Color.NEGRO;
		super.giraIzquierda(h);
	    } else if (vertice.esIzquierdo() && esRojo(sI)) {
		h.color = Color.ROJO;
		sI.color = Color.NEGRO;
		super.giraDerecha(h);
	    }
	    h = vertice.hermano();
	    sI = h.izquierdoRN();
	    sD = h.derechoRN();
	}
	h.color = p.color;
	p.color = Color.NEGRO;
	if (vertice.esDerecho()) {
	    sI.color = Color.NEGRO;
	    super.giraDerecha(p);
	} else {
	    sD.color = Color.NEGRO;
	    super.giraIzquierda(p);
	}
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la izquierda por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la izquierda " +
                                                "por el usuario.");
    }
    
    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles
     * rojinegros no pueden ser girados a la derecha por los usuarios de la
     * clase, porque se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles rojinegros no " +
                                                "pueden girar a la derecha " +
                                                "por el usuario.");
    }

    /**
     * Regresa verdadero si el vértice es negro, falso en otro caso.
     * @param vertice el vértice cuyo color revisaremos.
     * @return <code>true</code> si el vértice recibido es negro,
     *         <code>false</code> en otro caso.
     */
    private boolean esNegro(VerticeRojinegro vertice) {
	return vertice == null || vertice.color == Color.NEGRO;
    }
    
    /**
     * Regresa verdadero si el vértice rojo, falso en otro caso.
     * @param vertice el vértice cuyo color revisaremos.
     * @return <code>true</code> si el vértice recibido es rojo,
     *         <code>false</code> en otro caso.
     */
    private boolean esRojo(VerticeRojinegro vertice) {
	return vertice != null && vertice.color == Color.ROJO;
    }

    /**
     * Regresa verdadero si los dos vértices recibidos tienen un color diferente.
     * @param v1 el primer vértice a revisar.
     * @param v2 el segundo vértice a revisar.
     * @return <code>true</code> si uno de los vértices es negro y el otro rojo,
     *         <code>false</code> si su color es igual.
     */
    private boolean sonBicolores(VerticeRojinegro v1, VerticeRojinegro v2) {
	return esNegro(v1) ? esRojo(v2) : esNegro(v2);
    }
}
