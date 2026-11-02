import java.util.Scanner;
import java.util.Random;

public class App {

    // ==========================================
    // 1. VARIABLES GLOBALES Y CONSTANTES
    // ==========================================

    // Scanner y Random
    static Scanner teclado = new Scanner(System.in);
    static Random random = new Random();

    // Colores : se escriben delante del texto. RESET lo devuelve al original
    static final String RESET = "\u001B[0m";
    static final String CIAN = "\u001B[36m";
    static final String AMARILLO = "\u001B[33m";
    static final String VERDE = "\u001B[32m";
    static final String ROJO = "\u001B[31m";
    static final String AZUL = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";

    // Nombres de los jugadores
    static String nombreJugador1;
    static String nombreJugador2;

    // Marcadores
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

    // ==========================================
    // 2. PROGRAMA PRINCIPAL
    // ==========================================

    public static void main(String[] args) {

        System.out.println();

        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(CIAN + "                                                  " + RESET);
        System.out.println(CIAN + "                 BIENVENIDOS A" + RESET);
        System.out.println(CIAN + "                CIFRAS Y LETRAS" + RESET);
        System.out.println(CIAN + "               EL GRAN CONCURSO" + RESET);
        System.out.println(CIAN + "                                                  " + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        pedirNombres();

        System.out.println(MAGENTA + "\n ¿Qué modalidad quieres jugar?" + RESET);
        System.out.println(MAGENTA + " 1. Modo concurso" + RESET);
        System.out.println(MAGENTA + " 2. Modo rápido" + RESET);

        int opcion = leerNumeroEntre("Elige [1-2]: ", 1, 2);

        if (opcion == 1) {
            modoConcurso();
            mostrarMarcadorFinal();
        } else {
            jugarModoRapido();
        }
    }

    // ==========================================
    // 3. MODOS DE JUEGO
    // ==========================================
    public static void modoConcurso() {
        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(MAGENTA + "        HAS ELEGIDO EL MODO CONCURSO" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        System.out.println("Jugaréis a las cuatro pruebas correlativamente");
        System.out.println(MAGENTA + "¡Adelante!" + RESET);

        // Se juegan las cuatro pruebas en orden y cada ganador suma 1 punto global
        for (int prueba = 1; prueba <= 4; prueba++) {
            int ganador = ejecutarPrueba(prueba);
            actualizarMarcador(ganador);
        }

    }

    public static void jugarModoRapido() {
        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(MAGENTA + "                  MODO RÁPIDO" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        System.out.println(MAGENTA + "¿Qué prueba quieres jugar?" + RESET);
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
        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(MAGENTA + "            RESULTADO DE LA PRUEBA" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        if (ganador == 0) {
            System.out.println(MAGENTA + "¡La prueba termina en EMPATE!" + RESET);
        } else {
            System.out.println(VERDE + "¡Gana la prueba " + obtenerNombre(ganador) + "!" + RESET);

        }
    }

    // ==========================================
    // 4. MARCADOR
    // ==========================================
    public static void actualizarMarcador(int ganador) {

        if (ganador == 1) {
            puntosGlobales1++;
        } else if (ganador == 2) {
            puntosGlobales2++;
        }

    }

    public static void mostrarMarcadorFinal() {

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(CIAN + "                  MARCADOR FINAL" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        System.out.println();
        System.out.println(AMARILLO + "  " + nombreJugador1 + RESET);
        System.out.println(AMARILLO + "  Puntos: " + puntosGlobales1 + RESET);

        System.out.println();
        System.out.println(VERDE + "  " + nombreJugador2 + RESET);
        System.out.println(VERDE + "  Puntos: " + puntosGlobales2 + RESET);

        System.out.println();
        System.out.println(CIAN + "--------------------------------------------------" + RESET);

        if (puntosGlobales1 > puntosGlobales2) {

            System.out.println(AMARILLO + "       ¡HA GANADO " + nombreJugador1 + "!" + RESET);

        } else if (puntosGlobales2 > puntosGlobales1) {

            System.out.println(VERDE + "       ¡HA GANADO " + nombreJugador2 + "!" + RESET);

        } else {

            System.out.println(MAGENTA + "       ¡EL CONCURSO HA TERMINADO EN EMPATE!" + RESET);

        }

        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println();

    }

    // ==========================================
    // 5. INTERFAZ
    // ==========================================
    public static void linea(String simbolo, int veces) {
        for (int i = 0; i < veces; i++) {
            System.out.print(simbolo);
        }
    }

    public static void titulo(String texto, int numeroJuego) {

        int ancho = 48;

        System.out.println();

        // Línea superior
        System.out.println(CIAN + "=".repeat(ancho) + RESET);

        // Nombre del concurso
        System.out.printf(CIAN + "%" + ((ancho + 15) / 2)
                + "s%n" + RESET, "CIFRAS Y LETRAS");

        // Separador
        System.out.println(CIAN + "-".repeat(ancho) + RESET);

        System.out.println();

        // Nombre de la prueba
        System.out.printf(CIAN + "%" + ((ancho + texto.length()) / 2)
                + "s%n" + RESET, texto);

        System.out.println();

        // Línea inferior
        System.out.println(CIAN + "=".repeat(ancho) + RESET);

        System.out.println();
    }

    // ==========================================
    // 6. GESTIÓN DE JUGADORES
    // ==========================================

    public static void pedirNombres() {

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(MAGENTA + "             PRESENTACIÓN DE CONCURSANTES" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println();

        System.out.print(AMARILLO + "  JUGADOR 1: " + RESET);
        nombreJugador1 = teclado.nextLine().trim();

        while (nombreJugador1.length() == 0) {
            System.out.print(ROJO + "  El nombre no puede estar vacío. Inténtalo de nuevo: " + RESET);
            nombreJugador1 = teclado.nextLine().trim();
        }

        System.out.println();

        System.out.print(VERDE + "  JUGADOR 2: " + RESET);
        nombreJugador2 = teclado.nextLine().trim();

        while (nombreJugador2.length() == 0) {
            System.out.print(ROJO + "  El nombre no puede estar vacío. Inténtalo de nuevo: " + RESET);
            nombreJugador2 = teclado.nextLine().trim();
        }

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println();
    }

    // MÉTODO PARA MOSTRAR EL NOMBRE CON SU COLOR
    public static String obtenerNombre(int jugador) {

        if (jugador == 1) {
            return AMARILLO + nombreJugador1 + RESET;
        } else {
            return MAGENTA + nombreJugador2 + RESET;
        }

    }

    // ==========================================
    // 7. PRUEBA 1: LA CIFRA EXACTA
    // ==========================================

    public static int laCifraExacta() {

        titulo("LA CIFRA EXACTA", 1);

        System.out.println();
        System.out.println("  El objetivo es alcanzar el número de tres cifras");
        System.out.println("  utilizando los seis números auxiliares.");
        System.out.println();
        System.out.println("  Podréis realizar operaciones aritméticas para");
        System.out.println("  conseguir el resultado más cercano posible.");
        System.out.println();

        System.out.println(MAGENTA + "  ¡Disponéis de 5 oportunidades cada uno!" + RESET);

        System.out.println();
        System.out.println("  Ganará quien consiga la menor diferencia");
        System.out.println("  respecto al número objetivo.");
        System.out.println();

        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println();

        System.out.println(AMARILLO + "       ¡ES TU TURNO, " + nombreJugador1 + "!" + RESET);
        System.out.println(MAGENTA + "             ¡MUCHA SUERTE!" + RESET);
        System.out.println();

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

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(MAGENTA + "                 RESULTADO FINAL" + RESET);
        System.out.println(CIAN + "==================================================" + RESET);

        System.out.println(AMARILLO + nombreJugador1 + RESET + ": "
                + resultadoJugador1 + " (diferencia " + diferenciaJugador1 + ")");

        System.out.println(VERDE + nombreJugador2 + RESET + ": "
                + resultadoJugador2 + " (diferencia " + diferenciaJugador2 + ")");

        System.out.println();
        System.out.println("Número objetivo: " + numeroObjetivo);

        return decidirGanadorCifra(diferenciaJugador1, diferenciaJugador2);
    }

    // * LÓGICA: gana la menor diferencia. Devuelve 1, 2 o 0 si empatan *

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

        String colorJugador;

        if (jugador == 1) {
            colorJugador = AMARILLO;
        } else {
            colorJugador = VERDE;
        }

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println(CIAN + "                 TURNO DE " + RESET + obtenerNombre(jugador));
        System.out.println(CIAN + "==================================================" + RESET);

        mostrarNumeros();

        int resultado = 0;

        int numeroElegido1 = pedirNumeroDisponible(
                "Elige el primer número que quieres seleccionar: ");

        for (int i = 0; i < 5; i++) {

            System.out.println();
            System.out.println(CIAN + "--------------------------------------------------" + RESET);
            System.out.println(colorJugador + "                  INTENTO " + (i + 1) + " DE 5" + RESET);
            System.out.println(CIAN + "--------------------------------------------------" + RESET);

            System.out.println("Tu número actual es: " + numeroElegido1);

            int numeroElegido2 = pedirNumeroDisponible(
                    "Elige el siguiente número que quieres utilizar: ");

            boolean interruptor;
            String simboloOperacion = "";
            String nombreOperacion = "";

            do {

                interruptor = true;

                System.out.println();
                System.out.println(CIAN + "¿QUÉ OPERACIÓN QUIERES HACER?" + RESET);
                System.out.println("[1] Sumar");
                System.out.println("[2] Restar");
                System.out.println("[3] Multiplicar");
                System.out.println("[4] Dividir");

                int opcion = leerNumero("Selecciona una opción [1-4]: ");

                switch (opcion) {
                    case 1:
                        simboloOperacion = "+";
                        nombreOperacion = "sumar";
                        resultado = numeroElegido1 + numeroElegido2;
                        break;

                    case 2:
                        simboloOperacion = "-";
                        nombreOperacion = "restar";
                        resultado = numeroElegido1 - numeroElegido2;
                        break;

                    case 3:
                        simboloOperacion = "*";
                        nombreOperacion = "multiplicar";
                        resultado = numeroElegido1 * numeroElegido2;
                        break;

                    case 4:
                        simboloOperacion = "/";
                        nombreOperacion = "dividir";

                        if (numeroElegido2 == 0 || numeroElegido1 % numeroElegido2 != 0) {
                            System.out.println(ROJO + "División no válida. Elige otra operación." + RESET);
                            interruptor = false;
                        } else {
                            resultado = numeroElegido1 / numeroElegido2;
                        }
                        break;

                    default:
                        System.out.println(ROJO + "Opción incorrecta. Inténtalo de nuevo." + RESET);
                        interruptor = false;

                }

            } while (!interruptor);

            System.out.println(colorJugador + "Has elegido " + nombreOperacion + RESET);

            System.out.println();
            System.out.println(CIAN + "--------------------------------------------------" + RESET);
            System.out.println(colorJugador + "                   RESULTADO" + RESET);
            System.out.println(CIAN + "--------------------------------------------------" + RESET);

            System.out.println("              " + numeroElegido1 + " " + simboloOperacion
                    + " " + numeroElegido2 + " = " + resultado);

            int diferencia = Math.abs(numeroObjetivo - resultado);

            // * Volvemos a mostrar los números disponibles *

            System.out.println();
            System.out.println(AMARILLO + "              NÚMEROS AUXILIARES" + RESET);

            mostrarNumeros();

            numeroElegido1 = resultado;

            if (resultado == numeroObjetivo) {

                System.out.println();
                System.out.println(VERDE + "¡Has conseguido el número objetivo!" + RESET);

                break;

            }

        }

        System.out.println();
        System.out.println(CIAN + "==================================================" + RESET);
        System.out.println("                 FIN DEL TURNO");
        System.out.println(CIAN + "==================================================" + RESET);

        System.out.println(colorJugador + "Resultado conseguido: " + numeroElegido1);

        return resultado;

    }

    // * Pide un número hasta que sea uno de los seis disponibles *

    public static int pedirNumeroDisponible(String mensaje) {

        int numero = leerNumero(mensaje);

        while (!estaDisponible(numero)) {

            numero = leerNumero(ROJO + "No has elegido un número disponible: " + RESET);

        }

        return numero;

    }

    // * LÓGICA: true si el número es uno de los seis generados *

    public static boolean estaDisponible(int n) {

        return n == numeroAleatorio1 || n == numeroAleatorio2 || n == numeroAleatorio3
                || n == numeroAleatorio4 || n == numeroAleatorio5 || n == numeroAleatorio6;

    }

    public static void mostrarNumeros() {

        System.out.println();

        System.out.println(CIAN + "              NÚMERO OBJETIVO" + RESET);
        System.out.println(CIAN + "                     " + numeroObjetivo + RESET);

        System.out.println();

        System.out.println(AMARILLO + "             NÚMEROS DISPONIBLES" + RESET);

        System.out.println(AMARILLO + "             " + numeroAleatorio1 + " - "
                + numeroAleatorio2 + " - "
                + numeroAleatorio3 + " - "
                + numeroAleatorio4 + " - "
                + numeroAleatorio5 + " - "
                + numeroAleatorio6 + RESET);

        System.out.println();

    }

    // * false si es una división entre 0 o no exacta; true en cualquier otro caso *

    static boolean esOperacionValida(int a, int b, int operacion) {

        if (operacion == 4) {

            return b != 0 && a % b == 0;

        }

        return true;

    }

    // ==========================================
    // 8. LA PALABRA MÁS LARGA
    // ==========================================

    public static int laPalabraMasLarga() {

        titulo("LA PALABRA MÁS LARGA", 2);

        letras = generarLetras();

        System.out.print(CIAN + "Letras sorteadas: " + RESET);
        for (int i = 0; i < letras.length(); i++) {
            System.out.print(letras.charAt(i));

            if (i < letras.length() - 1) {
                System.out.print(" - ");
            }
        }
        System.out.println();
        System.out.println(MAGENTA + "¿Quieres repetir el sorteo? [s/n]" + RESET);

        String respuesta = teclado.nextLine().trim().toLowerCase();

        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println(ROJO + "Responde solo s o n:" + RESET);
            respuesta = teclado.nextLine().trim().toLowerCase();
        }

        if (respuesta.equals("s")) {
            letras = generarLetras();

            System.out.print(CIAN + "Nuevo sorteo: " + RESET);
            for (int i = 0; i < letras.length(); i++) {
                System.out.print(AMARILLO + letras.charAt(i) + RESET);

                if (i < letras.length() - 1) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
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
        System.out.println(MAGENTA + "===== RESULTADOS =====" + RESET);
        System.out.println(AMARILLO + nombreJugador1 + ": " + palabra1 + " (" + longitud1 + " letras)" + RESET);
        System.out.println(VERDE + nombreJugador2 + ": " + palabra2 + " (" + longitud2 + " letras)" + RESET);

        return decidirGanadorLetras(longitud1, longitud2);
    }

    static String pedirPalabra(int jugador) {
        System.out.println();
        System.out.println(CIAN + "Turno de " + RESET + obtenerNombre(jugador));
        System.out.print("Letras disponibles: ");
        for (int i = 0; i < letras.length(); i++) {
            System.out.print(AMARILLO + letras.charAt(i) + RESET);

            if (i < letras.length() - 1) {
                System.out.print(" - ");
            }
        }
        System.out.println();
        System.out.println(CIAN + "Escribe tu palabra:" + RESET);
        String palabra = teclado.nextLine().trim().toLowerCase();

        while (palabra.length() == 0 || !sePuedeFormar(palabra, letras)) {
            System.out.println(ROJO + "Esa palabra no se puede formar con las letras. Prueba otra:" + RESET);
            palabra = teclado.nextLine().trim().toLowerCase();
        }
        return palabra;
    }

    static boolean confirmarExistencia(String palabra, int jugadorRival) {
        System.out.println(CIAN + obtenerNombre(jugadorRival) + ", comprueba en un diccionario si existe \""
                + palabra + "\"." + RESET);
        System.out.println(MAGENTA + "¿Existe? [s/n]:" + RESET);
        String respuesta = teclado.nextLine().trim().toLowerCase();

        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println(ROJO + "Responde solo s o n:" + RESET);
            respuesta = teclado.nextLine().trim().toLowerCase();
        }
        return respuesta.equals("s");
    }

    // gana la palabra más larga. Devuelve 1, 2 o 0 si empatan
    static int decidirGanadorLetras(int longitud1, int longitud2) {
        if (longitud1 > longitud2) {
            return 1;
        } else if (longitud2 > longitud1) {
            return 2;
        } else {
            return 0;
        }
    }

    // true si la palabra se puede formar respetando cuántas veces aparece cada
    // letra
    static boolean sePuedeFormar(String palabra, String letrasDisponibles) {
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (contarLetra(palabra, letra) > contarLetra(letrasDisponibles, letra)) {
                return false;
            }

        }
        return true;
    }

    // cuenta cuántas veces aparece una letra en un texto
    static int contarLetra(String texto, char letra) {
        int contador = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == letra) {
                contador = contador + 1;
            }
        }
        return contador;
    }

    // 4 vocales + 4 consonantes garantizadas, y 2 letras extra
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

    // ==========================================
    // 9. PRUEBA 3: LA CALCULADORA HUMANA
    // ==========================================

    static int calculadoraHumana(int cantidad) {

        titulo("LA CALCULADORA HUMANA", 3);
        System.out.println("Pon a prueba tu cálculo mental por rondas.");
        System.out.println("Resuelve operaciones en cadena comenzando con un número inicial.");
        System.out.println("Gana el menor tiempo. Cada fallo suma 20 segundos de penalización.");

        double tiempo1 = turnoCalculadora(nombreJugador1, cantidad, 1);
        double tiempo2 = turnoCalculadora(nombreJugador2, cantidad, 2);

        System.out.println("\nTiempo final de " + AMARILLO + nombreJugador1 + RESET
                + ": " + tiempo1 + " segundos");
        System.out.println("Tiempo final de " + VERDE + nombreJugador2 + RESET
                + ": " + tiempo2 + " segundos");

        // Gana quien tarda menos

        if (tiempo1 < tiempo2) {
            return 1;
        } else if (tiempo2 < tiempo1) {
            return 2;
        } else {
            return 0;
        }
    }

    static double turnoCalculadora(String jugador, int cantidad, int numeroJugador) {

    String colorJugador;

    if (numeroJugador == 1) {
        colorJugador = AMARILLO;
    } else {
        colorJugador = VERDE;
    }

    System.out.println("\n" + CIAN + "Turno de " + colorJugador + jugador + RESET);
    System.out.println(MAGENTA + "Pulsa INTRO para empezar." + RESET);
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

        //* Buscamos un divisor para que la división sea exacta *

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
            System.out.println(VERDE + "Correcto." + RESET);
        } else {
            System.out.println(ROJO + "Incorrecto. El resultado era " + resultado + RESET);
            errores++;
        }

        //* El resultado correcto es el número inicial de la siguiente operación *

        numero = resultado;
    }

    long fin = System.currentTimeMillis();

    double segundos = (fin - inicio) / 1000.0;
    double tiempoFinal = segundos + errores * 20;

    System.out.println();
    System.out.println(CIAN + "--------------------------------------------------" + RESET);
    System.out.println(colorJugador + "                  RESUMEN DEL TURNO" + RESET);
    System.out.println(CIAN + "--------------------------------------------------" + RESET);

    System.out.println(ROJO + "Errores: " + errores + RESET);
    System.out.println("Tiempo real: " + segundos + " segundos");
    System.out.println("Penalización: " + errores * 20 + " segundos");

    return tiempoFinal;
}

