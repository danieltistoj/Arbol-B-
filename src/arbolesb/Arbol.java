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
        if(nodo_actual.getPadre()==null&&nodo_actual.esHoja()){ // esta condicion es cuando se realiza la primera division en un arbol b+, para que se trasforme en un arbol b+ de 2 niveles.
            Nodo ValoresMenor = new Nodo(grado,nodo_actual),ValoresIgualMayor = new Nodo(grado,nodo_actual); // se crean los hijos del nodo 
            int []array_aux = new int[this.grado], nuevoArrayPadre = new int[this.grado-1];
            int conta=0,posicionAdividir, valorAsubir;
            for(int num:nodo_actual.getValor()){//pasamos todos los 
                array_aux[conta] = num;
                conta++;
            }
            array_aux[this.grado-1] = valor;
            Arrays.sort(array_aux);// se ordena el array auxiliar 
            posicionAdividir = (this.grado/2); // se encuentra la posicion del valor que tiene que subir, la posicion del valor que ahora pasara a estar en el arreglo del nodo padre.
            valorAsubir = array_aux[posicionAdividir];// se encuentra el valor que debe se subir o ser insertado en el array del nodo padre. 
            for(conta=0; conta<posicionAdividir ;conta++){ // con este ciclo insertamos los valores en el nodo o hijo que tendra los valores menores al numero que va a subir, que es el numero que tenga la posicion m/2. 
                InsertarValor(array_aux[conta],ValoresMenor);
            }
            for(conta = posicionAdividir; conta<grado; conta++){// con este ciclo insertamos los valores en el nodo o hijo que tendra los valores iguales o mayores a el numero que va a subir y que estara en el nodo padre. 
                InsertarValor(array_aux[conta],ValoresIgualMayor);
            }
            
            ValoresMenor.setSiguiente(ValoresIgualMayor);//El nodo que contiene los numero menores al numero que subio apunta al nodo que contiene los valores mayores al numero que subio 
            
            nodo_actual.setValor(nuevoArrayPadre);// el nodo padre en principio se deja sin hijos 
            nodo_actual.setContador(0);// el contador de valores se deja a cero 
            nodo_actual.setHoja(false);// ahora el nodo padre no sera una hoja 
            nodo_actual.getValor()[0] = valorAsubir; //se ingresa el valor a subir en la posicion cero. 
            
            nodo_actual.getHijo()[0]= ValoresMenor;
            nodo_actual.getHijo()[1] = ValoresIgualMayor; 
            
            
         
        }
            
          
    }
    public void Insertar(int valor, Nodo nodo_actual){
        if(nodo_actual.getContador() != grado - 1){
            InsertarValor(valor,nodo_actual);
        }
        else{
            Dividir(valor, nodo_actual);
            
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
