package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * <p>Clase abstracta para árboles binarios genéricos.</p>
 *
 * <p>La clase proporciona las operaciones básicas para árboles binarios, pero
 * deja la implementación de varias en manos de las subclases concretas.</p>
 */
public abstract class ArbolBinario<T> implements Coleccion<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class Vertice implements VerticeArbolBinario<T> {

        /** El elemento del vértice. */
        protected T elemento;
        /** El padre del vértice. */
        protected Vertice padre;
        /** El izquierdo del vértice. */
        protected Vertice izquierdo;
        /** El derecho del vértice. */
        protected Vertice derecho;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        protected Vertice(T elemento) {
            this.elemento = elemento;
        }

        /**
         * Nos dice si el vértice tiene un padre.
         * @return <code>true</code> si el vértice tiene padre,
         *         <code>false</code> en otro caso.
         */
        @Override public boolean hayPadre() {
            return padre != null;
        }

        /**
         * Nos dice si el vértice tiene un izquierdo.
         * @return <code>true</code> si el vértice tiene izquierdo,
         *         <code>false</code> en otro caso.
         */
        @Override public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        /**
         * Nos dice si el vértice tiene un derecho.
         * @return <code>true</code> si el vértice tiene derecho,
         *         <code>false</code> en otro caso.
         */
        @Override public boolean hayDerecho() {
            return derecho != null;
        }

        /**
         * Regresa el padre del vértice.
         * @return el padre del vértice.
         * @throws NoSuchElementException si el vértice no tiene padre.
         */
        @Override public VerticeArbolBinario<T> padre() {
            if (padre == null)
		throw new NoSuchElementException();
	    return padre;
        }

        /**
         * Regresa el izquierdo del vértice.
         * @return el izquierdo del vértice.
         * @throws NoSuchElementException si el vértice no tiene izquierdo.
         */
        @Override public VerticeArbolBinario<T> izquierdo() {
            if (izquierdo == null)
		throw new NoSuchElementException();
	    return izquierdo;
        }

        /**
         * Regresa el derecho del vértice.
         * @return el derecho del vértice.
         * @throws NoSuchElementException si el vértice no tiene derecho.
         */
        @Override public VerticeArbolBinario<T> derecho() {
            if (derecho == null)
		throw new NoSuchElementException();
	    return derecho;
        }

        /**
         * Regresa la altura del vértice.
         * @return la altura del vértice.
         */
        @Override public int altura() {
            if (izquierdo == null && derecho == null)
		return 0;
	    if (izquierdo == null)
		return 1 + derecho.altura();
	    if (derecho == null)
		return 1 + izquierdo.altura();
	    return 1 + Math.max(derecho.altura(), izquierdo.altura());
        }

        /**
         * Regresa la profundidad del vértice.
         * @return la profundidad del vértice.
         */
        @Override public int profundidad() {
            if (padre == null)
		return 0;
	    return 1 + padre.profundidad();
        }

        /**
         * Regresa el elemento al que apunta el vértice.
         * @return el elemento al que apunta el vértice.
         */
        @Override public T get() {
            return elemento;
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Vertice} deben
         * sobrecargar el método {@link Vertice#equals}.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Vertice}, su elemento es igual al elemento de éste
         *         vértice, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked") Vertice vertice = (Vertice)objeto;
            if (!elemento.equals(vertice.elemento))
		return false;
	    if (izquierdo == null && derecho == null)
		return vertice.izquierdo == null && vertice.derecho == null;
	    if (izquierdo == null)
		return vertice.izquierdo == null && derecho.equals(vertice.derecho);
	    if (derecho == null)
		return izquierdo.equals(vertice.izquierdo) && vertice.derecho == null;
	    return izquierdo.equals(vertice.izquierdo) && derecho.equals(vertice.derecho);
        }

