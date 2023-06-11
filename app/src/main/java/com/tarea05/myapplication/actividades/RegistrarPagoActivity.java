package com.tarea05.myapplication.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.tarea05.myapplication.R;
import com.tarea05.myapplication.modelos.Prestamo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RegistrarPagoActivity extends AppCompatActivity {

    DatePickerDialog dialogoFecha;
    TextView tvFechaPago, tvSaldo;
    Spinner prestamoSelector;
    Button btnRegistrarPago;
    EditText input_monto;
    private int saldo_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_pago);

        Bundle receivedBundle = getIntent().getExtras();

        Prestamo prestamo1 = (Prestamo) receivedBundle.getSerializable("prestamo_1_cliente");
        Prestamo prestamo2 = (Prestamo) receivedBundle.getSerializable("prestamo_2_cliente");
        Prestamo prestamo3 = (Prestamo) receivedBundle.getSerializable("prestamo_3_cliente");

        tvFechaPago = findViewById(R.id.fecha_selector);
        tvSaldo = findViewById(R.id.tv_saldo);
        prestamoSelector = findViewById(R.id.prestamo_selector);
        btnRegistrarPago = findViewById(R.id.btn_registrar_pago);
        input_monto = findViewById(R.id.input_monto);

        ArrayList<String> prestamos = new ArrayList<String>();
        prestamos.add("Prestamo 1");
        prestamos.add("Prestamo 2");
        prestamos.add("Prestamo 3");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, prestamos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prestamoSelector.setAdapter(adapter);
        prestamoSelector.setPrompt("Selecciona un prestamo");
        prestamoSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        saldo_actual = prestamo1.getSaldo();
                        break;
                    case 1:
                        saldo_actual = prestamo2.getSaldo();
                        break;
                    case 2:
                        saldo_actual = prestamo3.getSaldo();
                        break;
                    default:
                        break;
                }
                tvSaldo.setText(saldo_actual + "");
                input_monto.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvFechaPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date fecha_actual = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(fecha_actual.getTime());
                long minDate = calendar.getTimeInMillis();
                long maxDate = calendar.getTimeInMillis();

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
                dialogoFecha.getDatePicker().setMaxDate(maxDate);

                dialogoFecha.show();
            }
        });

        input_monto.addTextChangedListener(new TextWatcher() {
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
                if (monto_ingresado > saldo_actual) {
                    input_monto.setText(saldo_actual + "");
                } else if (monto_ingresado < 0) {
                    input_monto.setText(0 + "");
                }
            }
        });

        btnRegistrarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pagoInfo = new Bundle();
                String montoIngresado = input_monto.getText().toString();
                int selectedPrestamoPosition = prestamoSelector.getSelectedItemPosition();
                String fechaPago = tvFechaPago.getText().toString();

                pagoInfo.putString("monto_pago", montoIngresado);
                pagoInfo.putString("prestamo_pago", (selectedPrestamoPosition + 1) + "");
                pagoInfo.putString("fecha_pago", fechaPago);

                Intent registrarPagoIntent = new Intent();
                registrarPagoIntent.putExtras(pagoInfo);

                setResult(RESULT_OK, registrarPagoIntent);
                finish();
            }
        });

        saldo_actual = prestamo1.getSaldo();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle receivedBundle = getIntent().getExtras();
        if (receivedBundle != null) {
            if (receivedBundle.getSerializable("prestamo_1_cliente") == null) return;

            Prestamo prestamo = (Prestamo) receivedBundle.getSerializable("prestamo_1_cliente");
            tvSaldo.setText(prestamo.getSaldo() + "");
        }
    }
}