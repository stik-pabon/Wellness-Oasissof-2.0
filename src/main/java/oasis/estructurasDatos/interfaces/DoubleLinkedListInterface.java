package oasis.estructurasDatos.interfaces;

import oasis.model.domain.paciente.Paciente;

public interface DoubleLinkedListInterface<T> {
    void agregarAlInicio(T valor);
    void agregarAlFinal(T valor);
    T eliminarDelInicio();
    T eliminarDelFinal();
    boolean estaVacia();
    int tamano();
    void insertarEnMedio (T valor, int indice);
    T eliminarEnMedio ( int indice);
    int buscarElemento (T valor);
    void mostrarLista ();
    //T navegarMetodo (int posicion);


}