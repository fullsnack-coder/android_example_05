package com.tarea05.myapplication.modelos;

public class Cliente {
    String dni, apellidos, nombres, linea_credito;

    public Cliente() {
        this.dni = "";
        this.nombres = "";
        this.apellidos = "";
        this.linea_credito = "";
    }

    public Cliente(
            String dni, String nombres, String apellidos, String linea_credito
    ) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.linea_credito = linea_credito;
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
}
