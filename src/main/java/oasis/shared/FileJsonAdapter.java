package oasis.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.estructurasDatos.listas.QueueList;
import oasis.estructurasDatos.listas.StackList;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileJsonAdapter<E> {
    private Object fileWriterLock;

    private FileJsonAdapter() {

        this.fileWriterLock = new Object();
    }

    public static synchronized <E> FileJsonAdapter<E> getInstance() {
        return new FileJsonAdapter<>();
    }

    public DoubleLinkedList<E> getObjects(String pathFile, Class<E[]> classOfT) {
        DoubleLinkedList<E> objList = new DoubleLinkedList<>();
        try {
            Gson gson = new GsonBuilder().create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            E[] objArray = gson.fromJson(bufferedReader, classOfT);
            if (objArray != null) {
                for (E obj : objArray) {
                    objList.agregarAlFinal(obj);
                }
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return objList;
    }

    public Boolean writeObjects(String pathFile, DoubleLinkedList<E> objects) {
        boolean successful = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(pathFile)) {
            synchronized (fileWriterLock) {
                // Limpiar el archivo (eliminar todos los objetos)
                writer.write(""); // Esto eliminará todo el contenido del archivo

                // Escribir los nuevos objetos
                gson.toJson(objects.toArray(), writer);

                successful = true;
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return successful;
    }


    public StackList<E> getObjectsStack(String pathFile, Class<E[]> classOfT) {
        StackList<E> objStack = new StackList<>();
        try {
            Gson gson = new GsonBuilder().create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            E[] objArray = gson.fromJson(bufferedReader, classOfT);
            if (objArray != null) {
                for (E obj : objArray) {
                    objStack.push(obj); // Agregar objetos al StackList
                }
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return objStack;
    }
    public Boolean writeObjectsStack(String pathFile, StackList<E> objects) {
        boolean successful = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(pathFile)) {
            synchronized (fileWriterLock) {
                // Limpiar el archivo (eliminar todos los objetos)
                writer.write(""); // Esto eliminará todo el contenido del archivo

                // Escribir los nuevos objetos
                gson.toJson(objects.toArray(), writer);

                successful = true;
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return successful;
    }

    public QueueList<E> getObjectsQueue(String pathFile, Class<E[]> classOfT) {
        QueueList<E> objQueue = new QueueList<>();
        try {
            Gson gson = new GsonBuilder().create();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            E[] objArray = gson.fromJson(bufferedReader, classOfT);
            if (objArray != null) {
                for (E obj : objArray) {
                    objQueue.enqueue(obj); // Agregar objetos a la cola
                }
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return objQueue;
    }

    public Boolean writeObjectsQueue(String pathFile, QueueList<E> objects) {
        boolean successful = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(pathFile)) {
            synchronized (fileWriterLock) {
                // Limpiar el archivo (eliminar todos los objetos)
                writer.write(""); // Esto eliminará todo el contenido del archivo

                // Escribir los nuevos objetos
                gson.toJson(objects.toArray(), writer);

                successful = true;
            }
        } catch (IOException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage(), e);
        }
        return successful;
}
}
