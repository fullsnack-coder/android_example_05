package com.tarea05.myapplication.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tarea05.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistrarPagoActivity extends AppCompatActivity {

    DatePickerDialog dialogoFecha;
    TextView tvFechaPago;
    Spinner prestamoSelector;
    Button btnRegistrarPago;
    EditText txt_monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pago);

        tvFechaPago = findViewById(R.id.fecha_selector);
        prestamoSelector = findViewById(R.id.prestamo_selector);
        btnRegistrarPago = findViewById(R.id.btn_registrar_pago);
        txt_monto = findViewById(R.id.input_monto);

        ArrayList<String> prestamos = new ArrayList<String>();
        prestamos.add("Prestamo 1");
        prestamos.add("Prestamo 2");
        prestamos.add("Prestamo 3");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, prestamos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prestamoSelector.setAdapter(adapter);
        prestamoSelector.setPrompt("Selecciona un prestamo");

        tvFechaPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 1970);
                long minDate = calendar.getTimeInMillis();

                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);

                dialogoFecha = new DatePickerDialog(RegistrarPagoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anio_seleccionado, int mes_seleccionado, int dia_seleccionado) {
                        mes_seleccionado = mes_seleccionado + 1;
                        String fechaSeleccionada = dia_seleccionado + "/" + mes_seleccionado + "/" + anio_seleccionado;
                        tvFechaPago.setText(fechaSeleccionada);
                    }
                }, dia, mes, anio);

                dialogoFecha.getDatePicker().setMinDate(minDate);

                dialogoFecha.show();
            }
        });

        btnRegistrarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pagoInfo = new Bundle();
                String montoIngresado = txt_monto.getText().toString();
                int selectedPrestamoPosition = prestamoSelector.getSelectedItemPosition();
                String prestamo = adapter.getItem(selectedPrestamoPosition);
                String fechaPago = tvFechaPago.getText().toString();

                Log.e("press::", "prestamo seleccionado: " + prestamo);

                pagoInfo.putString("monto_pago", montoIngresado);
                pagoInfo.putString("prestamo_pago", prestamo);
                pagoInfo.putString("fecha_pago", fechaPago);

                Intent registrarPagoIntent = new Intent();
                registrarPagoIntent.putExtras(pagoInfo);

                setResult(RESULT_OK, registrarPagoIntent);
            }
        });
    }
}