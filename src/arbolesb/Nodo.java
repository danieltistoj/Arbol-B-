/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesb;

/**
 *
 * @author Usuario
 */
public class Nodo {
        private int grado; //grado 
        private int contador; // numero de valores en el nodo
        private int valor[];  // arreglo de claves 
        private Nodo hijo[]; //arreglo de hijos
        private boolean hoja; //conocer si el nodo es hoja o no
        private Nodo padre, siguiente;  //padre del nodo actual

        
        public Nodo(int grado, Nodo padre) {
            this.grado = grado;  //asigna tamaño o grado
            this.padre = padre; //asignación de padre
            valor = new int[grado-1];  //asignar tamaño 2t
            hijo = new Nodo [grado]; // asginar hijos
            hoja = true; //todos los nodos son hojas al iniciar
            contador = 0; //contador inicial en cero
        }

        public int getValor(int index) {//obtiene un valor del nodo
            
            return valor[index];
        }

        
        public Nodo getHijo(int index) { //obtiene un hijo del nodo
            return hijo[index];
        }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int[] getValor() {
        return valor;
    }

    public void setValor(int[] valor) {
        this.valor = valor;
    }

    public Nodo[] getHijo() {
        return hijo;
    }

    public void setHijo(Nodo[] hijo) {
        this.hijo = hijo;
    }

    public boolean esHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
        
    }
    

