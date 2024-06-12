package oasis.estructurasDatos.interfaces;


public interface QueueInterface <T>{
    void enqueue(T elemento);
    T dequeue();
    T peek();
    boolean estaVacia();
    T dequeue(T clienteAEliminar);//Otro metodo agregado para cumplir con los requerimientos de A
}
