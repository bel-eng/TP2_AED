package aed.tp2;
public class ABB<K extends Comparable<K> , V> {
// el ABB tiene a K y V porque lo que se va a guardar es un diccionario

Nodo raiz;

int cantidad;

private  class Nodo { // tiene que estar privado para evitar modificaciones en las caracteristicas de mi clase
 K clave;
 V valor;
 Nodo padre;
 Nodo izquierda;
 Nodo derecha;

 public Nodo (K clave , V valor){

    this.clave = clave;
    this.valor = valor;
    this.padre = null;
    this.izquierda = null;
    this.derecha = null;
 }


}
public ABB (){
    this.raiz = null;
    this.cantidad= 0;

}


public boolean  pertenece_usuario (K clave) {
    Nodo actual = this.raiz;

    while (actual != null) {
       if (actual.clave.compareTo(clave) > 0) {// significa que actual.dato es mayor que elem
          actual = actual.izquierda;
       } 
       else if (actual.clave.compareTo(clave)<0){
        actual = actual.derecha;
       }
       else {
        return true;
       }
    }

    return false;
}

public int cantidad_de_usuarios_en_sistema (){
    return (this.cantidad);
}
public void agregar_nuevo_usuario (K clave, V valor){

    Nodo nuevo_usuario = new Nodo (clave , valor); // defino el nodo que va a ingresar.
    Nodo actual = this.raiz;
    Nodo padre = null;
    
    if (! pertenece_usuario (clave)){
    if (this.raiz == null){
        this.raiz = nuevo_usuario;
    }
    else{
          // tengo que buscar el nodo padre del que va a ser mi nuevo usuario
          while (actual!=null) { // cuando actual sea igual a null significa que el nodo que esta por arriva de ese null es el padre del nuevo usuario a agregar 
             padre = actual;
             if (actual.clave.compareTo(clave)>0) {
               actual = actual.izquierda; 
             } 
             else { // no va a ocurrir el caso de = 0 porque ya me asegure de que la K no pertnecesca 
                 actual = actual.derecha;
             }
          }

          if (padre.clave.compareTo(clave)>0) {
            padre.izquierda = nuevo_usuario;
            nuevo_usuario.padre = padre;
          }
          else{
            padre.derecha = nuevo_usuario;
            nuevo_usuario.padre = padre;
          }
     }
     this.cantidad = this.cantidad+1;

   }
 }
 public V obtener (K clave){
    Nodo actual = this.raiz;

    while (actual !=null) {
        if (actual.clave.compareTo(clave)>0) {
           actual = actual.izquierda;            
        }
        else if (actual.clave.compareTo(clave)<0){
            actual = actual.derecha;
        }
        else{
            return actual.valor;
        }
    
     }
    return (null);
   }
   public Iterador<V> iterar_arbol (){ // instancio- creo el iterado dentro de esta clase 
      return new  ABB_iterador() ; 
   }

   public class ABB_iterador implements  Iterador<V>{
    private Nodo actual;

    public ABB_iterador(){
        this.actual = raiz;
        if (this.actual !=null) {
        while (this.actual.izquierda!= null) {
          this.actual = this.actual.izquierda; // comienza actual valiendo siempre el nodo mas a la izquierda
        }
      }

   }

   @Override 
   public boolean haySiguiente(){
    return this.actual !=null; // si es igual a null significa que no tiene siguientes
   }
  @Override 
  public V siguiente(){
    V res = this.actual.valor;
    if (this.actual.derecha != null) {
         this.actual = this.actual.derecha;
         while (this.actual.izquierda!=null){
            this.actual = this.actual.izquierda;
         }        
    }
    else{
        while (this.actual.padre !=null && this.actual.padre.derecha == this.actual ){
         this.actual = this.actual.padre;

        }
        this.actual = this.actual.padre;
    }
    return res; 
  }
  @Override
    public boolean hayAnterior() {
        return false;
    }

    @Override
    public V anterior() {
        return null;
    }


 }




}