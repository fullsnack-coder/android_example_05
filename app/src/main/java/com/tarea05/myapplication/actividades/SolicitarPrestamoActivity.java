package com.tarea05.myapplication.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        Bundle receivedBundle = getIntent().getExtras();
        if (receivedBundle == null) return;
        String monto_actual_prestamo = receivedBundle.getString("monto_prestamo");
        String saldo_actual_prestamo = receivedBundle.getString("saldo_prestamo");
        String linea_credito_actual = receivedBundle.getString("linea_credito_cliente");

        if (monto_actual_prestamo == null || monto_actual_prestamo.isEmpty()) return;
        if (saldo_actual_prestamo == null || saldo_actual_prestamo.isEmpty()) return;

        input_monto_prestamo.setText(monto_actual_prestamo);
        tv_saldo.setText(saldo_actual_prestamo);

        if (monto_actual_prestamo == "0" || Integer.parseInt(monto_actual_prestamo) > Integer.parseInt(linea_credito_actual)) {
            btn_actualiza_prestamo.setEnabled(false);
        }

        btn_actualiza_prestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monto_ingresado = input_monto_prestamo.getText().toString();
                Intent guardar_info_intent = new Intent();
                Bundle guardar_info_contenedor = new Bundle();

                guardar_info_contenedor.putString("monto_prestamo", monto_ingresado);

                guardar_info_intent.putExtras(guardar_info_contenedor);
                setResult(RESULT_OK, guardar_info_intent);
                finish();
            }
        });

        input_monto_prestamo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) return;
                int monto_ingresado = Integer.parseInt(s.toString());
                int valor_minimo = 0;


                int valor_maximo = Integer.parseInt(linea_credito_actual);
                if (monto_ingresado > valor_maximo) {
                    input_monto_prestamo.setText(valor_maximo + "");
                } else if (monto_ingresado < valor_minimo) {
                    input_monto_prestamo.setText(valor_minimo + "");
                }
            }
        });

    }


}