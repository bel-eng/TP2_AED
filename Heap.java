package aed.tp2;

import java.util.Comparator;


public class Heap<T extends > {
    private ArrayList elems;  
    private HandleDeHeap<T>[] handles;
    private int cant;
    private static final int tamaño_inicial = 16;

    public int tamaño() {
        return this.cant;
    }

    public boolean estaVacio() {
        return this.cant == 0;
    }

    public T consultarMax() {
        if (estaVacio())
            return null;
        return this.elems[0];
    }

    public Handle<T> agregar( T valor) {
        int indice = this.cant;
        HandleDeHeap<T> h_valor = new HandleDeHeap<T>(this, indice);
        this.elems[indice] = valor;
        this.handles[indice]= h_valor;

        
        this.cant++;
        siftUp(indice);
        return h_valor;

    }

    public T desencolar(){
        if (this.cant == 0) return null;
        T maximo = this.elems[0]; /* guarda la raiz en la variable maximo */
        eliminarEnHeap(0);    /*elimina la raiz, el elemento con mayor prioridad */
        return maximo;          
    }
    /*saca al usuario , guarda la direccion donde estaba en el heap */
    public Handle<T> desencolarHandle(){
        if (this.cant == 0) return null;
        Handle<T> handleMaximo = this.handles[0]; /*guarda/copia la direccion que apunta al handle asociado  al usuario que ocupa la raiz del heap en este momento, antes que remueva la raiz y reordene el heap*/
        eliminarEnHeap(0); 
        return handleMaximo;

    }

    /*borra el elemento que esta en la posicion i */
    public void eliminarEnHeap(int i){   
        if (i< 0 || i >= this.cant) return;
        swap(i, this.cant - 1);
        this.cant--;
        this.elems[this.cant] = null;
        this.handles[this.cant] = null;

        if (i < this.cant) {
            siftUp(i);
            siftDown(i);
        }
    }

    public void modificarEnHeap(int i, T nuevoValor){
        if (i<0 || i >= this.cant) return;
        this.elems[i] = nuevoValor;
        siftUp(i);
        siftDown(i);
    }


    private void swap(int i, int j){ /*intercambia la posicion de los elementos de los indices i, j */
        T guardavalor = this.elems[i]; 
        this.elems[i] = this.elems[j];
        this.elems[j] = guardavalor;


        HandleDeHeap<T> guardaHandle = this.handles[i]; /*al intercambiar de posicion, tambien se tiene que intercambiar de posicion los punteros de los handles en el array de handles* */
        this.handles[i] = this.handles[j];
        this.handles[j] = guardaHandle;

        if (this.handles[i] != null) this.handles[i].setPosicion(i); /*como cambiaron de lugar los elementos, tenemos que cambiar las posiciones en los handles tambien */
        if (this.handles[j] != null) this.handles[j].setPosicion(j);
    }
    /*evalua, se compara con el padre , si es mayor, swapea y actualiza las posiciones */
    private void siftUp(int i) {
        int padre = (i - 1) / 2;
        while (i > 0 && this.elems[i].compareTo(this.elems[padre]) > 0) {
            swap(i, padre);
            i = padre;
            padre = (i - 1) / 2;
        }
    }
    /*evalua, se compara con los hijos, si es menor que alguno , actualiza su posicion  */
    private void siftDown(int i) {
        int izq = 2 * i + 1;
        int der = 2 * i + 2;
        int mayor = i;
    /*en mayor va a guardar al que tenga posicion mayor, si el izq es mayor,  guarda el indice en mayor */
        if (izq < this.cant && this.elems[izq].compareTo(this.elems[mayor]) > 0) {
            mayor = izq;  /*si el valor en indice del hijo izq supero al valor del padre, mayor cambia y guarda el indice del izq */
        }
    /* se fija si el hijo derecho es mayor al elemento que esta guardado en mayor */    
        if (der < this.cant && this.elems[der].compareTo(this.elems[mayor]) > 0) {
            mayor = der;  /* guarda al indice del hijo derecho si es el mayor elemento */
        }
        /*si mi indice inicial i no tiene el mayor valor habiendo comparado con los hijos,swapea de posicion  */
        if (mayor != i) {
            swap(i, mayor); /* cuando swapea, el contenido de mayor pasa a la posicion de i, y el contenido de i pasa a la de mayor  */
            siftDown(mayor);/*ahora en mayor , esta un elemento mas chico, que se va a comparar con los hijos nuevos que tiene al bajar un nivel  */
        }
    }
}

    /* }

    public class PorMillasDisponibles implements Comparator<Usuario>{

        @Override
        public int compare(Usuario a, Usuario b){
            return Integer.compare(b.millasDisponibles, a.millasDisponibles);
        }
    }

    PriorityQueue<Usuario> heap = new PriorityQueue<>(new PorMillasDisponibles()); */
