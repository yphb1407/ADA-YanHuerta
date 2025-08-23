package com.gimnasio.model;
public class Clase {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String instructor;
    private String horario;
    private int duracionMinutos;
    private int capacidadMaxima;
    private String nivel;
    private String observaciones;
    public Clase(String codigo, String nombre, String descripcion, String instructor, 
                 String horario, int duracionMinutos, int capacidadMaxima, 
                 String nivel, String observaciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.horario = horario;
        this.duracionMinutos = duracionMinutos;
        this.capacidadMaxima = capacidadMaxima;
        this.nivel = nivel;
        this.observaciones = observaciones;
    }
    // Getters y Setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getInstructor() { return instructor; }
    public String getHorario() { return horario; }
    public int getDuracionMinutos() { return duracionMinutos; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public String getNivel() { return nivel; }
    public String getObservaciones() { return observaciones; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public void setCapacidadMaxima(int capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    // Para guardar en archivo
    @Override
    public String toString() {
        return codigo + "," + nombre + "," + descripcion + "," + instructor + "," +
               horario + "," + duracionMinutos + "," + capacidadMaxima + "," + nivel + "," + observaciones;
    }
    // Para cargar desde archivo
    public static Clase fromString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 9) {
            return new Clase(
                partes[0], 
                partes[1], 
                partes[2], 
                partes[3], 
                partes[4], 
                Integer.parseInt(partes[5]), 
                Integer.parseInt(partes[6]), 
                partes[7], 
                partes[8]
            );
        }
        return null;
    }
}