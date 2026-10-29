import java.util.Scanner;
import java.util.Random;

public class App {

    static final Scanner teclado = new Scanner(System.in);
    static final Random generador = new Random();

    // variables la cifra
    static int numeroObjetivo;
    static int numeroAleatorio1, numeroAleatorio2, numeroAleatorio3;
    static int numeroAleatorio4, numeroAleatorio5, numeroAleatorio6;
    static String nombreJugador1, nombreJugador2;
    // variables la letra
    static String vocalAleatoria;
    static String consonanteAleatoria;

    public static void main(String[] args) throws Exception {

        System.out.println("¡BIENVENIDO AL CONCURSO CIFRAS Y LETRAS!");
        System.out.println(
                "En este programa los participantes deben demostrar su habilidad con los números, las operaciones matemáticas y el lenguaje.");
        System.out.println("Este programa dispone de dos tipos de juegos: el modo concurso y un modo rápido");
        System.out.println("¡¡¡Empezamos!!!");
        pedirNombres();
        laCifraExacta();

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

        numeroObjetivo = generador.nextInt(900) + 100;

        numeroAleatorio1 = generador.nextInt(10) + 1;
        numeroAleatorio2 = generador.nextInt(10) + 1;
        numeroAleatorio3 = generador.nextInt(10) + 1;
        numeroAleatorio4 = generador.nextInt(10) + 1;
        numeroAleatorio5 = generador.nextInt(10) + 1;
        numeroAleatorio6 = generador.nextInt(10) + 1;

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

}
