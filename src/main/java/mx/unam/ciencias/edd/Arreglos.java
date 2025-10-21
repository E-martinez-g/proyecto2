package mx.unam.ciencias.edd;

import java.util.Comparator;

/**
 * Clase para ordenar y buscar arreglos genéricos.
 */
public class Arreglos {

    /* Constructor privado para evitar instanciación. */
    private Arreglos() {}

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordenar el arreglo.
     */
    public static <T> void
    quickSort(T[] arreglo, Comparator<T> comparador) {
        qS(arreglo, 0, (arreglo.length - 1), comparador);
    }

    /**
     * Ordena un subarreglo utilizando QuickSort.
     * @param <T> el tipo del que puede ser el arreglo.
     * @param a el arreglo al que pertenece el subarreglo.
     * @param ini el índice inicial del subarreglo.
     * @param fin el índice final del subarreglo.
     * @param comparador el comparador que se usará para ordenar el
     *        subarreglo.
     */
    private static <T> void
    qS(T[] a, int ini, int fin, Comparator<T> comparador) {
	if (ini >= fin)
	    return;
	int i = ini + 1;
	int j = fin;
	while (i < j)
	    if (comparador.compare(a[i], a[ini]) <= 0)
		i++;
	    else if (comparador.compare(a[j], a[ini]) > 0)
		j--;
	    else
		intercambia(a, i++, j--);
	if(comparador.compare(a[i], a[ini]) > 0)
	    i--;
	intercambia(a, ini, i);
	qS(a, ini, (i - 1), comparador);
	qS(a, (i + 1), fin, comparador);
    }

    /**
     * Ordena el arreglo recibido usando QuickSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    quickSort(T[] arreglo) {
        quickSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo a ordenar.
     * @param comparador el comparador para ordernar el arreglo.
     */
    public static <T> void
    selectionSort(T[] arreglo, Comparator<T> comparador) {
        for (int i = 0; i < arreglo.length; i++) {
	    int m = i;
	    for (int j = i+1; j < arreglo.length; j++)
		if (comparador.compare(arreglo[m], arreglo[j]) > 0)
		    m = j;
	    intercambia(arreglo, m, i);
	}
    }

    /**
     * Ordena el arreglo recibido usando SelectionSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     */
    public static <T extends Comparable<T>> void
    selectionSort(T[] arreglo) {
        selectionSort(arreglo, (a, b) -> a.compareTo(b));
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo el arreglo dónde buscar.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador para hacer la búsqueda.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T> int
    busquedaBinaria(T[] arreglo, T elemento, Comparator<T> comparador) {
	if (elemento == null)
	    return -1;
	return busquedaBinaria(arreglo, 0, (arreglo.length - 1), elemento, comparador);
    }

    /**
     * Revisa a la mitad de un subarreglo si el elemento es el que buscamos,
     * si es mayor, revisa recursivamente a su izquierda y si es menor revisa
     * recursivamente a su derecha. Regresa el índice en que lo encuentre o
     * -1 si no lo encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param a el arreglo dentro del que se encuentra el subarreglo
     * @param ini el primer índice del subarreglo.
     * @param fin el último índice del subarreglo.
     * @param elemento el elemento que estamos buscando.
     * @param comparador el comparador para hacer la búsqueda
     * @return el índice del elemento en el arreglo o -1 si no está.
     */
    private static <T> int
    busquedaBinaria(T[] a, int ini, int fin, T elemento, Comparator<T> comparador) {
	if (fin < ini)
	    return -1;
	int medio = ini + fin;
	medio /= 2;
	if (comparador.compare(a[medio], elemento) == 0)
	    return medio;
	if (ini == fin)
	    return -1;
	if (comparador.compare(a[medio], elemento) > 0)
	    return busquedaBinaria(a, ini, (medio - 1), elemento, comparador);
	return busquedaBinaria(a, (medio + 1), fin, elemento, comparador);
    }

    /**
     * Hace una búsqueda binaria del elemento en el arreglo. Regresa el índice
     * del elemento en el arreglo, o -1 si no se encuentra.
     * @param <T> tipo del que puede ser el arreglo.
     * @param arreglo un arreglo cuyos elementos son comparables.
     * @param elemento el elemento a buscar.
     * @return el índice del elemento en el arreglo, o -1 si no se encuentra.
     */
    public static <T extends Comparable<T>> int
    busquedaBinaria(T[] arreglo, T elemento) {
        return busquedaBinaria(arreglo, elemento, (a, b) -> a.compareTo(b));
    }

    /**
     * Intercambia de posición dos elementos en un arreglo.
     * @param <T> el tipo del que puede ser el arreglo. 
     * @param a el arreglo en que el intercambio se realizará.
     * @param x el índice del primer elemento a intercambiar.
     * @param y el índice del segundo elemento a intercambiar.
     */
    private static <T> void intercambia(T[] a, int x, int y) {
	T t = a[x];
	a[x] = a[y];
	a[y] = t;
    }
}
