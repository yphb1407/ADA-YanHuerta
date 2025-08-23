package com.gimnasio.model;
public class TipoMembresia {
    private String codigo;
    private String nombre;
    private double precio;
    private int duracionDias;
    public TipoMembresia(String codigo, String nombre, double precio, int duracionDias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.duracionDias = duracionDias;
    }
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getDuracionDias() { return duracionDias; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }
    // Para guardar en archivo
    @Override
    public String toString() {
        return codigo + "," + nombre + "," + precio + "," + duracionDias;
    }
    // Para cargar desde archivo
    public static TipoMembresia fromString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 4) {
            return new TipoMembresia(
                partes[0], 
                partes[1], 
                Double.parseDouble(partes[2]), 
                Integer.parseInt(partes[3])
            );
        }
        return null;
    }
}