package com.tarea05.myapplication.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tarea05.myapplication.R;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHeading;
    EditText input_nombres, input_apellidos, input_dni, input_linea_credito;
    Button btn_cancelar, btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Intent navigationIntent = getIntent();
        Bundle receivedExtra = navigationIntent.getExtras();

        tvHeading = findViewById(R.id.tv_heading_editar);
        input_nombres = findViewById(R.id.input_nombres);
        input_apellidos = findViewById(R.id.input_apellidos);
        input_dni = findViewById(R.id.input_dni);
        btn_cancelar = findViewById(R.id.btn_cancelar_editar);
        btn_guardar = findViewById(R.id.btn_guardar_editar);
        input_linea_credito = findViewById(R.id.input_credito);

        int idCliente = navigationIntent.getIntExtra("id_cliente", 0);
        tvHeading.setText("Editar Cliente " + idCliente);

        input_nombres.setText(receivedExtra.getString("nombres_cliente"));
        input_dni.setText(receivedExtra.getString("dni_cliente"));
        input_apellidos.setText(receivedExtra.getString("apellidos_cliente"));
        input_linea_credito.setText(receivedExtra.getString("linea_credito_cliente"));

        btn_cancelar.setOnClickListener(this::onClick);
        btn_guardar.setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View view) {
        int clickedId = view.getId();
        String newDNI = input_dni.getText().toString();
        String newName = input_nombres.getText().toString();
        String newLastname = input_apellidos.getText().toString();
        String newCreditLine = input_linea_credito.getText().toString();

        switch (clickedId) {
            case R.id.btn_cancelar_editar:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.btn_guardar_editar:
                Toast.makeText(this, "Cliente editado correctamente", Toast.LENGTH_SHORT).show();
                Bundle bundleToSend = new Bundle();
                bundleToSend.putString("dni_cliente", newDNI);
                bundleToSend.putString("nombres_cliente", newName);
                bundleToSend.putString("apellidos_cliente", newLastname);
                bundleToSend.putString("linea_credito_cliente", newCreditLine);

                Intent guardarIntent = new Intent();
                guardarIntent.putExtras(bundleToSend);

                setResult(RESULT_OK, guardarIntent);
                finish();
                break;
            default:
        }


    }
}