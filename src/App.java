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
    static String letras;

    public static void main(String[] args) throws Exception {

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
            if (generador.nextInt(4) < 3) {
                resultado = resultado + vocalAleatoria();
            } else {
                resultado = resultado + consonanteAleatoria();
            }
        }

        return resultado;

    }

    static char vocalAleatoria() {
        String vocales = "aeiou";
        return vocales.charAt(generador.nextInt(5));
    }

    static char consonanteAleatoria() {
        String consonantes = "bcdfghjklmnpqrstvwxyz";
        return consonantes.charAt(generador.nextInt(21));
    }

    public static void CalculadoraHumana(){

        Scanner sc = new Scanner(System.in);

        Random random = new Random();

        int numero = random.nextInt(100) + 1;
        int penalizacion = 0;

        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= 5; i++) {
            
            int operacion = random.nextInt(4);
            int numero2;
            int resultado = 0;

            if (operacion == 0 || operacion == 1){
                numero2= random.nextInt(90) + 10;
            } else {
                numero2 = random.nextInt(9) + 1;
            }

            System.out.println(numero);

            if (operacion==0) {
                System.out.print(" + " + numero2 + " = ");
                resultado = numero + numero2;
            } else if (operacion==1) {
                System.out.print(" - " + numero2 + " = ");
                resultado= numero - numero2;
            } else if (operacion==2) {
                System.out.print(" * " + numero2 + " = ");
                resultado= numero * numero2;   
            } else {
                while (numero / numero2 !=0) {
                    numero2 = random.nextInt(9) + 1;
                }
                System.out.print(" / " + numero2 + " = ");
                resultado= numero / numero2;
            }

                int respuesta = sc.nextInt();

            if (respuesta== resultado) {
                System.out.println("Correcto");
            }  else {
                System.out.println("Incorrecto. Era " + resultado);
                penalizacion = penalizacion + 20;
            }

            numero = resultado;
        }

        long fin = System.currentTimeMillis();

        long tiempo = (fin - inicio) / 1000 + penalizacion;

        System.out.println("Penalizacion:" + penalizacion + " segundos ");
        System.out.println("Tiempo total" + tiempo + " segundos");

        sc.close();
    }
}
