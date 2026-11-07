package aed.tp2;

public class HandleDeHeap <T> implements Handle<T>{
    private Heap<T> heap;
    private int pos;

    public HandleDeHeap(Heap<T> heap, int pos) {
        this.heap = heap;
        this.pos = pos;
    }

    public int getPosicion() {
        return this.pos;
    }

    public void setPosicion(int nueva_pos) {
        this.pos = nueva_pos;
    }

    @Override
    public T valorEnHeap() {
        return this.heap.obtener(this.pos);
    }

    @Override
    public void eliminar(T valor) {
        this.heap.eliminarEnHeap(this.pos);
    }

    @Override
    public void modificar(T nuevoValor) {
        this.heap.modificarEnHeap(this.pos, nuevoValor);
    }

    @Override
    public boolean equals(Handle<T> otro) {
        if (this == otro) 
            return true;
        if (otro == null)
            return false;
        T thisValor = this.valor();
        T otroValor = otro.valor();
        if (thisValor == null)
            return otroValor == null;
        return thisValor.equals(otroValor);
    }
}
