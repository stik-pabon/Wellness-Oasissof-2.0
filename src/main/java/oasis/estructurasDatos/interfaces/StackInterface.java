package oasis.estructurasDatos.interfaces;

public interface StackInterface <T>{
    void push(T element);
    T pop();
    T peek();
    boolean estaVacia();
    void remove();//Metodo agregado para cumpli con los requerimientos del B
    T[] toArray(); //Metodo agregado para cumpli con los requerimientos del B
    int size(); //Metodo agregado para cumpli con los requerimientos del B
}
