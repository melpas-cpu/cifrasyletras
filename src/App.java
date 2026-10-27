import java.util.Scanner;
import java.util.random.RandomGenerator;
import java.util.Random;

public class App {

    static final Scanner teclado = new Scanner(System.in);
    static final Random generador = new Random();

    public static void main(String[] args) throws Exception {

        System.out.println("¡BIENVENIDO AL CONCURSO CIFRAS Y LETRAS!");
        System.out.println(
                "En este programa los participantes deben demostrar su habilidad con los números, las operaciones matemáticas y el lenguaje.");
        System.out.println("Este programa dispone de dos tipos de juegos: el modo concurso y un modo rápido");
        System.out.println("¡¡¡Empezamos!!!");
        laCifraExacta();

    }

    public static void laCifraExacta() {

        // numero de tres cifras//

        // int numeroObjetivo = generador.nextInt(900) + 100;
        int numeroObjetivo = generador.nextInt(900) + 100;
        System.out.println("Este es el número que tienes que conseguir = " + numeroObjetivo);

        // numeros auxiliares//
        int numeroAleatorio1 = generador.nextInt(10) + 1;
        int numeroAleatorio2 = generador.nextInt(10) + 1;
        int numeroAleatorio3 = generador.nextInt(10) + 1;
        int numeroAleatorio4 = generador.nextInt(10) + 1;
        int numeroAleatorio5 = generador.nextInt(10) + 1;
        int numeroAleatorio6 = generador.nextInt(10) + 1;

        System.out.println("Aquí tienes los seis números que puedes elegir para llegar a tu número objetivo:");
        System.out.println("[-]" + numeroAleatorio1);
        System.out.println("[-]" + numeroAleatorio2);
        System.out.println("[-]" + numeroAleatorio3);
        System.out.println("[-]" + numeroAleatorio4);
        System.out.println("[-]" + numeroAleatorio5);
        System.out.println("[-]" + numeroAleatorio6);

        // pedimos los dos numeros que ha elegido//

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

            // crear switch//
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
                        resultado = numeroElegido1 / numeroElegido2;
                        break;

                    default:
                        System.out.println("No has elegido la opción correcta");
                        interruptor = false;
                }
            }

            while (!interruptor);

            System.out.println("El resultado es: " + resultado);
            numeroElegido1 = resultado;
            System.out.println("Es tu intento número:" + (i+1));

        }
        if(numeroObjetivo==resultado){
            System.out.println("has ganado");
        }else{
            System.out.println("has perdido");
            System.out.println(numeroObjetivo + "  " + resultado);
        }

    }

}
