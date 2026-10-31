import java.util.Scanner;
import java.util.Random;

public class App {

    static Scanner teclado = new Scanner(System.in);
    static Random random = new Random();

    // Nombres de los jugadores
    static String nombreJugador1;
    static String nombreJugador2;

    // Marcador global (concurso) y marcador de la prueba en curso
    static int puntosGlobales1 = 0;
    static int puntosGlobales2 = 0;
    static int puntosPrueba1;
    static int puntosPrueba2;

    // Configuración (el menú de configuración se hará después)
    static int repeticiones = 1;
    static int cantidadLetras = 10;
    static int cantidadOperaciones = 5;

    // Variables de La cifra exacta
    static int numeroObjetivo;
    static int numeroAleatorio1, numeroAleatorio2, numeroAleatorio3;
    static int numeroAleatorio4, numeroAleatorio5, numeroAleatorio6;

    // Variable de La palabra más larga
    static String letras;

    //------PROGRAMA PRINCIPAL------

    public static void main(String[] args) {

        System.out.println("¡BIENVENIDO AL CONCURSO CIFRAS Y LETRAS!");
        System.out.println(
                "En este programa los participantes deben demostrar su habilidad con las operaciones matemáticas y el lenguaje.");
        System.out.println("Este programa dispone de dos tipos de juegos: el modo concurso y un modo rápido");
        System.out.println("¡¡¡Empezamos!!!");
        pedirNombres();

        System.out.println("\n¿Qué modalidad quieres jugar?");
        System.out.println("1. Modo concurso");
        System.out.println("2. Modo rápido");

        int opcion = leerNumeroEntre("Elige [1-2]: ", 1, 2);

        if (opcion == 1) {
            modoConcurso();
            mostrarMarcadorFinal();
        } else {
            jugarModoRapido();
        }
    }

    //------MODOS DE JUEGO------

    public static void modoConcurso() {
        System.out.println("\n===== HAS ELEGIDO EL MODO CONCURSO =====");
        System.out.println("Jugaréis a las cuatro pruebas correlativamente");
        System.out.println("¡Adelante!");

        // Se juegan las cuatro pruebas en orden y cada ganador suma 1 punto global
        for (int prueba = 1; prueba <= 4; prueba++) {
            int ganador = ejecutarPrueba(prueba);
            actualizarMarcador(ganador);
        }

    }

    public static void jugarModoRapido() {
        System.out.println("\n===== MODO RÁPIDO =====");
        System.out.println("¿Qué prueba quieres jugar?");
        System.out.println("1. La cifra exacta");
        System.out.println("2. La palabra más larga");
        System.out.println("3. La calculadora humana");
        System.out.println("4. La secuencia misteriosa");

        int prueba = leerNumeroEntre("Elige [1-4]: ", 1, 4);
        ejecutarPrueba(prueba);
    }

    // Ejecuta la prueba indicada, anuncia el resultado y devuelve el ganador
    // (1, 2 o 0 si hay empate). Se usa tanto en el concurso como en el modo rápido.
    public static int ejecutarPrueba(int prueba) {
        int ganador;

        switch (prueba) {
            case 1:
                ganador = laCifraExacta();
                break;
            case 2:
                ganador = laPalabraMasLarga();
                break;
            case 3:
                ganador = calculadoraHumana(cantidadOperaciones);
                break;
            default:
                ganador = secuenciaMisteriosa();
        }

        anunciarGanador(ganador);
        return ganador;
    }

    public static void anunciarGanador(int ganador) {
        System.out.println();
        if (ganador == 0) {
            System.out.println(">>> La prueba termina en EMPATE <<<");
        } else {
            System.out.println(">>> ¡Gana la prueba " + obtenerNombre(ganador) + "! <<<");
        }
        System.out.println();
    }

    // ------MARCADOR------

    public static void actualizarMarcador(int ganador) {
        if (ganador == 1) {
            puntosGlobales1++;
        } else if (ganador == 2) {
            puntosGlobales2++;
        }
    }

    public static void mostrarMarcadorFinal() {
        System.out.println("\n===== MARCADOR FINAL =====");
        System.out.println(nombreJugador1 + ": " + puntosGlobales1 + " puntos");
        System.out.println(nombreJugador2 + ": " + puntosGlobales2 + " puntos");

        if (puntosGlobales1 > puntosGlobales2) {
            System.out.println("¡Ha ganado " + nombreJugador1 + "!");

        } else if (puntosGlobales2 > puntosGlobales1) {
            System.out.println("¡Ha ganado " + nombreJugador2 + "!");
        } else {
            System.out.println("¡El concurso ha terminado en empate!");
        }
    }

    // ------ NOMBRES ------

    public static void pedirNombres() {
        System.out.println("Jugador 1, ¿cómo te llamas?");
        nombreJugador1 = teclado.nextLine().trim();
        while (nombreJugador1.length() == 0) {
            System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo:");
            nombreJugador1 = teclado.nextLine().trim();
        }

        System.out.println("Jugador 2, ¿cómo te llamas?");
        nombreJugador2 = teclado.nextLine().trim();
        while (nombreJugador2.length() == 0) {
            System.out.println("El nombre no puede estar vacío. Inténtalo de nuevo:");
            nombreJugador2 = teclado.nextLine().trim();
        }
    }

