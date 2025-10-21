package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * <p>Clase para árboles binarios ordenados. Los árboles son genéricos, pero
 * acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos sus
 *       descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos sus
 *       descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Pila para recorrer los vértices en DFS in-order. */
        private Pila<Vertice> pila;

        /* Inicializa al iterador. */
        private Iterador() {
	    pila = new Pila<>();
	    Vertice v = raiz;
	    while (v != null) {
		pila.mete(v);
		v = v.izquierdo;
	    }
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return !pila.esVacia();
        }

        /* Regresa el siguiente elemento en orden DFS in-order. */
        @Override public T next() {
	    Vertice v = pila.saca();
	    T t = v.elemento;
	    v = v.derecho;
	    while (v != null){
		pila.mete(v);
		v = v.izquierdo;
	    }
	    return t;
        }
    }

    /**
     * El vértice del último elemento agegado. Este vértice sólo se puede
     * garantizar que existe <em>inmediatamente</em> después de haber agregado
     * un elemento al árbol. Si cualquier operación distinta a agregar sobre el
     * árbol se ejecuta después de haber agregado un elemento, el estado de esta
     * variable es indefinido.
     */
    protected Vertice ultimoAgregado;

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }

    /**
     * Construye un árbol binario ordenado a partir de una colección. El árbol
     * binario ordenado tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario ordenado.
     */
    public ArbolBinarioOrdenado(Coleccion<T> coleccion) {
        super(coleccion);
    }

    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden in-order.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	Vertice v = nuevoVertice(elemento);
	elementos++;
	ultimoAgregado = v;
	if (raiz == null) {
	    raiz = v;
	    return;
	}
	Vertice buscador = raiz;
	while (true) {
	    if (elemento.compareTo(buscador.elemento) <= 0) {
		if (buscador.izquierdo == null) {
		    buscador.izquierdo = v;
		    v.padre = buscador;
		    return;
		}
		buscador = buscador.izquierdo;
	    } else {
		if (buscador.derecho == null) {
		    buscador.derecho = v;
		    v.padre = buscador;
		    return;
		}
		buscador = buscador.derecho;
	    }
	}
    }

    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no hace nada; si
     * está varias veces, elimina el primero que encuentre (in-order). El árbol
     * conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	Vertice v = (Vertice) busca(elemento);
	if (v == null)
	    return;
	if (v.izquierdo != null && v.derecho != null)
	    v = intercambiaEliminable(v);
        eliminaVertice(v);
    }

    /**
     * Intercambia el elemento de un vértice con dos hijos distintos de
     * <code>null</code> con el elemento de un descendiente que tenga a lo más
     * un hijo.
     * @param vertice un vértice con dos hijos distintos de <code>null</code>.
     * @return el vértice descendiente con el que vértice recibido se
     *         intercambió. El vértice regresado tiene a lo más un hijo distinto
     *         de <code>null</code>.
     */
    protected Vertice intercambiaEliminable(Vertice vertice) {
        Vertice buscador = vertice.izquierdo;
	while (buscador.derecho != null)
	    buscador = buscador.derecho;
	vertice.elemento = buscador.elemento;
	return buscador;
    }

    /**
     * Elimina un vértice que a lo más tiene un hijo distinto de
     * <code>null</code> subiendo ese hijo (si existe).
     * @param vertice el vértice a eliminar; debe tener a lo más un hijo
     *                distinto de <code>null</code>.
     */
    protected void eliminaVertice(Vertice vertice) {
	elementos--;
	if (vertice.padre == null) {
	    if (vertice.izquierdo != null) {
		raiz = vertice.izquierdo;
		vertice.izquierdo.padre = null;
	    } else if (vertice.derecho != null) {
		raiz = vertice.derecho;
		vertice.derecho.padre = null;
	    } else
		raiz = null;
	} else if (vertice == vertice.padre.izquierdo) {
	    if (vertice.izquierdo != null) {
		vertice.izquierdo.padre = vertice.padre;
		vertice.padre.izquierdo = vertice.izquierdo;
	    } else if (vertice.derecho != null) {
		vertice.derecho.padre = vertice.padre;
		vertice.padre.izquierdo = vertice.derecho;
	    } else
		vertice.padre.izquierdo = null;
	} else {
	    if (vertice.izquierdo != null) {
		vertice.izquierdo.padre = vertice.padre;
		vertice.padre.derecho = vertice.izquierdo;
	    } else if (vertice.derecho != null) {
		vertice.derecho.padre = vertice.padre;
		vertice.padre.derecho = vertice.derecho;
	    } else
		vertice.padre.derecho = null;
	}
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo encuentra,
     * regresa el vértice que lo contiene; si no, regresa <code>null</code>.
     * @param elemento el elemento a buscar.
     * @return un vértice que contiene al elemento buscado si lo
     *         encuentra; <code>null</code> en otro caso.
     */
    @Override public VerticeArbolBinario<T> busca(T elemento) {
        if (raiz == null || elemento == null)
	    return null;
	Vertice v = raiz;
	while (v != null) {
	    if (elemento.equals(v.elemento))
		break;
	    else if (elemento.compareTo(v.elemento) < 0)
		v = v.izquierdo;
	    else
		v = v.derecho;
	}
	return v;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al
     * árbol. Este método sólo se puede garantizar que funcione
     * <em>inmediatamente</em> después de haber invocado al método {@link
     * agrega}. Si cualquier operación distinta a agregar sobre el árbol se
     * ejecuta después de haber agregado un elemento, el comportamiento de este
     * método es indefinido.
     * @return el vértice que contiene el último elemento agregado al árbol, si
     *         el método es invocado inmediatamente después de agregar un
     *         elemento al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return ultimoAgregado;
    }

    /**
     * Gira el árbol a la derecha sobre el vértice recibido. Si el vértice no
     * tiene hijo izquierdo, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraDerecha(VerticeArbolBinario<T> vertice) {
        if (!vertice.hayIzquierdo())
	    return;
	Vertice v = vertice(vertice);
	v.izquierdo.padre = v.padre;
	if (v.padre == null)
	    raiz = v.izquierdo;
	else if (v.padre.izquierdo == v)
		v.padre.izquierdo = v.izquierdo;
	else
		v.padre.derecho = v.izquierdo;
	Vertice nieto = v.izquierdo.derecho;
	v.izquierdo.derecho = v;
	v.padre = v.izquierdo;
	v.izquierdo = nieto;
	if (nieto != null)
	    nieto.padre = v;
    }

    /**
     * Gira el árbol a la izquierda sobre el vértice recibido. Si el vértice no
     * tiene hijo derecho, el método no hace nada.
     * @param vertice el vértice sobre el que vamos a girar.
     */
    public void giraIzquierda(VerticeArbolBinario<T> vertice) {
	if (!vertice.hayDerecho())
	    return;
	Vertice v = vertice(vertice);
	v.derecho.padre = v.padre;
	if (v.padre == null)
	    raiz = v.derecho;
	else if (v.padre.izquierdo == v)
	    v.padre.izquierdo = v.derecho;
	else
	    v.padre.derecho = v.derecho;
	Vertice nieto = v.derecho.izquierdo;
	v.derecho.izquierdo = v;
	v.padre = v.derecho;
	v.derecho = nieto;
	if (nieto != null)
	    nieto.padre = v;
    }

    /**
     * Realiza un recorrido DFS <em>pre-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPreOrder(AccionVerticeArbolBinario<T> accion) {
        if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<>();
	pila.mete(raiz);
	while (!pila.esVacia()) {
	    Vertice v = pila.saca();
	    accion.actua(v);
	    if (v.derecho != null)
		pila.mete(v.derecho);
	    if (v.izquierdo != null)
		pila.mete(v.izquierdo);
	}
    }

    /**
     * Realiza un recorrido DFS <em>in-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsInOrder(AccionVerticeArbolBinario<T> accion) {
        if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<>();
	Vertice v = raiz;
	while (v != null) {
	    pila.mete(v);
	    v = v.izquierdo;
	}
	while (!pila.esVacia()) {
	    v = pila.saca();
	    accion.actua(v);
	    v = v.derecho;
	    while (v != null) {
		pila.mete(v);
		v = v.izquierdo;
	    }
	}
    }

    /**
     * Realiza un recorrido DFS <em>post-order</em> en el árbol, ejecutando la
     * acción recibida en cada elemento del árbol.
     * @param accion la acción a realizar en cada elemento del árbol.
     */
    public void dfsPostOrder(AccionVerticeArbolBinario<T> accion) {
        if (raiz == null)
	    return;
	Pila<Vertice> pila = new Pila<>();
	pila.mete(raiz);
	pila.mete(raiz);
	while (!pila.esVacia()) {
	    Vertice v = pila.saca();
	    if (pila.esVacia() || v != pila.mira())
		accion.actua(v);
	    else {
		if (v.derecho != null) {
		    pila.mete(v.derecho);
		    pila.mete(v.derecho);
		}
		if (v.izquierdo != null) {
		    pila.mete(v.izquierdo);
		    pila.mete(v.izquierdo);
		}
	    }
	}
    }

    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * @return un iterador para iterar el árbol.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
