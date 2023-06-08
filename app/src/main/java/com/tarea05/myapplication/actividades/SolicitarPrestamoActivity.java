package com.tarea05.myapplication.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tarea05.myapplication.R;

public class SolicitarPrestamoActivity extends AppCompatActivity {

    TextView tv_saldo;
    EditText input_monto_prestamo;
    Button btn_actualiza_prestamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_prestamo);

        tv_saldo = findViewById(R.id.tv_saldo);
        input_monto_prestamo = findViewById(R.id.input_monto_prestamo);
        btn_actualiza_prestamo = findViewById(R.id.btn_actualiza_prestamo);

        btn_actualiza_prestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monto_ingresado = input_monto_prestamo.getText().toString();
                Intent guardar_info_intent = new Intent();
                Bundle guardar_info_contenedor = new Bundle();

                Log.println(Log.INFO, "click", "clickeed");
                guardar_info_contenedor.putString("monto_prestamo", monto_ingresado);

                guardar_info_intent.putExtras(guardar_info_contenedor);
                setResult(RESULT_OK, guardar_info_intent);
            }
        });

    }
}