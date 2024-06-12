package oasis.estructurasDatos.listas;

import oasis.estructurasDatos.interfaces.DoubleLinkedListInterface;
import oasis.model.domain.paciente.Paciente;

public class DoubleLinkedList<T> implements DoubleLinkedListInterface<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;
    int contador = 0;

    public DoubleLinkedList() {
        cabeza = null;
        cola = null;
        tamano = 0;
    }

    @Override
    public void agregarAlInicio(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor, cabeza, null);
        if (cabeza != null) {
            cabeza.setAnterior(nuevoNodo);
        }
        cabeza = nuevoNodo;
        if (cola == null) {
            cola = nuevoNodo;
        }
        tamano++;
    }

    @Override
    public void agregarAlFinal(T valor) {
        Nodo<T> nuevoNodo = new Nodo<>(valor, null, cola);
        if (cola != null) {
            cola.setSiguiente(nuevoNodo);
        }
        cola = nuevoNodo;
        if (cabeza == null) {
            cabeza = nuevoNodo;
        }
        tamano++;
    }

    @Override
    public void insertarEnMedio(T valor, int indice) {
        if (indice >= tamano || indice <0) {
            throw new UnsupportedOperationException("Fuera de los limites de la lista"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        else if (indice == 0) {//Si el indice es 1 reutilizamos agregarAlInicio()
            agregarAlInicio(valor);
            return;
        }else if (indice == tamano-1) {//Si el indice es igual al tamano, reutilizamos agregarAlFinal()
            agregarAlFinal(valor);
            return;
        }
        Nodo<T> actual = cabeza;

        for (int i = 0; i < indice - 1; i++) {

            actual = actual.getSiguiente();//Se recorre la lista nodo por nodo
        }

        Nodo<T> nuevoNodo = new Nodo<>(valor, actual.getSiguiente(),actual); //Nuevo nodo que va antes de actual.getSiguiente y despues de actual
        actual.getSiguiente().setAnterior(nuevoNodo);// Se cambia el puntero de ac
        actual.setSiguiente(nuevoNodo);
        tamano++;

    }



    @Override
    public T eliminarDelInicio() {
        if(estaVacia()) {
            throw new UnsupportedOperationException("La lista esta vacia"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody


        }else if(tamano==1) {//Si solo tiene un elemento, tamano y cola son el mismo valor y ahora seran nulos tras eliminarlos
            T valor =  cabeza.getValor();
            cabeza = null;
            cola = null;
            tamano = 0;//La lista queda vacia
            return valor;

        }
        T eliminado = cabeza.getValor();
        Nodo<T> nodoActual = cabeza.getSiguiente(); // Mantenemos una referencia al nodo anterior
        nodoActual.setAnterior(null); // establecemos como null la referencia anterior a nodoActual, osea la cabeza que vamos a eliminar
        cabeza= nodoActual;	// cabeza ahora es nodoActual
        tamano--; //disminuye el tamano

        return eliminado;

    }

    @Override
    public T eliminarDelFinal() {
        if(estaVacia()) {
            throw new UnsupportedOperationException("La lista esta vacia"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            //Si la lista esta vacia, muestra el siguiente error

        }else if(tamano==1) {
            T valor =  cabeza.getValor();
            cabeza = null;
            cola = null;
            tamano = 0;
            return valor;

        } // Si tiene contenido
        T eliminado = cola.getValor(); // se almacena el valor a eliminar para poderlo retornar sin que sea nulo
        Nodo<T> actual = cola; //Se inicia desde la cola
        Nodo<T> anterior = actual.getAnterior(); // Mantenemos una referencia al nodo anterior
        anterior.setSiguiente(null);// Siguiendo con la referencia de anterior, decimos que su siguiente sera nulo, es decir el ultimo nodo (cola)
        cola = anterior; //Ahora anterior es la nueva cola

        tamano--;
        return eliminado;
    }



    @Override
    public T eliminarEnMedio(int indice) {
        if (indice >= tamano || indice < 0) {
            throw new UnsupportedOperationException("Índice fuera de los límites de la lista");
        }
        if (indice == 0) { // Si el elemento a eliminar es la primera posición, reutilizamos el método eliminarDelInicio()
            return eliminarDelInicio();
        }
        if (indice == tamano - 1) { // Si el elemento a eliminar es igual al tamaño menos uno, reutilizamos el método eliminarDelFinal()
            return eliminarDelFinal();
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente(); // Se recorre la lista nodo por nodo hasta llegar al nodo a eliminar
        }

        Nodo<T> anterior = actual.getAnterior();
        Nodo<T> siguiente = actual.getSiguiente();
        anterior.setSiguiente(siguiente);
        siguiente.setAnterior(anterior);

        T eliminado = actual.getValor();
        tamano--;
        return eliminado;
    }


    @Override
    public int buscarElemento(T valor) {
        Nodo<T> actual = cabeza;//Inicializar el nodo actual por la cabeza
        int indice=0;//Necesitamos inicializar un indice para saber la posicion del elemento actual
        if(estaVacia() == true) {//Si esta vacia, marca error
            throw new UnsupportedOperationException("Fuera de los limites de la lista");
        }
        while (actual != null) {//Busca que el actual nodo no sea igual nulo para asi pasar a la validación y comparación de que tenga algo
            if(actual.getValor().equals(valor)) {//Obtiene el valor con el get y lo compara con el valor escrito o dijitado
                //System.out.println("Se encontro un valor: "+actual.getValor());
                return indice;//Retorna el indice de la posicion del nodo
            }
            actual = actual.getSiguiente();//Si no se encuentra un igual simplemente  pasa al siguiente nodo obteniendo la información de aquel
            indice++;//Y aumentando su indice para indicarle al programa o al usuario de que con forme avanza el nodo avanza el indice

        }
        System.out.println("No se encontro el valor");
        return -1;//Si ninguno es similar retorna un -1 indicado en los requerimientos del programa

    }

    @Override
    public boolean estaVacia() {
        if(tamano == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public void mostrarLista() {
        Nodo<T> actual = cabeza;
        while(actual!= null) {
            System.out.println( actual.getValor());//Se imprime el valor
            actual = actual.getSiguiente();// se pasa al siguiente nodo al siguiente
        }
    }



    //Metodo toArray() para convertir la lista en un arreglo
    public T[] toArray() {
        T[] arreglo = (T[]) new Object[tamano];
        Nodo<T> actual = cabeza;
        for (int i = 0; i < tamano; i++) {
            arreglo[i] = actual.getValor();
            actual = actual.getSiguiente();
        }
        return arreglo;
    }

    // Clase interna Nodo
    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;
        Nodo<T> anterior;

        public Nodo(T valor){
            this(valor, null, null);
        }

        public Nodo(T valor, Nodo<T> siguiente, Nodo<T> anterior) {
            this.valor = valor;
            this.siguiente = siguiente;
            this.anterior = anterior;
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

        public Nodo<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<T> anterior) {
            this.anterior = anterior;
        }


    }


    //@Override
    public  T navegarMetodo(int posicion) {
        if (estaVacia()) {
            System.out.println("No hay páginas en el historial.");
            return null;
        }


        if (posicion == 1) {
            if ( contador == 0) {
                System.out.println("Ya estás en la primera página del historial.");
                T valor = buscarPorIndice(contador).getValor();
                return  valor;

            }
            contador--;
            T valor = buscarPorIndice(contador).getValor();
            return  valor;
        }

        if (posicion == 2) {


            if (contador == tamano-1) {

                System.out.println("Ya estás en la última página del historial.");
                T valor = buscarPorIndice(contador).getValor();
                return  valor;
            }
            contador++;
            T valor = buscarPorIndice(contador).getValor();
            return  valor;
        }



        System.out.println("Opción de navegación no válida.");
        return null;

    }
    public Nodo<T> buscarPorIndice(int indice) {

        Nodo<T>actual=cabeza;
        for(int i = 0; i< indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    } //Planeaba reutilizar este metodo en el de navegarMetodo, pero no me funciono bien y decidi hacerlo por los nodos y las referencias directamente en el otro metodo

    public T buscarPorIndiceIterar(int indice) {
        if (indice < 0 || indice >= tamano()) { // Verificar si el índice está fuera de los límites de la lista
            return null; // Retornar null si el índice no es válido
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getValor(); // Retornar el valor del nodo actual después de iterar hasta el índice deseado
    }
    //buscar un objeto y devuelve un objeto
    public T buscarElementoPorObjeto(T valor) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