        /**
         * Regresa una representación en cadena del vértice.
         * @return una representación en cadena del vértice.
         */
        @Override public String toString() {
            return elemento.toString();
        }
    }

    /** La raíz del árbol. */
    protected Vertice raiz;
    /** El número de elementos */
    protected int elementos;

    /**
     * Constructor sin parámetros. Tenemos que definirlo para no perderlo.
     */
    public ArbolBinario() {}

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     * tendrá los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public ArbolBinario(Coleccion<T> coleccion) {
        for (T t : coleccion)
	    agrega(t);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link Vertice}. Para
     * crear vértices se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de vértices.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    protected Vertice nuevoVertice(T elemento) {
        return new Vertice(elemento);
    }

    /**
     * Regresa la altura del árbol. La altura de un árbol es la altura de su
     * raíz.
     * @return la altura del árbol.
     */
    public int altura() {
	return raiz == null ? -1 : raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    @Override public int getElementos() {
        return elementos;
    }

    /**
     * Nos dice si un elemento está en el árbol binario.
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return buscaVertice(elemento) != null;
    }

    /**
     * Busca el vértice de un elemento en el árbol. Si no lo encuentra regresa
     * <code>null</code>.
     * @param elemento el elemento para buscar el vértice.
     * @return un vértice que contiene el elemento buscado si lo encuentra;
     *         <code>null</code> en otro caso.
     */
    public VerticeArbolBinario<T> busca(T elemento) {
        return buscaVertice(elemento);
    }

    /**
     * Regresa el vértice que contiene la raíz del árbol.
     * @return el vértice que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public VerticeArbolBinario<T> raiz() {
        if (raiz == null)
	    throw new NoSuchElementException();
	return raiz;
    }

    /**
     * Nos dice si el árbol es vacío.
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return raiz == null;
    }

    /**
     * Limpia el árbol de elementos, dejándolo vacío.
     */
    @Override public void limpia() {
        elementos = 0;
	raiz = null;
    }

    /**
     * Compara el árbol con un objeto.
     * @param objeto el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked")
            ArbolBinario<T> arbol = (ArbolBinario<T>)objeto;
        if (raiz == null)
	    return arbol.raiz == null;
	return elementos == arbol.elementos && raiz.equals(arbol.raiz);
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
        if (raiz == null)
	    return "";
	return toString(raiz, 0, new boolean[altura() + 1]);
    }

    /**
     * Devuelve el primer vértice encontrado con BFS que contiene un elemento. 
     * @param elemento el elemento a buscar.
     * @return el primer vértice encontrado con BFS que tiene al elemento
     *         buscado o <code>null</code> en otro caso.
     */
    private Vertice buscaVertice (T elemento) {
	if (raiz == null || elemento == null)
	    return null;
	Cola<Vertice> cola = new Cola<>();
	cola.mete(raiz);
	while (!cola.esVacia()) {
	    Vertice v = cola.saca();
	    if (elemento.equals(v.elemento))
		return v;
	    if (v.izquierdo != null)
		cola.mete(v.izquierdo);
	    if (v.derecho != null)
		cola.mete(v.derecho);
	}
	return null;
    }

    /**
     * Crea saltos de linea para conservar la representación de las ramas más
     * largas y la distancia a vértices más profundos.
     * @param nivel el índice del nivel para el que creará los espacios, entre
     *        mayor sea más espacios habrán.
     * @param saltitos el arreglo que contiene información sobre dónde colocar
     *        una rama y dónde únicamente espacios.
     * @return una representación en cadena de los espacios necesarios para
     *         representar posteriormente a un vértice del árbol.
     */
    private String dibujaEspacios(int nivel, boolean[] saltitos) {
	String s = "";
	for (int i = 0; i < nivel; i++)
	    s += saltitos[i] ? "│  " : "   ";
	return s;
    }
    
    /**
     * Crea la representación en cadena de un vértice y sus hijos.
     * @param vertice el vértice cuya representación en cadena creará.
     * @param nivel el índice del nivel en que se encuentra el vértice.
     * @param saltitos el arreglo con información para saber los espacios
     *        necesarios previo a representar a cada uno de sus hijos.
     * @return la representación en cadena del vértice y sus hijos.
     */
    private String toString(Vertice vertice, int nivel, boolean[] saltitos) {
	String s = vertice.toString() + "\n";
	saltitos[nivel] = true;
	if (vertice.izquierdo != null && vertice.derecho != null) {
	    s += dibujaEspacios(nivel, saltitos);
	    s += "├─›";
	    s += toString(vertice.izquierdo, nivel + 1, saltitos);
	    s += dibujaEspacios(nivel, saltitos);
	    s += "└─»";
	    saltitos[nivel] = false;
	    s += toString(vertice.derecho, nivel + 1, saltitos);
	} else if (vertice.izquierdo != null) {
	    s += dibujaEspacios(nivel, saltitos);
	    s += "└─›";
	    saltitos[nivel] = false;
	    s += toString(vertice.izquierdo, nivel + 1, saltitos);
	} else if (vertice.derecho != null) {
	    s += dibujaEspacios(nivel, saltitos);
	    s += "└─»";
	    saltitos[nivel] = false;
	    s += toString(vertice.derecho, nivel + 1, saltitos);
	}
	return s;
    }
    
    /**
     * Convierte el vértice (visto como instancia de {@link
     * VerticeArbolBinario}) en vértice (visto como instancia de {@link
     * Vertice}). Método auxiliar para hacer esta audición en un único lugar.
     * @param vertice el vértice de árbol binario que queremos como vértice.
     * @return el vértice recibido visto como vértice.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *         Vertice}.
     */
    protected Vertice vertice(VerticeArbolBinario<T> vertice) {
        return (Vertice)vertice;
    }
}
