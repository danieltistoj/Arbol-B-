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
    private int altura =0, PosicionDelHijo, valorAsubir;

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
        else{ // si no es una hoja 
            
             PosicionDelHijo = 0;
            while (PosicionDelHijo < nodo_actual.getContador() && valor > nodo_actual.getValor()[PosicionDelHijo]) {   //Busca la posición del hijo a recorrer			             		
                PosicionDelHijo++;
            }
            if (nodo_actual.getHijo()[PosicionDelHijo].getContador() == grado - 1) {
                Dividir(valor, nodo_actual.getHijo()[PosicionDelHijo]);//Divide el nodo x, index j
                if (valor > nodo_actual.getValor()[PosicionDelHijo]) {
                    PosicionDelHijo++;
                }
            }
            int conta = 0;
            for(int num: nodo_actual.getHijo()[PosicionDelHijo].getValor()){
                if(valor == num){
                    conta++;
                }
            }
            if(conta == 0){
                 InsertarValor( valor,nodo_actual.getHijo()[PosicionDelHijo]);//Recursividad
            }
           

        }

    }
    private void OrdenarArray(int valor, Nodo nodo_actual){
         int posicion = nodo_actual.getContador(); //posicion es la cantidad de valores que tiene el nodo. 
        while (posicion >= 1 && valor < nodo_actual.getValor()[posicion - 1]){//buscar la posición de insercion
                    {
                        nodo_actual.getValor()[posicion] = nodo_actual.getValor()[posicion - 1];//desplaza los valores
                        posicion--;//desde posición maxima a minima
                    }

                    nodo_actual.getValor()[posicion] = valor;//Luego de ordenar se inserta el valor
                    nodo_actual.setContador(nodo_actual.getContador()+1); //incrementar el numero de valores
                }
        
    }
    
    private Nodo[] ObtenerHijos(int valor, Nodo nodo_actual){
        Nodo Hijos[] = new Nodo[2];
          Nodo ValoresMenor = new Nodo(grado,nodo_actual),ValoresIgualMayor = new Nodo(grado,nodo_actual); // se crean los hijos del nodo padre 
            int []array_aux = new int[this.grado];
            int conta=0,posicionAdividir;
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
            Hijos[0] = ValoresMenor;
            Hijos[1] = ValoresIgualMayor;
        return Hijos;
        
    }
    
    private void Dividir(int valor, Nodo nodo_actual){
        if(nodo_actual.getPadre()==null&&nodo_actual.esHoja()){ // esta condicion es cuando se realiza la primera division en un arbol b+, para que se trasforme en un arbol b+ de 2 niveles.
            
            int [] nuevoArrayPadre = new int[this.grado-1];
            Nodo []NuevosHijos = ObtenerHijos(valor, nodo_actual);// se obtienen los dos nodos, en los cuales esta integrado el valor a insertar, y que con en estos nodos uno tiene los valores menores al valor segun m/2 y otro los valore igual y mayor a este valor en dicha posicion
            Nodo ValoresMenor = NuevosHijos[0], ValoresIgualMayor = NuevosHijos[1];
            
            nodo_actual.setValor(nuevoArrayPadre);// el nodo padre en principio se deja sin hijos 
            nodo_actual.setContador(0);// el contador de valores se deja a cero 
            nodo_actual.setHoja(false);// ahora el nodo padre no sera una hoja 
            nodo_actual.getValor()[0] = valorAsubir; //se ingresa el valor a subir en la posicion cero. 
            nodo_actual.setContador(1);// le aumenta 1 al contador por el valor que acaba de insertar 
            //se ingresan en las al array de hijos los nodos que contiene los valores menores y mayores e igual, al valor que se subio. 
            nodo_actual.getHijo()[0]= ValoresMenor;
            nodo_actual.getHijo()[1] = ValoresIgualMayor;     
        }
        
        else if(nodo_actual.getPadre()!=null&&nodo_actual.esHoja()){
            //*** obtener nuevos nodos 
            Nodo []NuevosHijos = ObtenerHijos(valor, nodo_actual); // se obtienen los dos nuevos nodos. 
            Nodo ValoresMenor = NuevosHijos[0], ValoresIgualMayor = NuevosHijos[1];
            // *** Insertar El valor que debe subir de acuerdo a la formula grado/2 que es la posicion del valor que deberia subir al nodo padre.  
            Nodo padre = nodo_actual.getPadre(); // obtener el padre
            int posicion = padre.getContador();// obtener la ultima posicio disponible 
            padre.getValor()[posicion] = valorAsubir; // insertar el valor en el array del nodo padre 
            OrdenarArray(valorAsubir,padre);
            
            for ( int i = padre.getContador(); i > PosicionDelHijo; i--) { // desplazar los nodos hijos del padre hacia la derecha, para ingresar los nuevos dos nodos hijos, obtenidos de la division del nodo que es hoja y que el padre es distinto de nulo.
            padre.getHijo()[i + 1] = padre.getHijo()[i];        
        }
              
            
            padre.getHijo()[PosicionDelHijo] = ValoresMenor;
            padre.getHijo()[PosicionDelHijo+1] = ValoresIgualMayor;
            
            
        }  
        else if(nodo_actual.getPadre()==null&&!nodo_actual.esHoja()){
            
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
