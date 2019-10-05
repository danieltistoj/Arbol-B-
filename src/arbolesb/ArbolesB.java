/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesb;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class ArbolesB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int []valores = new int[3];
        valores[0] =1;
        valores[1] = 2;
        valores [2] = 3;
        
       for(int conta = 0; conta<2 ; conta++){
           System.out.print(valores[conta]);
       }
        /*
        Scanner input = new Scanner(System.in);
        
        int grado, num_valores, temp;
        System.out.print("Ingrese el grado m del arbol:  ");
        grado = input.nextInt();
        
        Arbol arbol = new Arbol(grado);
        
        while (grado < 2) {
            System.out.print("Por favor ingrese un grado mayor a 1 : ");
            grado = input.nextInt();
        }
      
        
        int opcion, k;

        boolean flag;
        flag = true;
        System.out.println("\tM\tE\tN\tU\n");
        System.out.println("1. Ingresar valor");
        System.out.println("2. mostrar raiz");
        System.out.println("3. Buscar un valor e imprimir el nodo");
        System.out.println("4. Borrar un valor de una hoja");
        System.out.println("5. Exit");

        while (flag)
        {

            System.out.print("\nSeleccionar opcion::");
            opcion = input.nextInt();
            if (opcion == 5) {             
                System.exit(0);
                flag = false;
                break;
            } else {
                switch (opcion) {
                    case 1: 
                        System.out.print("¿Cuantos valores desean ingresar?:");
                       num_valores = input.nextInt();

                        for (int i = 0; i < num_valores; i++) {
                            System.out.print("\nIngrese valor: ");
                            System.out.println(i + 1);
                            temp = input.nextInt();
                            arbol.Insertar(temp,arbol.getRaiz());
                        }
                        break;

                    case 2:
                        for(int num: arbol.getRaiz().getValor()){
                            System.out.print(num+", ");
                        }
                        break;

                    case 3:
                      
                        break;
                    case 4:

                        
                        break;

                    case 5:
                        System.exit(0);
                        break;

                    default: 
                        System.out.println("\nError\n");
                        break;
                }
            }
        }
*/
    }
    
}
