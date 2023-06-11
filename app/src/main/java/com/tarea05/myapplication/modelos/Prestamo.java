package com.tarea05.myapplication.modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Prestamo implements Serializable {
    private int numero, monto_pedido, saldo;
    private String estado;
    private ArrayList<Pago> pagos;

    public Prestamo() {
        this.monto_pedido = 0;
        this.saldo = 0;
        this.estado = "Aun no registrado";
        this.pagos = new ArrayList<Pago>();
    }

    public Prestamo(int numero) {
        this.numero = numero;
        this.monto_pedido = 0;
        this.saldo = 0;
        this.estado = "Aun no registrado";
        this.pagos = new ArrayList<Pago>();
    }

    public Prestamo(int numero, int monto_pedido, int saldo, String estado) {
        this.numero = numero;
        this.monto_pedido = monto_pedido;
        this.saldo = saldo;
        this.estado = estado;
        this.pagos = new ArrayList<Pago>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMonto_pedido() {
        return monto_pedido;
    }

    public void setMonto_pedido(int monto_pedido) {
        if (this.monto_pedido == 0) {
            this.monto_pedido = monto_pedido;
            this.saldo = monto_pedido;
        } else {
            this.monto_pedido = monto_pedido;
        }
        this.estado = "Registrado";
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
        if (this.saldo <= 0) {
            setEstado("Cancelado");
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int addPago(Pago nuevoPago) {
        this.pagos.add(nuevoPago);
        this.setSaldo(this.saldo - nuevoPago.getMonto());

        return this.pagos.size();
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }
}
