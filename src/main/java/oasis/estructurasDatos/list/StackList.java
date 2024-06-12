package oasis.estructurasDatos.listas;

import oasis.estructurasDatos.interfaces.StackInterface;

public class StackList <T> implements StackInterface <T> {

    private Nodo<T> cima;

    public StackList() {
        this.cima = null;
    }
    @Override//Agrega un elemento
    public void push(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
    }

    @Override//elimina y devuelve el elemento en la parte de arriba de la pila
    public T pop() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T elemento = cima.valor;
        cima = cima.siguiente;
        return elemento;
    }

    @Override//devuelve el elemento sin eliminarlo
    public T peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return cima.valor;
    }

    @Override
    public boolean estaVacia() {
        return cima == null;
    }


    private static class Nodo<T>{
        private T valor;
        private Nodo<T> siguiente;

        public Nodo(T valor) {
            this(valor, null);
        }

        public Nodo(T valor, Nodo<T> siguiente) {
            this.valor = valor;
            this.siguiente = siguiente;
        }

        public T getValor() {
            return valor;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }

    }

    @Override
    public void remove() {
        if (!estaVacia()) {
            cima=cima.getSiguiente();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> temp = cima;
        while (temp != null) {
            sb.append(temp.getValor()).append(" -> ");
            temp = temp.getSiguiente();
        }
        sb.append(" ");//to String
        return sb.toString();
    }


    @Override
    public T[] toArray() {
        int size = size(); // Calcular el tamaño de la pila
        T[] array = (T[]) new Object[size]; // Crear un arreglo del tamaño de la pila
        Nodo<T> temp = cima; // Nodo temporal para recorrer la pila
        int index = 0;

        // Recorrer la pila y agregar los elementos al arreglo
        while (temp != null) {
            array[index++] = temp.getValor();
            temp = temp.getSiguiente();
        }

        return array;
    }

    public int size() {
        int size = 0;
        Nodo<T> temp = cima;
        while (temp != null) {
            size++;
            temp = temp.getSiguiente();
        }
        return size;
    }

    //Metoodo para obtener los elementos de la pila en una linkedList
    public DoubleLinkedList<T> toList() {
        DoubleLinkedList<T> lista = new DoubleLinkedList<>();
        Nodo<T> temp = cima;
        while (temp != null) {
            lista.agregarAlFinal(temp.getValor());
            temp = temp.getSiguiente();
        }
        return lista;
    }

}
