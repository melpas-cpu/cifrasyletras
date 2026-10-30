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

     // variables la cifra
    static int numeroObjetivo;
    static int numeroAleatorio1, numeroAleatorio2, numeroAleatorio3;
    static int numeroAleatorio4, numeroAleatorio5, numeroAleatorio6;
    static String nombreJugador1, nombreJugador2;
    // variables la letra
    static String vocalAleatoria;
    static String consonanteAleatoria;
    static String letras;


    public static void main(String[] args) {

        System.out.println("¡BIENVENIDO AL CONCURSO CIFRAS Y LETRAS!");
        System.out.println(
                "En este programa los participantes deben demostrar su habilidad con los números, las operaciones matemáticas y el lenguaje.");
        System.out.println("Este programa dispone de dos tipos de juegos: el modo concurso y un modo rápido");
        System.out.println("¡¡¡Empezamos!!!");
        pedirNombres();
        laCifraExacta();
        //laPalabraMasLarga();
        //CalculadoraHumana();

    }

    public static void pedirNombres() {
        System.out.println("Jugador 1, ¿cómo te llamas?");
        nombreJugador1 = teclado.nextLine();
        while (nombreJugador1.length() == 0) {
            System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo:");
            nombreJugador1 = teclado.nextLine();
        }

        System.out.println("Jugador 2, ¿cómo te llamas?");
        nombreJugador2 = teclado.nextLine();
        while (nombreJugador2.length() == 0) {
            System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo:");
            nombreJugador2 = teclado.nextLine();
        }
    }

    public static String obtenerNombre(int jugador) {
        if (jugador == 1) {
            return nombreJugador1;
        } else {
            return nombreJugador2;
        }
    }

    public static void laCifraExacta() {

        numeroObjetivo = random.nextInt(900) + 100;

        numeroAleatorio1 = random.nextInt(10) + 1;
        numeroAleatorio2 = random.nextInt(10) + 1;
        numeroAleatorio3 = random.nextInt(10) + 1;
        numeroAleatorio4 = random.nextInt(10) + 1;
        numeroAleatorio5 = random.nextInt(10) + 1;
        numeroAleatorio6 = random.nextInt(10) + 1;

        int resultadoJugador1 = jugarTurno(1);
        int resultadoJugador2 = jugarTurno(2);

        int diferenciaJugador1 = Math.abs(numeroObjetivo - resultadoJugador1);
        int diferenciaJugador2 = Math.abs(numeroObjetivo - resultadoJugador2);

        System.out.println("Número objetivo: " + numeroObjetivo);
        System.out.println("Jugador 1: " + resultadoJugador1 + " (diferencia " + diferenciaJugador1 + ")");
        System.out.println("Jugador 2: " + resultadoJugador2 + " (diferencia " + diferenciaJugador2 + ")");

        if (diferenciaJugador1 < diferenciaJugador2) {
            System.out.println("¡Gana el jugador 1!");
        } else if (diferenciaJugador2 < diferenciaJugador1) {
            System.out.println("¡Gana el jugador 2!");
        } else {
            System.out.println("¡Empate!");
        }
    }

    public static int jugarTurno(int jugador) {

        System.out.println("Turno del jugador " + jugador);
        mostrarNumeros();

        int numeroElegido1 = 0;
        int resultado = 0;

        System.out.println("Elige el primer número que quieres seleccionar: ");
        numeroElegido1 = Integer.parseInt(teclado.next());

        while (numeroAleatorio1 != numeroElegido1 && numeroAleatorio2 != numeroElegido1
                && numeroAleatorio3 != numeroElegido1 && numeroAleatorio4 != numeroElegido1
                && numeroAleatorio5 != numeroElegido1 && numeroAleatorio6 != numeroElegido1) {

            System.out.println("No has elegido el número correcto: ");
            numeroElegido1 = Integer.parseInt(teclado.next());
        }

        for (int i = 0; i < 5; i++) {
            int numeroElegido2 = 0;

            System.out.println("Elige el segundo número que quieres seleccionar: ");
            numeroElegido2 = Integer.parseInt(teclado.next());

            while (numeroAleatorio1 != numeroElegido2 && numeroAleatorio2 != numeroElegido2
                    && numeroAleatorio3 != numeroElegido2 && numeroAleatorio4 != numeroElegido2
                    && numeroAleatorio5 != numeroElegido2 && numeroAleatorio6 != numeroElegido2) {

                System.out.println("No has elegido el número correcto: ");
                numeroElegido2 = Integer.parseInt(teclado.next());
            }

            boolean interruptor = true;

            do {
                interruptor = true;
                System.out.println("¿Qué operación quieres hacer?");
                System.out.println("[1] Sumar");
                System.out.println("[2] Restar");
                System.out.println("[3] Multiplicar");
                System.out.println("[4] Dividir");
                System.out.print("Seleccionar la opción [1-4]:");
                int opcion = Integer.parseInt(teclado.next());
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Has elegido sumar...");
                        resultado = numeroElegido1 + numeroElegido2;
                        break;
                    case 2:
                        System.out.println("Has elegido restar");
                        resultado = numeroElegido1 - numeroElegido2;
                        break;
                    case 3:
                        System.out.println("Has elegido multiplicar");
                        resultado = numeroElegido1 * numeroElegido2;
                        break;
                    case 4:
                        System.out.println("Has elegido dividir");
                        if (numeroElegido2 == 0 || numeroElegido1 % numeroElegido2 != 0) {
                            System.out.println("Lo sentimos, esta división no válida. ¡Elige otra operación! :)");
                            interruptor = false;
                        } else {
                            resultado = numeroElegido1 / numeroElegido2;
                        }
                        break;
                    default:
                        System.out.println("Vaya, no has elegido la opción correcta");
                        interruptor = false;
                }
            } while (!interruptor);

            System.out.println("El resultado es: " + resultado);
            numeroElegido1 = resultado;
            System.out.println("Es tu intento número:" + (i + 1));

            if (resultado == numeroObjetivo) {
                System.out.println("¡Jugador " + jugador + ", has llegado al número objetivo!");
                break;

            }

            mostrarNumeros();
            System.out.println("Tu número actual es: " + numeroElegido1);

        }

        return resultado;

    }

    public static void mostrarNumeros() {
        System.out.println("Número objetivo: " + numeroObjetivo);
        System.out.println("Números disponibles: " + numeroAleatorio1 + " - " + numeroAleatorio2 + " - "
                + numeroAleatorio3 + " - " + numeroAleatorio4 + " - " + numeroAleatorio5 + " - "
                + numeroAleatorio6);
    }

    public static void laPalabraMasLarga() {
        letras = generarLetras();

        System.out.println("Letras sorteadas: " + letras);
        System.out.println("¿Quieres repetir el sorteo? [s/n]");

        String respuesta = teclado.nextLine().trim().toLowerCase();

        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println("Responde solo s o n:");
            respuesta = teclado.nextLine().trim().toLowerCase();
        }

        if (respuesta.equals("s")) {
            letras = generarLetras();
            System.out.println("Nuevo sorteo: " + letras);
        }

        String palabra1 = pedirPalabra(1);
        boolean existe1 = confirmarExistencia(palabra1, 2);

        String palabra2 = pedirPalabra(2);
        boolean existe2 = confirmarExistencia(palabra2, 1);
        int longitud1 = 0;
        if (existe1) {
            longitud1 = palabra1.length();
        }
        int longitud2 = 0;
        if (existe2) {
            longitud2 = palabra2.length();
        }

        System.out.println();
        System.out.println("===== RESULTADOS =====");
        System.out.println("Letras: " + letras);
        System.out.println(nombreJugador1 + ": " + palabra1 + " (" + longitud1 + " letras)");
        System.out.println(nombreJugador2 + ": " + palabra2 + " (" + longitud2 + " letras)");

        int ganador = decidirGanadorLetras(longitud1, longitud2);
        if (ganador == 0) {
            System.out.println("¡Empate!");

        } else {
            System.out.println("¡Gana " + obtenerNombre(ganador) + "!");
        }
    }

    static String pedirPalabra(int jugador) {
        System.out.println();
        System.out.println("Turno de " + obtenerNombre(jugador));
        System.out.println("Letras disponibles: " + letras);
        System.out.println("Escribe tu palabra:");
        String palabra = teclado.nextLine().trim().toLowerCase();

        while (palabra.length() == 0 || !sePuedeFormar(palabra, letras)) {
            System.out.println("Esa palabra no se puede formar con las letras. Prueba otra:");
            palabra = teclado.nextLine().trim().toLowerCase();
        }
        return palabra;
    }

    static boolean confirmarExistencia(String palabra, int jugadorRival) {
        System.out.println(obtenerNombre(jugadorRival) + ", comprueba en un diccionario si existe \""
                + palabra + "\".");
        System.out.println("¿Existe? [s/n]:");
        String respuesta = teclado.nextLine().trim().toLowerCase();

        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println("Responde solo s o n:");
            respuesta = teclado.nextLine().trim().toLowerCase();
        }
        return respuesta.equals("s");

    }

    static int decidirGanadorLetras(int longitud1, int longitud2) {
        if (longitud1 > longitud2) {
            return 1;
        } else if (longitud2 > longitud1) {
            return 2;
        } else {
            return 0;
        }
    }

    static boolean sePuedeFormar(String palabra, String letrasDisponibles) {
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (contarLetra(palabra, letra) > contarLetra(letrasDisponibles, letra)) {
                return false;
            }
        }
        return true;

    }

    static int contarLetra(String texto, char letra) {
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == letra) {
                contador = contador + 1;
            }
        }
        return contador;
    }

    static String generarLetras() {
        String resultado = "";

        for (int i = 0; i < 4; i++) {
            resultado = resultado + vocalAleatoria();
        }

        for (int i = 0; i < 4; i++) {
            resultado = resultado + consonanteAleatoria();
        }

        for (int i = 0; i < 2; i++) {
            if (random.nextInt(4) < 3) {
                resultado = resultado + vocalAleatoria();
            } else {
                resultado = resultado + consonanteAleatoria();
            }
        }

        return resultado;

    }

    static char vocalAleatoria() {
        String vocales = "aeiou";
        return vocales.charAt(random.nextInt(5));
    }

    static char consonanteAleatoria() {
        String consonantes = "bcdfghjklmnpqrstvwxyz";
        return consonantes.charAt(random.nextInt(21));
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



