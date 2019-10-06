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
    private int altura =0, PosicionDelHijo, valorAsubir, contador;

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
    private Nodo OrdenarArray(int valor, Nodo nodo_actual){
         int posicion = nodo_actual.getContador(); //posicion es la cantidad de valores que tiene el nodo. 
        
        while (posicion >= 1 && valor < nodo_actual.getValor()[posicion - 1])//buscar la posición de insercion
                    {
                        
                        nodo_actual.getValor()[posicion] = nodo_actual.getValor()[posicion - 1];//desplaza los valores
                        posicion--;//desde posición maxima a minima
                    }

                    nodo_actual.getValor()[posicion] = valor;//Luego de ordenar se inserta el valor
                    nodo_actual.setContador(nodo_actual.getContador()+1); //incrementar el numero de valores
                
        return nodo_actual;
        
    }
    /*se obtienen los hijos o los nodos de una division y si el nodo que se va a dividir tiene un padre los nodos
      que resulten de la division tendran este mismo padre. 
      Si el nodo a dividir no tiene padre y es una hoja los nodos que resulten de la division tendran el nodo que se dividion.*/
    private Nodo[] ObtenerHijos(int valor, Nodo nodo_actual){
          Nodo Hijos[] = new Nodo[2]; // creamos este array de nodos para que los nodos resultantes de la division puedan ser enviados donde se llamo la funcion 
          Nodo ValoresMenor, ValoresIgualMayor; 
          //Se inicializan los nodos nuevos nodos y se les asigna un padre segun las condiciones.
          if(nodo_actual.getPadre()!=null){ // si el nodo tiene un padre los nuevos nodos tendran que tener el mismo padre
             ValoresMenor = new Nodo(grado,nodo_actual.getPadre());
             ValoresIgualMayor = new Nodo(grado,nodo_actual.getPadre()); // se crean los hijos del nodo padre   
          }
          else{
             ValoresMenor = new Nodo(grado,nodo_actual);
             ValoresIgualMayor = new Nodo(grado,nodo_actual); // se crean los hijos del nodo padre   
          }
           
           
            int []array_aux = new int[this.grado];//Se crea un array con una posicion mas que los array que contienen los nodos para los valores. 
            int conta=0,posicionAdividir;//Se crea una variable que obtendra la posicion del valor que debe de subir.
            for(int num:nodo_actual.getValor()){//pasamos todos los valores del nodo que queremos dividir al array auxiliar 
                array_aux[conta] = num;
                conta++;
            }
            array_aux[this.grado-1] = valor;//ingresamos en la ultima posicion el nuevo valor. 
            Arrays.sort(array_aux);// se ordena el array auxiliar. 
            
            posicionAdividir = (this.grado/2); // se encuentra la posicion del valor que tiene que subir, la posicion del valor que ahora pasara a estar en el arreglo del nodo padre.
            valorAsubir = array_aux[posicionAdividir];// se encuentra el valor que debe se subir o ser insertado en el array del nodo padre, este valor no es necesariamente el nuevo valor nuevo. 
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
            
            int [] nuevoArrayPadre = new int[this.grado-1];// se crea un array nuevo, esto con el fin de igualarlo al nodo_actual para que se le borren todos los valores que tenia.
            Nodo []NuevosHijos = ObtenerHijos(valor, nodo_actual);// se obtienen los dos nodos, en los cuales puede estar integrado el valor a insertar, y que con en estos nodos uno tiene los valores menores al valor segun m/2 y otro los valore igual y mayor a este valor en dicha posicion
            Nodo ValoresMenor = NuevosHijos[0], ValoresIgualMayor = NuevosHijos[1]; // se asiganan los nodos de resultantes de la division en variables tipo nodo
            
            nodo_actual.setValor(nuevoArrayPadre);// el nodo padre en principio se deja sin hijos, con esto es como si el padre ubiera sino inicializado sin hijos
            nodo_actual.setContador(0);// el contador de valores se deja a cero 
            nodo_actual.setHoja(false);// ahora el nodo padre no sera una hoja 
            nodo_actual.getValor()[0] = valorAsubir; //se ingresa el valor a subir en la posicion cero. 
            nodo_actual.setContador(1);// le aumenta 1 al contador por el valor que acaba de insertar 
            //se ingresan en las al array de hijos los nodos que contiene los valores menores y mayores e igual, al valor que se subio. 
            nodo_actual.getHijo()[0]= ValoresMenor;
            nodo_actual.getHijo()[1] = ValoresIgualMayor;     
        }
        
        else if(nodo_actual.getPadre()!=null&&nodo_actual.esHoja()){// esta division es cuando el nodo a dividir tiene como padre un valor distinto de nulo pero el es una hoja, osea no tiene hijos.
            Nodo padre = nodo_actual.getPadre(); // obtener el padre
            if(padre.getPadre() == null){
            //*** obtener nuevos nodos 
            
            Nodo []NuevosHijos = ObtenerHijos(valor, nodo_actual); // se obtienen los dos nuevos nodos. 
            Nodo ValoresMenor = NuevosHijos[0], ValoresIgualMayor = NuevosHijos[1];
          
            
            // *** Insertar El valor que debe subir de acuerdo a la formula grado/2 que es la posicion del valor que deberia subir al nodo padre.  
            
            int posicion = padre.getContador();// obtener la ultima posicio disponible 
            //padre.getValor()[posicion] = valorAsubir; // insertar el valor en el array del nodo padre 
            padre = OrdenarArray(valorAsubir,padre);// se insertar el valor a subir en la posicion corespondiente. 
           
            for ( int i = padre.getContador()-1; i > PosicionDelHijo; i--) { // desplazar los nodos hijos del padre hacia la derecha, para ingresar los nuevos dos nodos hijos, obtenidos de la division del nodo que es hoja y que el padre es distinto de nulo.
            padre.getHijo()[i + 1] = padre.getHijo()[i];        
        }
              
            //*** Se ingresan los nuevos valores en el array de hijos del nodo padre. 
            
            padre.getHijo()[PosicionDelHijo] = ValoresMenor;
            padre.getHijo()[PosicionDelHijo+1] = ValoresIgualMayor;
            //*** Lo que hace el siguinete codigo es une un hijo con otro con apuntadores. 
            //nodo[0]--->nodo[1]--->nodo[2]--->null
            int conta=0, conta2=0;
            for(Nodo nodo: padre.getHijo()){
                if(nodo!=null){
                    conta++;
                }
            }
            
            while(conta2<conta-1){
                padre.getHijo()[conta2].setSiguiente(padre.getHijo()[conta2+1]);
                conta2++;
            }
            
            }//fin de if
        }  
        else if(nodo_actual.getPadre()==null&&!nodo_actual.esHoja()){
            
        }
          
    }
    
    public void Insertar(int valor, Nodo nodo_actual){
      
        if(nodo_actual.getContador() != grado - 1){
            
            InsertarValor(valor,nodo_actual);
        }
        else if((nodo_actual.getContador() == grado - 1)&&!nodo_actual.esHoja()){ //si el nodo actual esta lleno pero no es hoja se manda a la funcion que inserta valores 
             InsertarValor(valor,nodo_actual);
        }
        else{
            Dividir(valor, nodo_actual);
            
        }
        
    }
    private void BuscarValor(int valor, Nodo nodo_actual){
        if(nodo_actual!=null){
            for(int num : nodo_actual.getValor()){
                if(num == valor){
                    contador++;
                }
            }
            
            BuscarValor(valor, nodo_actual.getSiguiente());
        }
        
    }
    public boolean Buscar(int valor, Nodo nodo_actual){
        contador=0;
        boolean existe;
        if(nodo_actual.esHoja()){
            BuscarValor(valor, nodo_actual);
            if(contador>0){
                existe = true;
            }
            else{
                existe = false;
            }
            
        }
        else{
          existe =  Buscar(valor, nodo_actual.getHijo()[0]);
        }
        return existe;
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
