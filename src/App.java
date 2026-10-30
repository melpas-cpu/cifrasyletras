import java.util.Scanner;
import java.util.Random;

public class App {

    static Scanner teclado = new Scanner(System.in);
    static Random random = new Random();

    static String jugador1;
    static String jugador2;

    static int puntosGlobales1 = 0;
    static int puntosGlobales2 = 0;

    static int puntosPrueba1;
    static int puntosPrueba2;

    static int repeticiones = 1;
    static int cantidadLetras = 10;
    static int cantidadOperaciones = 5;

    static boolean concurso = false;


    public static void main(String[] args)  {
        System.out.println("Aqui iría la presentacion del programa");
        
        
    }

    static int leerNumero(String mensaje) {

        int numero;

        while (true) {
            System.out.print(mensaje);

            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                teclado.nextLine();
                return numero;
            } else {
                System.out.println("Debes introducir un numero.");
                teclado.nextLine();
            }
        }
    }

    // LEER TEXTO

    static String leerTexto(String mensaje) {

        String texto;

        do {
            System.out.print(mensaje);
            texto = teclado.nextLine().trim(); //trim es una clase de metodo String que sirve para eliminar los espacios en blanco que quedan al inicio y fin.

            if (texto.length() == 0) {
                System.out.println("El texto no puede estar vacio.");
            }

        } while (texto.length() == 0);

        return texto;
    }


    static void calculadoraHumana(int cantidad) {

        System.out.println("===== LA CALCULADORA HUMANA =====");
        System.out.println("Pon a prueba tu calculo mental por rondas.");
        System.out.println("Resuelve operaciones en cadena comenzando con un numero inicial.");
        System.out.println("Gana el menor tiempo. Cada fallo suma 20 segundos de penalizacion.");

        double tiempo1 = turnoCalculadora(jugador1, cantidad);
        double tiempo2 = turnoCalculadora(jugador2, cantidad);

        // Menos tiempo final significa mas puntos.
        puntosPrueba1 = 100000 - (int) tiempo1;
        puntosPrueba2 = 100000 - (int) tiempo2;

        if (puntosPrueba1 < 0) {
            puntosPrueba1 = 0;
        }

        if (puntosPrueba2 < 0) {
            puntosPrueba2 = 0;
        }

        System.out.println("Tiempo final de " + jugador1 + ": "
                + tiempo1 + " segundos");

        System.out.println("Tiempo final de " + jugador2 + ": "
                + tiempo2 + " segundos");
    }

    static double turnoCalculadora(String jugador, int cantidad) {

        System.out.println("\nTurno de " + jugador);
        System.out.println("Pulsa INTRO para empezar.");
        teclado.nextLine();

        int numero = random.nextInt(100) + 1;
        int errores = 0;

        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= cantidad; i++) {

            int operacion = random.nextInt(4) + 1;
            int valor;
            int resultado;

            if (operacion == 1 || operacion == 2) {
                valor = random.nextInt(90) + 10;
            } else {
                valor = random.nextInt(9) + 1;
            }

            // Buscamos un divisor para que la division sea exacta.

            if (operacion == 4) {
                while (numero % valor != 0) {
                    valor = random.nextInt(9) + 1;
                }
            }

            if (operacion == 1) {
                resultado = numero + valor;
                System.out.println(numero + " + " + valor);
            } else if (operacion == 2) {
                resultado = numero - valor;
                System.out.println(numero + " - " + valor);
            } else if (operacion == 3) {
                resultado = numero * valor;
                System.out.println(numero + " * " + valor);
            } else {
                resultado = numero / valor;
                System.out.println(numero + " / " + valor);
            }

            int respuesta = leerNumero("Resultado: ");

            if (respuesta == resultado) {
                System.out.println("Correcto.");
            } else {
                System.out.println("Incorrecto. El resultado era " + resultado);
                errores++;
            }

            numero = resultado;
        }

        long fin = System.currentTimeMillis();

        double segundos = (fin - inicio) / 1000.0;
        double tiempoFinal = segundos + errores * 20;

        System.out.println("Errores: " + errores);
        System.out.println("Tiempo real: " + segundos);
        System.out.println("Penalizacion: " + errores * 20 + " segundos");

        return tiempoFinal;
    }

    // Esta es la prueba original
    static void secuenciaMisteriosa() {

        Random random = new Random();

        System.out.println("===== LA SECUENCIA MISTERIOSA =====");
        System.out.println("Descubre el numero que continua.");
        System.out.println("La regla sera sumar o multiplicar.");
        System.out.println("Cada acierto vale 1 punto.");
        System.out.println("La prueba tiene 10 rondas.");

        // Esta prueba tiene diez rondas.
        for (int ronda = 1; ronda <= 10; ronda++) {

            System.out.println(" --- Ronda " + ronda + " ---");

            int tipoOperacion = random.nextInt(2);

            int numero1;
            int numero2;
            int numero3;
            int respuestaCorrecta;
            int cantidad;

            if (tipoOperacion == 0) {

                // Secuencia de sumas.
                numero1 = random.nextInt(50) + 20;
                cantidad = random.nextInt(25) + 10;

                numero2 = numero1 + cantidad;
                numero3 = numero2 + cantidad;
                respuestaCorrecta = numero3 + cantidad;

            } else {

                // Secuencia de multiplicaciones.
                numero1 = random.nextInt(20) + 5;
                cantidad = random.nextInt(5) + 3;

                numero2 = numero1 * cantidad;
                numero3 = numero2 * cantidad;
                respuestaCorrecta = numero3 * cantidad;
            }

            System.out.println("Secuencia: " + numero1 + " - " + numero2 + " - " + numero3 + " - ?");

            // Turno del primer jugador.
            System.out.println("Turno de " + jugador1);
            int respuesta1 = leerNumero("Numero siguiente: ");

            if (respuesta1 == respuestaCorrecta) {
                System.out.println("Correcto. Ganas 1 punto.");
                puntosPrueba1++;
            } else {
                System.out.println("Incorrecto. No ganas puntos.");
            }

            // Turno del segundo jugador.
            System.out.println("\nTurno de " + jugador2);
            int respuesta2 = leerNumero("Numero siguiente: ");

            if (respuesta2 == respuestaCorrecta) {
                System.out.println("Correcto. Ganas 1 punto.");
                puntosPrueba2++;
            } else {
                System.out.println("Incorrecto. No ganas puntos.");
            }

            // Se revela la solucion despues de ambos turnos.
            System.out.println("La respuesta correcta era: "
                    + respuestaCorrecta);

            System.out.println(jugador1 + ": " + puntosPrueba1 + " puntos");
            System.out.println(jugador2 + ": " + puntosPrueba2 + " puntos");
        }

    
}

}
