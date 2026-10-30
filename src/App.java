import java.util.Scanner;
import java.util.Random;

public class App {


    public static void main(String[] args)  {
        System.out.println("Aqui iría la presentacion del programa");
        
        
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