    public static String obtenerNombre(int jugador) {
        if (jugador == 1) {
            return nombreJugador1;
        } else {
            return nombreJugador2;
        }
    }

    // ------PRUEBA 1: LA CIFRA EXACTA------

    public static int laCifraExacta() {

        System.out.println("\n===== LA CIFRA EXACTA =====");
        System.out.println("En el primer juego, el programa generará un número de tres cifras y seis números auxiliares");
        System.out.println("Tendréis que acercaros lo máximo posible al número objetivo mediante operaciones aritméticas");
        System.out.println("Tendréis 5 oportunidades por jugador");
        System.out.println("El ganador será el que haya conseguido el resultado más cercano");
        System.out.println("Si ambos obtenéis la misma diferencia, se producirá un empate");
        System.out.println("Es tu turno, jugador número 1. ¡Mucha suerte!");

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

        System.out.println("\nNúmero objetivo: " + numeroObjetivo);
        System.out.println(nombreJugador1 + ": " + resultadoJugador1 + " (diferencia " + diferenciaJugador1 + ")");
        System.out.println(nombreJugador2 + ": " + resultadoJugador2 + " (diferencia " + diferenciaJugador2 + ")");

        return decidirGanadorCifra(diferenciaJugador1, diferenciaJugador2);
    }

