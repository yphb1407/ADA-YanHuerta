package com.gimnasio.model;
public class Miembro {
    private String dni;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento; // Formato YYYY-MM-DD
    private String direccion;
    private String telefono;
    private String correo;
    private TipoMembresia tipoMembresia; // Objeto de tipo TipoMembresia
    private String fechaInicio; // Formato YYYY-MM-DD
    private String fechaVencimiento; // Formato YYYY-MM-DD
    private String estado; // Activo, Inactivo
    public Miembro(String dni, String nombres, String apellidos, String fechaNacimiento, 
                   String direccion, String telefono, String correo, 
                   TipoMembresia tipoMembresia, String fechaInicio, 
                   String fechaVencimiento, String estado) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoMembresia = tipoMembresia;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }
    // Getters
    public String getDni() { return dni; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public TipoMembresia getTipoMembresia() { return tipoMembresia; }
    public String getFechaInicio() { return fechaInicio; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public String getEstado() { return estado; }
    // Setters
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setTipoMembresia(TipoMembresia tipoMembresia) { this.tipoMembresia = tipoMembresia; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public void setEstado(String estado) { this.estado = estado; }
    // Para guardar en archivo
    @Override
    public String toString() {
        return String.join(",",
                dni,
                nombres,
                apellidos,
                fechaNacimiento,
                direccion,
                telefono,
                correo,
                tipoMembresia.getCodigo(), // Guardar solo el código de la membresía
                fechaInicio,
                fechaVencimiento,
                estado
        );
    }
    // Para cargar desde archivo
    public static Miembro fromString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 11) {
            return new Miembro(
                    partes[0], // dni
                    partes[1], // nombres
                    partes[2], // apellidos
                    partes[3], // fechaNacimiento
                    partes[4], // direccion
                    partes[5], // telefono
                    partes[6], // correo
                    new TipoMembresia(partes[7], "", 0, 0), // Crear un objeto TipoMembresia temporal
                    partes[8], // fechaInicio
                    partes[9], // fechaVencimiento
                    partes[10] // estado
            );
        }
        return null;
    }
}