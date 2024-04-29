package introduceDatos;
import java.util.Scanner;

public class Pregunta {

    public static int pideEntero(String pregunta, Scanner entrada) {
        /**
         * Pide al usuario que introduzca un valor de tipo entero.
         * @param entrada String pregunta, una candena de texto que le entra como parámetro desde el main y la entrada del scanner (explicado en pdf)
         * @return valor entero introducido por el usuario.
         */
        int valorIntroducido = 0;
        boolean valorCorrecto = false;
        do{
            System.out.println(pregunta);  
            try{
                valorIntroducido = entrada.nextInt();
                valorCorrecto=true;
                entrada.nextLine(); // nextLine adicional para limpiar la entrada después del nextint porque no me recogia el nuevo nombre
            }catch (Exception e){
                System.out.println("Valor incorrecto!!");
                entrada.next();
            }    
        }while(valorCorrecto == false);
        return valorIntroducido;
    }

    public static double pideDouble(String pregunta, Scanner entrada) {
        /**
         * Pide al usuario que introduzca un valor de tipo double.
         * @param entrada String pregunta, una candena de texto que le entra como parámetro desde el main y la entrada del scanner.
         * @return valor double introducido por el usuario.
         */

        double valorIntroducido = 0;
        boolean valorCorrecto = false;
        do{
            System.out.println(pregunta);  
            try{
                valorIntroducido = entrada.nextDouble();
                valorCorrecto=true;    
            }catch (Exception e){
                System.out.println("Valor incorrecto!!");
                entrada.next();
            }    
        }while (valorCorrecto == false);
        return valorIntroducido;
    }
}        