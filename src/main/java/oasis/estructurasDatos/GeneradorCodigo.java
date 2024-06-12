package oasis.estructurasDatos;

import java.util.Random;

public class GeneradorCodigo {

    // Método para generar un código compuesto por una letra y un número aleatorio
    public String generarCodigo(String tipoCita, String especialidad) {
        try {
            Random random = new Random();

            // Obtener la letra correspondiente a la especialidad y al tipo de cita
            String letraEspecialidad = obtenerLetraEspecialidad(especialidad);
            String letraTipoCita = obtenerLetraTipoCita(tipoCita);

            // Generar un número aleatorio entre 100 y 999
            int numeroAleatorio = random.nextInt(900) + 100; // Rango de 100 a 999

            // Concatenar las letras de la especialidad, tipo de cita y el número aleatorio
            String codigo = letraEspecialidad + letraTipoCita + numeroAleatorio;

            return codigo;
        } catch (IllegalArgumentException e) {
            // Captura la excepción y muestra el mensaje de error
            System.out.println("Error al generar código: " + e.getMessage());
            return null; // Devuelve null para indicar que ocurrió un error
        }
    }


    // Método auxiliar para obtener la letra correspondiente a la especialidad
    private String obtenerLetraEspecialidad(String especialidad) {
        switch (especialidad) {
            case "MEDICINA_GENERAL":
                return "M";
            case "PEDIATRIA":
                return "P";
            case "CARDIOLOGIA":
                return "C";
            case "DERMATOLOGIA":
                return "D";
            case "GINECOLOGIA":
                return "G";
            case "PSIQUIATRIA":
                return "PS";
            case "NUTRICION":
                return "N";
            case "ODONTOLOGIA":
                return "O";
            default:
                throw new IllegalArgumentException("Especialidad no válida: " + especialidad);
        }
    }

    // Método auxiliar para obtener la letra correspondiente al tipo de cita
    private String obtenerLetraTipoCita(String tipoCita) {
        switch (tipoCita) {
            case "CONTROL":
                return "C";
            case "VALORACION":
                return "V";
            case "EXAMEN":
                return "E";
            default:
                throw new IllegalArgumentException("Tipo de cita no válido: " + tipoCita);
        }
    }

    // Método para generar un identificador aleatorio de 5 caracteres (letra + números)
    public String generarIdentificador() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generar la letra aleatoria
        char letra = generarLetraAleatoria();
        sb.append(letra);

        // Generar los números aleatorios
        for (int i = 0; i < 3; i++) {
            int numeroAleatorio = random.nextInt(10); // Números del 0 al 9
            sb.append(numeroAleatorio);
        }

        return sb.toString();
    }

    // Método auxiliar para generar una letra aleatoria (mayúscula)
    private char generarLetraAleatoria() {
        Random random = new Random();
        // Rango de letras mayúsculas en ASCII (A=65, Z=90)
        return (char) (random.nextInt(26) + 'A');
    }



}