    // ==========================================
    // 10. PRUEBA 4: LA SECUENCIA MISTERIOSA
    // =========================================

    static int secuenciaMisteriosa() {

        // * Reiniciamos los puntos de esta prueba *

        puntosPrueba1 = 0;
        puntosPrueba2 = 0;

        titulo("LA SECUENCIA MISTERIOSA", 4);

        System.out.println(MAGENTA + "Descubre el número que continúa." + RESET);
        System.out.println("La regla será sumar o multiplicar.");
        System.out.println("Cada acierto vale 1 punto.");
        System.out.println(MAGENTA + "La prueba tiene 10 rondas." + RESET);

        for (int ronda = 1; ronda <= 10; ronda++) {

            System.out.println(CIAN + " --- Ronda " + ronda + " ---" + RESET);

            int tipoOperacion = random.nextInt(2);

            int numero1;
            int numero2;
            int numero3;
            int respuestaCorrecta;
            int cantidad;

            if (tipoOperacion == 0) {

                // * Secuencia de sumas *

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

            System.out.println(CIAN + "Secuencia: " + RESET + AMARILLO
                    + numero1 + " - " + numero2 + " - " + numero3 + " - ?" + RESET);

            // Turno del primer jugador.

            System.out.println(CIAN + "Turno de " + AMARILLO + nombreJugador1 + RESET);
            int respuesta1 = leerNumero("Número siguiente: ");

            if (respuesta1 == respuestaCorrecta) {
                System.out.println(VERDE + "Correcto. Ganas 1 punto." + RESET);
                puntosPrueba1++;
            } else {
                System.out.println(ROJO + "Incorrecto. No ganas puntos." + RESET);
            }

            // * Turno del segundo jugador *

            System.out.println("\n" + CIAN + "Turno de " + VERDE + nombreJugador2 + RESET);
            int respuesta2 = leerNumero("Número siguiente: ");

            if (respuesta2 == respuestaCorrecta) {
                System.out.println(VERDE + "Correcto. Ganas 1 punto." + RESET);
                puntosPrueba2++;
            } else {
                System.out.println(ROJO + "Incorrecto. No ganas puntos." + RESET);
            }

            // Se revela la solución después de ambos turnos

            System.out.println(CIAN + "La respuesta correcta era: " + respuestaCorrecta + RESET);

            System.out.println(AMARILLO + nombreJugador1 + ": " + puntosPrueba1 + " puntos" + RESET);
            System.out.println(VERDE + nombreJugador2 + ": " + puntosPrueba2 + " puntos" + RESET);
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

    // ==================================================
    // 11.MÉTODOS AUXILIARES: LECTURA DE DATOS
    // ==================================================

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

                System.out.println(ROJO + "Debes introducir un numero." + RESET);
                teclado.nextLine();
            }
        }
    }

    // Pide un número entero entre un mínimo y un máximo
    static int leerNumeroEntre(String mensaje, int minimo, int maximo) {
        int numero = leerNumero(mensaje);
        while (numero < minimo || numero > maximo) {
            System.out.println(ROJO + "El número debe estar entre " + minimo + " y " + maximo + "." + RESET);
            numero = leerNumero(mensaje);
        }
        return numero;
    }

}