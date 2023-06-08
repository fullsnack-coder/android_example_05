package com.tarea05.myapplication.actividades;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarea05.myapplication.R;

public class PrestamosActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLineaCredito;
    Button btnSolicitarPrestamo1, btnSolicitarPrestamo2, btnSolicitarPrestamo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);

        tvLineaCredito = findViewById(R.id.tv_linea_credito);
        btnSolicitarPrestamo1 = findViewById(R.id.btn_solicitar_prestamo_1);
        btnSolicitarPrestamo2 = findViewById(R.id.btn_solicitar_prestamo_2);
        btnSolicitarPrestamo3 = findViewById(R.id.btn_solicitar_prestamo_3);

        Bundle prestamosReceivedBundle = getIntent().getExtras();
        String linea = prestamosReceivedBundle.getString("linea_credito_cliente");
        if (!linea.isEmpty()) {
            tvLineaCredito.setText(linea);
            btnSolicitarPrestamo1.setOnClickListener(this::onClick);
            btnSolicitarPrestamo2.setOnClickListener(this::onClick);
            btnSolicitarPrestamo3.setOnClickListener(this::onClick);
        }
    }

    @Override
    public void onClick(View v) {
        int clicked_id = v.getId();
        int id_prestamo = 0;
        Bundle solicitarPrestamoBundle = new Bundle();
        switch (clicked_id) {
            case R.id.btn_solicitar_prestamo_1:
                id_prestamo = 1;
                solicitarPrestamoBundle.putString("id_prestamo", "1");
                break;
            case R.id.btn_solicitar_prestamo_2:
                id_prestamo = 2;
                solicitarPrestamoBundle.putString("id_prestamo", "2");
                break;
            case R.id.btn_solicitar_prestamo_3:
                id_prestamo = 3;
                solicitarPrestamoBundle.putString("id_prestamo", "3");
                break;
            default:
                break;
        }
        Intent solicitarPrestamoIntent = new Intent(PrestamosActivity.this, SolicitarPrestamoActivity.class);
        startActivityForResult(solicitarPrestamoIntent, id_prestamo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.println(Log.INFO, "acres", "request: " + requestCode);
        Log.println(Log.INFO,"acres", "result: " + resultCode);
        setResult(RESULT_OK, data);

    }
}