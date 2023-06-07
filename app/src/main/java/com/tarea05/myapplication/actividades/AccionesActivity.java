package com.tarea05.myapplication.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tarea05.myapplication.R;

public class AccionesActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_editar_cliente, btn_solicitar_prestamo, btn_registrar_pagos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acciones);

        btn_editar_cliente = findViewById(R.id.btn_editar_cliente);
        btn_solicitar_prestamo = findViewById(R.id.btn_solicitar_prestamo);

        btn_editar_cliente.setOnClickListener(this::onClick);

        Bundle selectedCliente = getIntent().getExtras();

        String linea_credito = selectedCliente.getString("linea_credito_cliente");

        btn_solicitar_prestamo.setEnabled(!linea_credito.isEmpty());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
            Bundle receivedBundle = data.getExtras();
            String lineaCreditoCliente = receivedBundle.getString("linea_credito_cliente");
            btn_solicitar_prestamo.setEnabled(!lineaCreditoCliente.isEmpty());
        }
    }

    @Override
    public void onClick(View v) {
        int clickedId = v.getId();
        Intent activityIntent = getIntent();
        Bundle receivedBundle = activityIntent.getExtras();

        switch (clickedId) {
            case R.id.btn_editar_cliente:
                Intent editarClienteIntent = new Intent(AccionesActivity.this, EditarActivity.class);
                editarClienteIntent.putExtras(receivedBundle);

                startActivityForResult(editarClienteIntent, receivedBundle.getInt("id_cliente"));
                break;
        }
    }
}