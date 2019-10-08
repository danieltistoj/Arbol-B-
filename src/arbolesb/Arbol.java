/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesb;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Usuario
 */
public class Arbol {
    private Nodo raiz;
    private int grado;
    private int altura =0, PosicionDelHijo, valorAsubir, contador;
    private boolean VolverAinsertar;
    private String cadena = "";
  
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
                /*
                    if(nodo_actual.getPadre()!=null){
                        for(int num:nodo_actual.getPadre().getValor()){
                            System.out.print(num+", ");
                        }
                        System.out.println("");
                        for(int num:nodo_actual.getValor()){
                            System.out.print(num+", ");
                        }
                        System.out.println("");
                    }
                */
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
                //System.out.println(valor);
                VolverAinsertar = false;
            }
            else{
                VolverAinsertar = true;
            }
            if(VolverAinsertar){
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
        int conta=0,posicionAdividir;//Se crea una variable que obtendra la posicion del valor que debe de subir.
        posicionAdividir = ((this.grado-1)/2); // se encuentra la posicion del valor que tiene que subir, la posicion del valor que ahora pasara a estar en el arreglo del nodo padre.
        
        if(nodo_actual.esHoja()){ // si el nodo es una hoja 
           
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
            
            for(int num:nodo_actual.getValor()){//pasamos todos los valores del nodo que queremos dividir al array auxiliar 
                array_aux[conta] = num;
                conta++;
            }
            array_aux[this.grado-1] = valor;//ingresamos en la ultima posicion el nuevo valor. 
            Arrays.sort(array_aux);// se ordena el array auxiliar. 
           
            valorAsubir = array_aux[posicionAdividir];// se encuentra el valor que debe se subir o ser insertado en el array del nodo padre, este valor no es necesariamente el nuevo valor nuevo. 
            for(conta=0; conta<posicionAdividir ;conta++){ // con este ciclo insertamos los valores en el nodo o hijo que tendra los valores menores al numero que va a subir, que es el numero que tenga la posicion m/2. 
                InsertarValor(array_aux[conta],ValoresMenor);
            }
            for(conta = posicionAdividir; conta<grado; conta++){// con este ciclo insertamos los valores en el nodo o hijo que tendra los valores iguales o mayores a el numero que va a subir y que estara en el nodo padre. 
                InsertarValor(array_aux[conta],ValoresIgualMayor);
            }
           
        }
        else{
           
            ValoresMenor = new Nodo(grado,nodo_actual);
            ValoresIgualMayor = new Nodo(grado,nodo_actual); // se crean los hijos del nodo padre
            valorAsubir = nodo_actual.getValor(posicionAdividir);
           //pasar los valores a los nuevos nodos 
           int i = 0;
           for( i=0; i<posicionAdividir;i++){
               OrdenarArray(nodo_actual.getValor()[i], ValoresMenor);
               ValoresMenor.setContador(ValoresMenor.getContador()+1);
           }
           int j=0;
           for( i=posicionAdividir+1; i<grado-1;i++){
               ValoresIgualMayor.getValor()[j] = nodo_actual.getValor()[i];
               ValoresIgualMayor.setContador(ValoresIgualMayor.getContador()+1);
               j++;
           }
           //pasar los hijos a cada nuevo nodo 
           for(i=0;i<=posicionAdividir;i++){
               ValoresMenor.getHijo()[i] = nodo_actual.getHijo()[i];
               ValoresMenor.getHijo()[i].setPadre(ValoresMenor);
               
           }
           j=0;
           
           for(i=posicionAdividir+1;i<grado;i++){
               
               ValoresIgualMayor.getHijo()[j] = nodo_actual.getHijo()[i];
               ValoresIgualMayor.getHijo()[j].setPadre(ValoresIgualMayor);
               j++;
           }
          
           ValoresIgualMayor.setHoja(false);
           ValoresMenor.setHoja(false);
         
        }
         Hijos[0] = ValoresMenor;
         Hijos[1] = ValoresIgualMayor;
         
        return Hijos;
        
    }
    
    private void Dividir(int valor, Nodo nodo_actual){
        if(nodo_actual.getPadre()==null){ // esta condicion es cuando se realiza la primera division en un arbol b+, para que se trasforme en un arbol b+ de 2 niveles.
            Nodo nodo[] =new Nodo[grado];
            int [] nuevoArrayPadre = new int[this.grado-1];// se crea un array nuevo, esto con el fin de igualarlo al nodo_actual para que se le borren todos los valores que tenia.
            Nodo []NuevosHijos = ObtenerHijos(valor, nodo_actual);// se obtienen los dos nodos, en los cuales puede estar integrado el valor a insertar, y que con en estos nodos uno tiene los valores menores al valor segun m/2 y otro los valore igual y mayor a este valor en dicha posicion
            Nodo ValoresMenor = NuevosHijos[0], ValoresIgualMayor = NuevosHijos[1]; // se asiganan los nodos de resultantes de la division en variables tipo nodo
            
            nodo_actual.setValor(nuevoArrayPadre);// el nodo padre en principio se deja sin hijos, con esto es como si el padre ubiera sino inicializado sin hijos
            nodo_actual.setHijo(nodo);// se deja el array de nodos como uno nuevo 
            nodo_actual.setContador(0);// el contador de valores se deja a cero 
            nodo_actual.setHoja(false);// ahora el nodo padre no sera una hoja 
           
            nodo_actual.getValor()[0] = valorAsubir; //se ingresa el valor a subir en la posicion cero. 
            
            nodo_actual.setContador(1);// le aumenta 1 al contador por el valor que acaba de insertar 
            //se ingresan en las al array de hijos los nodos que contiene los valores menores y mayores e igual, al valor que se subio. 
            nodo_actual.getHijo()[0]= ValoresMenor;
            nodo_actual.getHijo()[1] = ValoresIgualMayor;
            Enlazar(nodo_actual);// se enlazan todos los nodos de cada nivel
        }
        
        else if(nodo_actual.getPadre()!=null){// esta division es cuando el nodo a dividir tiene como padre un valor distinto de nulo pero el es una hoja, osea no tiene hijos.
            Nodo padre = nodo_actual.getPadre(); // obtener el padre
            
            if(padre.getContador()!=grado-1){// esto es si el padre aun tiene espacio para ingresar valores en el array 
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
            Enlazar(padre);//se enlazan los nodos de cada nivel
            
            }//fin de if
            else{// esta condicion es cuando en el nodo padre ya no tiene espacio para mas valores 
              
                Dividir(valor, padre);
                Insertar(valor,padre);
                Enlazar(padre);// se enlazan los nodos de cada nivel
                
                
            }
        }  
          
    }
    
    public void Insertar(int valor, Nodo nodo_actual){
      
        if(nodo_actual.getContador() != grado - 1){
            
            InsertarValor(valor,nodo_actual);
        }
        else if((nodo_actual.getContador() == grado - 1)&&nodo_actual.esHoja()==false){ //si el nodo actual esta lleno pero no es hoja se manda a la funcion que inserta valores 
           
            InsertarValor(valor,nodo_actual);
        }
        else{
          
            Dividir(valor, nodo_actual);
            
        }
        
    }
    //buscar un valor 
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
    //enlazar cada nodo 
    private void EnlazarNodos(int conta, ArrayList<Nodo> ListaNodos){
        ArrayList <Nodo> array_aux = new ArrayList<Nodo>();
        Nodo array_aux2[] = new Nodo[ListaNodos.size()];
        //pasar los nodos del nivel siguiente al array list
        if(conta<altura){
            for(Nodo nodo:ListaNodos){
                for(Nodo nodoHijo: nodo.getHijo()){
                    if(nodoHijo!=null){
                        array_aux.add(nodoHijo);
                    }
                }
            }
        
        //enlaar los nodos que tiene ListaNodos 
        int i =0;
        for(Nodo nodo2:ListaNodos){
             array_aux2[i] = nodo2;   
             i++;
        }
        
        for(i=0;i<ListaNodos.size()-1;i++){
            array_aux2[i].setSiguiente(array_aux2[i+1]);
        }
        
        conta++;
        EnlazarNodos(conta, array_aux);
        }
        
    }
    
   public void Enlazar(Nodo nodo_actual){
       altura = Altura(nodo_actual);
       ArrayList<Nodo> ListaNodos = new ArrayList<Nodo>();
       ListaNodos.add(nodo_actual);
       EnlazarNodos(0,ListaNodos);
   }
   //retornar altura del arbol
    public int Altura(Nodo nodo){
       int conta = 0;
        while(nodo!=null){
           nodo = nodo.getHijo()[0];
            conta++;
            
        }
        return conta;
    }
    //mostrar 
    private void MostrarDatos(int conta,Nodo nodo_actual){
        Nodo nodo_aux = nodo_actual;
        if(conta<altura){
            cadena += "Nivel "+(conta+1)+"\n";
            
            while(nodo_actual!=null){
               cadena+="[ ";
                for(int num: nodo_actual.getValor()){
                    cadena+=num+", ";
                }
                if(nodo_actual.getSiguiente() == null){
                    cadena+="] --> null";
                }
                else{
                    cadena+="] --> ";
                }
                nodo_actual = nodo_actual.getSiguiente(); 
            }
            conta++;
            cadena+="\n";
            MostrarDatos(conta,nodo_aux.getHijo()[0]);  
        }
       
        
        
    }

     public void Mostrar(Nodo nodo_actual){
        cadena = "";
        altura = Altura(nodo_actual);
        MostrarDatos(0,nodo_actual);
    }
     
     //fin de mostrar 
     
     //Retornar nivel del nodo
     public int RetornarNivelNodo(int valor,Nodo nodo_actual){
         boolean existe_aux;
         altura = Altura(nodo_actual);
         existe_aux = Buscar(valor, nodo_actual);
         if(existe_aux){
             return altura;
         }
         else{
             return 0;
         }
     }
     //Eliminar 
     private Nodo ObtenerUltimoNivel(Nodo nodo_actual){
         int conta=0;
         altura = Altura(nodo_actual);
         while(conta<(altura-1)){
             nodo_actual = nodo_actual.getHijo()[0];
             conta++;
         }
         return nodo_actual;
         
     }
     public String Eliminar(int valor, Nodo nodo_actual){
         //debe verce si el valor existe 
         if(Buscar(valor, nodo_actual)){
             Nodo nodo_aux;
              nodo_aux = ObtenerUltimoNivel(nodo_actual);// se obtiene el valor mas a la izquierda del ultimo nivel ;
             
             int conta=0;
             while(conta == 0){ // se busca que nodo en el ultimo nivel contiene el valor 
                 for(int num: nodo_aux.getValor()){
                     if(num == valor){
                         conta++;
                     }
                 }
                 if(conta == 0){
                      nodo_aux = nodo_aux.getSiguiente();
                 }
                 
             }
             //ya encontrado el nodo se ve si tiene los valores minimos para borrar. 
             if(nodo_aux.getContador()>(grado/2)){
                 ArrayList<Integer> array_aux = new ArrayList<Integer>();
                 for(int num:nodo_aux.getValor()){
                     if(num!=valor&&num!=0){
                         array_aux.add(num);
                     }
                 }
             
                 int aux[] = new int[grado-1];
                 nodo_aux.setValor(aux);//se deja el array de valores a cero
                 nodo_aux.setContador(0);
                 for(int num: array_aux){
                     OrdenarArray(num, nodo_aux);
                 }
                 cadena = "El valor se elimino correctamente";
                 
             }
             else{
                cadena = "El valor no se puede eliminaar porque el nodo debe de tener mas valores a la cantidad minima\n"+
                        "Cantidad de valores > (grado/2)";
             }
         }
         else{
             cadena = "El valor no existes";
         }
         return cadena;
     }
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
    public int NumeroDeHijos(Nodo nodo){
        int numHijos = 0;
        for(Nodo hijo : nodo.getHijo()){
            if(hijo!=null){
                numHijos++;
            }
        }
        return numHijos;
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
