package aed.tp2;

public class Promocion {
    private String nombre;
    private double factor;
    private boolean tieneTope;
    private double tope;
    private double millasTotales;

    //constructor para promociones SIN tope
    public Promocion(String nombre, double factor) {
        this.nombre = nombre;
        this.factor = factor;
        this.tieneTope = false;
        this.tope = 0.0;
        this.millasTotales = 0.0;
    }

    //constructor para promociones CON topr
    public Promocion(String nombre, double factor, double tope) {
        this(nombre, factor);
        this.tieneTope = true;
        this.tope = tope;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getFactor() {
        return this.factor;
    }

    public boolean tieneTope() {
        return this.tieneTope;
    }

    public double getTope() {
        return this.tope;
    }

    public double getMillasTotales() {
        return this.millasTotales;
    }

    //para sumar la cantidad de millas por promocion (complejidad O(1))
    public void sumaMillasTotales(double cant) {
        this.millasTotales += cant;
    }

    //para checkear que la promocion alcanzo tope (complejidad(1))
    public boolean alcanzaTope() {
        return this.tieneTope && this.millasTotales >= this.tope;
    }
}