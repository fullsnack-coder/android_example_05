package com.tarea05.myapplication.modelos;

import java.io.Serializable;

public class Pago implements Serializable {
    private int monto;
    private String fecha;

    public Pago() {
        this.monto = 0;
        this.fecha = "";
    }

    public Pago(int monto, String fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
