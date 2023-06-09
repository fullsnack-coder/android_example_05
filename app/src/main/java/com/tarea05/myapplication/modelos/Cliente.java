package com.tarea05.myapplication.modelos;

public class Cliente {
    String dni, apellidos, nombres, linea_credito;
    Prestamo prestamo1, prestamo2, prestamo3;

    public Cliente() {
        this.dni = "";
        this.nombres = "";
        this.apellidos = "";
        this.linea_credito = "";
        this.prestamo1 = new Prestamo();
        this.prestamo2 = new Prestamo();
        this.prestamo3 = new Prestamo();
    }

    public Cliente(
            String dni, String nombres, String apellidos, String linea_credito
    ) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.linea_credito = linea_credito;
        this.prestamo1 = new Prestamo();
        this.prestamo2 = new Prestamo();
        this.prestamo3 = new Prestamo();
    }

    public String getDni() {
        return dni;
    }

    public Cliente setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Cliente setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public String getNombres() {
        return nombres;
    }

    public Cliente setNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public String getLinea_credito() {
        return linea_credito;
    }

    public Cliente setLinea_credito(String linea_credito) {
        this.linea_credito = linea_credito;
        return this;
    }

    public Prestamo getPrestamo1() {
        return prestamo1;
    }

    public void setPrestamo1(Prestamo prestamo1) {
        this.prestamo1 = prestamo1;
    }

    public Prestamo getPrestamo2() {
        return prestamo2;
    }

    public void setPrestamo2(Prestamo prestamo2) {
        this.prestamo2 = prestamo2;
    }

    public Prestamo getPrestamo3() {
        return prestamo3;
    }

    public void setPrestamo3(Prestamo prestamo3) {
        this.prestamo3 = prestamo3;
    }
}
