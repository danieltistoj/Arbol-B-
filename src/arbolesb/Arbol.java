/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesb;

import java.util.Arrays;

/**
 *
 * @author Usuario
 */
public class Arbol {
    private Nodo raiz;
    private int grado;
    private int altura =0;

    public Arbol(int grado) {
        this.grado = grado;
        raiz = new Nodo(this.grado,null);
    }

  
   
    private void InsertarValor(int valor, Nodo nodo_actual){
        int posicion = nodo_actual.getContador(); //posicion es la cantidad de valores que tiene el nodo. 
        if(nodo_actual.esHoja()){// si el nodo es una hoja 
    
                while (posicion >= 1 && valor < nodo_actual.getValor()[posicion - 1])//buscar la posición de insercion
                    {
                        nodo_actual.getValor()[posicion] = nodo_actual.getValor()[posicion - 1];//desplaza los valores
                        posicion--;//desde posición maxima a minima
                    }

                    nodo_actual.getValor()[posicion] = valor;//Luego de ordenar se inserta el valor
                    nodo_actual.setContador(nodo_actual.getContador()+1); //incrementar el numero de valores
                }
        else{
            System.out.println("Dividir");
        }
    }
    private void Dividir(int valor, Nodo nodo_actual){
        if(nodo_actual.getPadre()==null&&nodo_actual.esHoja()){
            Nodo ValoresMenor = new Nodo(grado,nodo_actual),ValoresIgualMayor = new Nodo(grado,nodo_actual); // se crean los hijos del nodo 
            int []array_aux = new int[this.grado];
            int conta=0,posicionAdividir;
            for(int num:nodo_actual.getValor()){//pasamos todos los 
                array_aux[conta] = num;
                conta++;
            }
            array_aux[this.grado] = valor;
            Arrays.sort(array_aux);// se ordena el array auxiliar 
            posicionAdividir = (this.grado/2); // se encuentra la posicion del valor que tiene que subir.
            for(conta=0; conta<posicionAdividir ;conta++){
                
            }
         
        }
            
          
    }
    public void Insertar(int valor, Nodo nodo_actual){
        if(nodo_actual.getContador() != grado - 1){
            InsertarValor(valor,nodo_actual);
        }
        else{
            
            
        }
        
    }
    
 
    private void Ordenar(Nodo nodo){
        
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
   
    
}
