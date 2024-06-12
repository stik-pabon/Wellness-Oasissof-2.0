package oasis.estructurasDatos.listas;
import oasis.estructurasDatos.interfaces.QueueInterface;


public class QueueList<T> implements  QueueInterface <T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;

    private int size = 0;

    public QueueList() {
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }

    public void enqueue(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (estaVacia()) {
            inicio = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
        }
        fin = nuevoNodo;
        size++; // Incrementar el tamaño al agregar un elemento
    }

    public T dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T elemento = inicio.elemento;
        inicio = inicio.siguiente;
        if (inicio == null) {
            fin = null;
        }
        size--; // Decrementar el tamaño al quitar un elemento
        return elemento;
    }

    public T peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return inicio.elemento;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int tamano() {
        return size; // Devolver el tamaño actual de la cola
    }

    private static class Nodo<T> {
        private T elemento;
        private Nodo<T> siguiente;

        public Nodo(T elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }

        public T getElemento() {
            return elemento;
        }

        public void setElemento(T elemento) {
            this.elemento = elemento;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }


    }

    @Override
    public T dequeue(T clienteAEliminar) {
        if (estaVacia()) {
            throw new IndexOutOfBoundsException("La cola está vacía");
        }

        if (inicio.getElemento().equals(clienteAEliminar)) {
            return dequeue();
        }

        Nodo<T> guia = inicio;
        while (guia.getSiguiente() != null && !guia.getSiguiente().getElemento().equals(clienteAEliminar)) {
            guia = guia.getSiguiente();
        }
        //condición en el bucle while se detiene cuando el siguiente nodo no es nulo
        //(lo que significa que hay más nodos en la cola) y el valor del siguiente nodo no es igual al cliente

        if (guia.getSiguiente() != null) {
            if (guia.getSiguiente() == fin) {
                fin = guia;
            }
            T valor = guia.getSiguiente().getElemento();
            guia.setSiguiente(guia.getSiguiente().getSiguiente());//actualizamos referencias algo asi como eliminar en doblemente enlazada
            return valor;
        } else {
            throw new IndexOutOfBoundsException("El cliente especificado no está en la cola");
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> temp = inicio;
        while (temp != null) {
            sb.append(temp.getElemento()).append(" -> ");
            temp = temp.getSiguiente();
        }
        sb.append(" ");
        return sb.toString();
    }

    //toArray
    public Object[] toArray() {
        Object[] array = new Object[tamano()];
        Nodo<T> temp = inicio;
        for (int i = 0; i < array.length; i++) {
            array[i] = temp.getElemento();
            temp = temp.getSiguiente();
        }
        return array;
    }

}
