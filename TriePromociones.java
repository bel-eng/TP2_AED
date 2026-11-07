package aed.tp2;

public class TriePromociones {

    private static final int cant_letras_ascii = 256;

    private static class NodoTrie {
        Promocion promo;
        boolean esClave;
        NodoTrie[] hijos;
        int cantDeHijos;

        NodoTrie() {
            this.promo = null;
            this.esClave = false;
            this.hijos = new NodoTrie[cant_letras_ascii];
            this.cantDeHijos = 0;
        }
    }

    private NodoTrie raiz;
    private int tamaño;

    public TriePromociones() {
        this.raiz = new NodoTrie();
        this.tamaño = 0;
    }

    public boolean estaVacio() {
        return this.tamaño == 0;
    }

    //para buscar promocion por nombre -> complejidad en peor caso O(|L|)
    public Promocion obtener(String nombre) {
        if (nombre == null)
            return null;

        NodoTrie actual = this.raiz;
        for (int i = 0; i < nombre.length(); i += 1) {
            int n = (int) nombre.charAt(i);
            if (n < 0 || n >= cant_letras_ascii || actual.hijos[n] == null) {
                return null;
            }
            actual = actual.hijos[n];
        }
        if (actual.esClave) {
            return actual.promo;
        }
        return null;
    }

    // para verificar si una promocion existe en elsistema -> complejiddad den el peor caso O(|L|)
    public boolean pertenece(String nombre) {
        return obtener(nombre) != null;
    }


}