    // LÓGICA: gana la menor diferencia. Devuelve 1, 2 o 0 si empatan
    public static int decidirGanadorCifra(int diferencia1, int diferencia2) {
        if (diferencia1 < diferencia2) {

            return 1;
        } else if (diferencia2 < diferencia1) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int jugarTurno(int jugador) {

        System.out.println("\nTurno de " + obtenerNombre(jugador));
        mostrarNumeros();

        int resultado = 0;

        int numeroElegido1 = pedirNumeroDisponible("Elige el primer número que quieres seleccionar: ");

        for (int i = 0; i < 5; i++) {

            int numeroElegido2 = pedirNumeroDisponible("Elige el segundo número que quieres seleccionar: ");

            boolean interruptor;

            do {
                interruptor = true;
                System.out.println("¿Qué operación quieres hacer?");
                System.out.println("[1] Sumar");
                System.out.println("[2] Restar");
                System.out.println("[3] Multiplicar");
                System.out.println("[4] Dividir");
                int opcion = leerNumero("Seleccionar la opción [1-4]: ");

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
                        // Solo se permite si no es entre 0 y la división es exacta
                        if (numeroElegido2 == 0 || numeroElegido1 % numeroElegido2 != 0) {
                            System.out.println("Lo sentimos, esta división no es válida. ¡Elige otra operación! :)");
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
            System.out.println("Es tu intento número: " + (i + 1));

            if (resultado == numeroObjetivo) {
                System.out.println("¡" + obtenerNombre(jugador) + ", has llegado al número objetivo!");
                break;
            }

            mostrarNumeros();
            System.out.println("Acabó tu turno");
            System.out.println("Tu número conseguido ha sido: " + numeroElegido1);
        }

        return resultado;
    }

    // Pide un número hasta que sea uno de los seis disponibles
    public static int pedirNumeroDisponible(String mensaje) {
        int numero = leerNumero(mensaje);
        while (!estaDisponible(numero)) {
            numero = leerNumero("No has elegido un número disponible: ");
        }
        return numero;
    }

    // LÓGICA: true si el número es uno de los seis generados
    public static boolean estaDisponible(int n) {
        return n == numeroAleatorio1 || n == numeroAleatorio2 || n == numeroAleatorio3
                || n == numeroAleatorio4 || n == numeroAleatorio5 || n == numeroAleatorio6;
    }

    public static void mostrarNumeros() {
        System.out.println("Número objetivo: " + numeroObjetivo);
        System.out.println("Números disponibles: " + numeroAleatorio1 + " - " + numeroAleatorio2 + " - "
                + numeroAleatorio3 + " - " + numeroAleatorio4 + " - " + numeroAleatorio5 + " - "

                + numeroAleatorio6);
    }

    // LÓGICA: false si es una división entre 0 o no exacta; true en cualquier otro
    // caso
    static boolean esOperacionValida(int a, int b, int operacion) {
        if (operacion == 4) {
            return b != 0 && a % b == 0;
        }
        return true;
    }

    // ------ PRUEBA 2: LA PALABRA MÁS LARGA ------

    public static int laPalabraMasLarga() {

        System.out.println("\n===== LA PALABRA MÁS LARGA =====");

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

        // Una palabra que no existe vale 0 letras
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

        return decidirGanadorLetras(longitud1, longitud2);
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

    //gana la palabra más larga. Devuelve 1, 2 o 0 si empatan
    static int decidirGanadorLetras(int longitud1, int longitud2) {
        if (longitud1 > longitud2) {
            return 1;
        } else if (longitud2 > longitud1) {
            return 2;
        } else {
            return 0;
        }
    }

    //true si la palabra se puede formar respetando cuántas veces aparece cada letra
    static boolean sePuedeFormar(String palabra, String letrasDisponibles) {
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (contarLetra(palabra, letra) > contarLetra(letrasDisponibles, letra)) {
                return false;
            }

        }
        return true;
    }

    //cuenta cuántas veces aparece una letra en un texto
    static int contarLetra(String texto, char letra) {
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == letra) {
                contador = contador + 1;
            }
        }
        return contador;
    }

    //4 vocales + 4 consonantes garantizadas, y 2 letras extra
    static String generarLetras() {
        String resultado = "";

        for (int i = 0; i < 4; i++) {
            resultado = resultado + vocalAleatoria();
        }

        for (int i = 0; i < 4; i++) {
            resultado = resultado + consonanteAleatoria();
        }

        // Las extra tienen más probabilidad de ser vocal (3 de cada 4)
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

    // ------LECTURA DE DATOS------

    // Pide un número entero y repite hasta que se escriba uno válido
    static int leerNumero(String mensaje) {

        int numero;

        while (true) {
            System.out.print(mensaje);

            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                teclado.nextLine(); // limpia el salto de línea pendiente
                return numero;
            } else {

                System.out.println("Debes introducir un numero.");
                teclado.nextLine();
            }
        }
    }

    // Pide un número entero entre un mínimo y un máximo
    static int leerNumeroEntre(String mensaje, int minimo, int maximo) {
        int numero = leerNumero(mensaje);
        while (numero < minimo || numero > maximo) {
            System.out.println("El número debe estar entre " + minimo + " y " + maximo + ".");
            numero = leerNumero(mensaje);
        }
        return numero;
    }

    // ------ PRUEBA 3: LA CALCULADORA HUMANA------
    

    static int calculadoraHumana(int cantidad) {

        System.out.println("\n===== LA CALCULADORA HUMANA =====");
        System.out.println("Pon a prueba tu calculo mental por rondas.");
        System.out.println("Resuelve operaciones en cadena comenzando con un numero inicial.");
        System.out.println("Gana el menor tiempo. Cada fallo suma 20 segundos de penalizacion.");

        double tiempo1 = turnoCalculadora(nombreJugador1, cantidad);
        double tiempo2 = turnoCalculadora(nombreJugador2, cantidad);

        System.out.println("\nTiempo final de " + nombreJugador1 + ": " + tiempo1 + " segundos");
        System.out.println("Tiempo final de " + nombreJugador2 + ": " + tiempo2 + " segundos");

        // Gana quien tarda menos

        if (tiempo1 < tiempo2) {
            return 1;
        } else if (tiempo2 < tiempo1) {
            return 2;
        } else {
            return 0;
        }
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
                valor = random.nextInt(90) + 10; // suma y resta: dos cifras
            } else {
                valor = random.nextInt(9) + 1; // multiplicación y división: una cifra
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

            // El resultado correcto es el número inicial de la siguiente operación
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

    // ------PRUEBA 4: LA SECUENCIA MISTERIOSA------

    static int secuenciaMisteriosa() {

        // Reiniciamos los puntos de esta prueba.
        puntosPrueba1 = 0;
        puntosPrueba2 = 0;

        System.out.println("\n===== LA SECUENCIA MISTERIOSA =====");
        System.out.println("Descubre el numero que continua.");
        System.out.println("La regla sera sumar o multiplicar.");
        System.out.println("Cada acierto vale 1 punto.");
        System.out.println("La prueba tiene 10 rondas.");

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

            System.out.println("Turno de " + nombreJugador1);
            int respuesta1 = leerNumero("Numero siguiente: ");

            if (respuesta1 == respuestaCorrecta) {
                System.out.println("Correcto. Ganas 1 punto.");
                puntosPrueba1++;
            } else {
                System.out.println("Incorrecto. No ganas puntos.");
            }

            // Turno del segundo jugador.
            System.out.println("\nTurno de " + nombreJugador2);
            int respuesta2 = leerNumero("Numero siguiente: ");

            if (respuesta2 == respuestaCorrecta) {
                System.out.println("Correcto. Ganas 1 punto.");
                puntosPrueba2++;
            } else {
                System.out.println("Incorrecto. No ganas puntos.");
            }

            // Se revela la solucion despues de ambos turnos.
            System.out.println("La respuesta correcta era: " + respuestaCorrecta);

            System.out.println(nombreJugador1 + ": " + puntosPrueba1 + " puntos");
            System.out.println(nombreJugador2 + ": " + puntosPrueba2 + " puntos");
        }

        // Decidimos quién ha ganado.
        if (puntosPrueba1 > puntosPrueba2) {
            return 1;
        } else if (puntosPrueba2 > puntosPrueba1) {

            return 2;
        } else {
            return 0;
        }
    }

}