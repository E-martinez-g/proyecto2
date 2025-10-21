package mx.unam.ciencias.edd;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
	    this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            siguiente = cabeza;
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
	    return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            if (siguiente == null)
		throw new NoSuchElementException();
	    anterior = siguiente;
	    siguiente = siguiente.siguiente;
	    return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
	    return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            if (anterior == null)
		throw new NoSuchElementException();
            siguiente = anterior;
	    anterior = anterior.anterior;
	    return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            anterior = null;
	    siguiente = cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            anterior = rabo;
	    siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
	return rabo == null;
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
	if (elemento == null)
	    throw new IllegalArgumentException();
	longitud++;
	Nodo n = new Nodo(elemento);
	if (rabo == null)
	    rabo = cabeza = n;
	else {
	    rabo.siguiente = n;
	    n.anterior = rabo;
	    rabo = n;
	}
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	longitud++;
	Nodo n = new Nodo(elemento);
	if (rabo == null)
	    rabo = cabeza = n;
	else {
	    rabo.siguiente = n;
	    n.anterior = rabo;
	    rabo = n;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	longitud++;
	Nodo n = new Nodo(elemento);
	if (longitud == 1)
	    cabeza = rabo = n;
	else {
	    cabeza.anterior = n;
	    n.siguiente = cabeza;
	    cabeza = n;
	}
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        if (elemento == null)
	    throw new IllegalArgumentException();
	if (i <= 0)
	    agregaInicio(elemento);
	else  if (i >= longitud)
	    agrega(elemento);
	else {
	    longitud++;
	    Nodo n = new Nodo(elemento);
	    Nodo b = cabeza;
	    for (int c = 0 ; c < i ; c++)
		b = b.siguiente;
	    b.anterior.siguiente = n;
	    n.anterior = b.anterior;
	    n.siguiente = b;
	    b.anterior = n;
	}
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
	eliminaNodo(buscaNodo(elemento));
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if (rabo == null)
	    throw new NoSuchElementException();
	longitud--;
	Nodo eliminado = cabeza;
	if (longitud == 0)
	    rabo = cabeza = null;
	else {
	    cabeza.siguiente.anterior = null;
	    cabeza = cabeza.siguiente;
	}
	return eliminado.elemento;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if (rabo == null)
	    throw new NoSuchElementException();
	longitud--;
	Nodo eliminado = rabo;
	if (longitud == 0)
	    rabo = cabeza = null;
	else {
	    rabo.anterior.siguiente = null;
	    rabo = rabo.anterior;
	}
	return eliminado.elemento;
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> opuesta = new Lista<T>();
	Nodo b = cabeza;
	while (b != null) {
	    opuesta.agregaInicio(b.elemento);
	    b = b.siguiente;
	}
	return opuesta;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> igual = new Lista<T>();
	Nodo b = cabeza;
	while (b != null) {
	    igual.agrega(b.elemento);
	    b = b.siguiente;
	}
	return igual;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
        cabeza = rabo = null;
	longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
	if (cabeza == null)
	    throw new NoSuchElementException();
	return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
	if (rabo == null)
	    throw new NoSuchElementException();
	return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i < 0 || i >= longitud)
	    throw new ExcepcionIndiceInvalido();
	Nodo b = cabeza;
	for (int c = 0 ; c < i ; c++)
	    b = b.siguiente;
	return b.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
	if (elemento == null)
	    return -1;
	Nodo b = cabeza;
	for (int c = 0 ; c < longitud ; c++) {
	    if (elemento.equals(b.elemento))
		return c;
	    b = b.siguiente;
	}
	return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if (rabo == null)
	    return "[]";
        String s = "[";
	Nodo b = cabeza;
	while (b.siguiente != null) {
	    s += b.elemento.toString();
	    s += ", ";
	    b = b.siguiente;
	}
	s += b.elemento.toString();
	s += "]";
	return s;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if (longitud != lista.longitud)
	    return false;
	Nodo a = cabeza;
	Nodo b = lista.cabeza;
	while (a != null) {
	    if (!a.elemento.equals(b.elemento))
		return false;
	    a = a.siguiente;
	    b = b.siguiente;
	}
	return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        if (longitud <= 1)
	    return copia();
	Lista<T> a = new Lista<T>();
	Lista<T> b = new Lista<T>();
	Nodo n = cabeza;
	for (int i = 0; i < (longitud / 2); i++) {
	    a.agrega(n.elemento);
	    n = n.siguiente;
	}
	while (n != null) {
	    b.agrega(n.elemento);
	    n = n.siguiente;
	}
	return merge(a.mergeSort(comparador), b.mergeSort(comparador), comparador);
    }

    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
	if (elemento == null)
	    return false;
	Nodo b = cabeza;
	while (b != null) {
	    if (elemento.equals(b.elemento))
		return true;
	    if (comparador.compare(b.elemento, elemento) > 0)
		return false;
	    b = b.siguiente;
	}
	return false;
    }

    /**
     * Busca el nodo que contiene al elemento recibido.
     * @param elemento el elemento a buscar.
     * @return el primer nodo que contiene al <code>elemento</code> que se busca
     *         <code>null</code> si no lo encuentra.
     */
    private Nodo buscaNodo(T elemento) {
	if (elemento == null)
	    return null;
	Nodo b = cabeza;
	while (b != null) {
	    if (elemento.equals(b.elemento)) 
		return b;
	    b = b.siguiente;
	}
	return null;
    }

    /**
     * Elimina el nodo recibido de la lista.
     * @param nodo el nodo a eliminar de la lista.
     */
    private void eliminaNodo(Nodo nodo) {
	if (nodo == null)
	    return;
	if (nodo == cabeza)
	    eliminaPrimero();
	else if (nodo == rabo)
	    eliminaUltimo();
	else {
	    longitud--;
	    nodo.anterior.siguiente = nodo.siguiente;
	    nodo.siguiente.anterior = nodo.anterior;
	}
    }

    /**
     * Recibe dos listas ordenadas y las mezcla de tal forma que se mantenga el orden.
     * @param a la primera lista ordenada.
     * @param b la segunda lista ordenada.
     * @param comparador el comparador a utilizar para ordenar las listas.
     * @return una lista ordenada con todos los elementos de las recibidas.
     */
    private Lista<T> merge(Lista<T> a, Lista<T> b, Comparator<T> comparador) {
	Lista<T> nueva = new Lista<T>();
	while (!a.esVacia() && !b.esVacia())
	    if (comparador.compare(a.getPrimero(), b.getPrimero()) > 0)
		nueva.agrega(b.eliminaPrimero());
	    else
		nueva.agrega(a.eliminaPrimero());
	if (a.esVacia())
	    concatena(nueva, b);
	else
	    concatena(nueva, a);
	return nueva;
    }

    /**
     * Recibe dos listas y concatena la segunda al final de la primera.
     * @param a la lista en cuyo final se concatenará la otra.
     * @param b la lista que será concatenada al final de la otra.
     */
    private void concatena(Lista<T> a, Lista<T> b) {
	if (!a.esVacia() && !b.esVacia()) {
	    a.rabo.siguiente = b.cabeza;
	    b.cabeza.anterior = a.rabo;
	    a.rabo = b.rabo;
	    a.longitud += b.longitud;
	} else if (a.esVacia())
	    a = b;
    }
    
    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
