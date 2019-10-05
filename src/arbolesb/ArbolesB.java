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
        Arbol arbol = new Arbol(4);
         arbol.Insertar(5,arbol.getRaiz());
        
        arbol.Insertar(7,arbol.getRaiz());
       
        arbol.Insertar(10,arbol.getRaiz());
        
        arbol.Insertar(9,arbol.getRaiz());
        arbol.Insertar(11,arbol.getRaiz());
        arbol.Insertar(8,arbol.getRaiz());
        arbol.Insertar(3,arbol.getRaiz());
        arbol.Insertar(12,arbol.getRaiz());
        /*
        arbol.Insertar(10,arbol.getRaiz());
        arbol.Insertar(27,arbol.getRaiz());
        arbol.Insertar(29,arbol.getRaiz());
        arbol.Insertar(17,arbol.getRaiz());
        
        arbol.Insertar(25,arbol.getRaiz());
        arbol.Insertar(21,arbol.getRaiz());
        arbol.Insertar(15,arbol.getRaiz());
        
        arbol.Insertar(31,arbol.getRaiz());
        
        arbol.Insertar(13,arbol.getRaiz());
       
        arbol.Insertar(51,arbol.getRaiz());
        
        arbol.Insertar(20,arbol.getRaiz());
        arbol.Insertar(24,arbol.getRaiz());
        arbol.Insertar(48,arbol.getRaiz());
        arbol.Insertar(19,arbol.getRaiz());
        */
        System.out.println("Cantidad de valores en padre: "+arbol.getRaiz().getContador());
        //arbol.Insertar(12,arbol.getRaiz());
            
        System.out.println("Valores Nodo padre");
        for(int num: arbol.getRaiz().getValor()){
            System.out.print(num+", ");
        }
       System.out.println("\nValores Nodos Hijo");
       
       int conta1=0, conta2=0;
       for(Nodo nodo: arbol.getRaiz().getHijo()){
           if(nodo!=null){
               conta1++;
           }
       }
       
       while(conta2<conta1){
           Nodo nodo = arbol.getRaiz().getHijo()[conta2];
           for(int conta=0;conta<nodo.getValor().length;conta++){
               System.out.print(nodo.getValor()[conta]+", ");
           }
           System.out.println("");
           conta2++;
       }
        System.out.println("Buscar Valor");
        System.out.println("Buscar 2: "+arbol.Buscar(2,arbol.getRaiz()));
        System.out.println("Buscar 5: "+arbol.Buscar(5,arbol.getRaiz()));
        System.out.println("Buscar 3:"+arbol.Buscar(3,arbol.getRaiz()));
        System.out.println("Buscar 7:"+arbol.Buscar(7,arbol.getRaiz()));
       
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
