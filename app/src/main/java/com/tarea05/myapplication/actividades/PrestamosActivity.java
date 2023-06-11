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
import com.tarea05.myapplication.modelos.Prestamo;

public class PrestamosActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLineaCredito,
        tvMontoPrestamo1, tvMontoPrestamo2, tvMontoPrestamo3,
        tvEstadoPrestamo1, tvEstadoPrestamo2, tvEstadoPrestamo3;

    Button btnSolicitarPrestamo1, btnSolicitarPrestamo2, btnSolicitarPrestamo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);

        tvLineaCredito = findViewById(R.id.tv_linea_credito);
        btnSolicitarPrestamo1 = findViewById(R.id.btn_solicitar_prestamo_1);
        btnSolicitarPrestamo2 = findViewById(R.id.btn_solicitar_prestamo_2);
        btnSolicitarPrestamo3 = findViewById(R.id.btn_solicitar_prestamo_3);

        tvMontoPrestamo1 = findViewById(R.id.tv_monto_prestamo_1);
        tvMontoPrestamo2 = findViewById(R.id.tv_monto_prestamo_2);
        tvMontoPrestamo3 = findViewById(R.id.tv_monto_prestamo_3);

        tvEstadoPrestamo1 = findViewById(R.id.tv_estado_prestamo_1);
        tvEstadoPrestamo2 = findViewById(R.id.tv_estado_prestamo_2);
        tvEstadoPrestamo3 = findViewById(R.id.tv_estado_prestamo_3);

        Bundle prestamosReceivedBundle = getIntent().getExtras();

        String linea = prestamosReceivedBundle.getString("linea_credito_cliente");
        if (!linea.isEmpty()) {
            tvLineaCredito.setText(linea);
            btnSolicitarPrestamo1.setOnClickListener(this::onClick);
            btnSolicitarPrestamo2.setOnClickListener(this::onClick);
            btnSolicitarPrestamo3.setOnClickListener(this::onClick);
        }

        Prestamo prestamo_1 = (Prestamo) prestamosReceivedBundle.getSerializable("prestamo_1_cliente");
        Prestamo prestamo_2 = (Prestamo) prestamosReceivedBundle.getSerializable("prestamo_2_cliente");
        Prestamo prestamo_3 = (Prestamo) prestamosReceivedBundle.getSerializable("prestamo_3_cliente");

        if (prestamo_1 != null) {
            tvMontoPrestamo1.setText(prestamo_1.getMonto_pedido() + "");
            tvEstadoPrestamo1.setText(prestamo_1.getEstado());
        }
        if (prestamo_2 != null) {
            tvMontoPrestamo2.setText(prestamo_2.getMonto_pedido() + "");
            tvEstadoPrestamo2.setText(prestamo_2.getEstado());
        }
        if (prestamo_3 != null) {
            tvMontoPrestamo3.setText(prestamo_3.getMonto_pedido() + "");
            tvEstadoPrestamo3.setText(prestamo_3.getEstado());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int prestamo_id = requestCode;

        if (resultCode == RESULT_OK && data != null) {
            String monto = data.getExtras().getString("monto_prestamo");
            Bundle solicitaPrestamoBundle = new Bundle();
            solicitaPrestamoBundle.putString("id_prestamo", prestamo_id + "");
            solicitaPrestamoBundle.putString("monto_prestamo", monto);

            if (monto.isEmpty()) return;
            Intent solicitaPrestamoIntent = new Intent();
            solicitaPrestamoIntent.putExtras(solicitaPrestamoBundle);
            setResult(RESULT_OK, solicitaPrestamoIntent);

            switch (prestamo_id) {
                case 1:
                    tvMontoPrestamo1.setText(monto);
                    tvEstadoPrestamo1.setText("Registrado");
                    break;
                case 2:
                    tvMontoPrestamo2.setText(monto);
                    tvEstadoPrestamo2.setText("Registrado");
                    break;
                case 3:
                    tvMontoPrestamo3.setText(monto);
                    tvEstadoPrestamo3.setText("Registrado");
                    break;
                default:
                    break;
            }
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        int clicked_id = v.getId();
        int id_prestamo = 0;
        Bundle solicitarPrestamoBundle = new Bundle();
        String linea_credito_actual = tvLineaCredito.getText().toString();
        solicitarPrestamoBundle.putString("linea_credito_cliente", linea_credito_actual);

        Bundle receivedBundle = getIntent().getExtras();

        switch (clicked_id) {
            case R.id.btn_solicitar_prestamo_1:
                Prestamo prestamo1 = (Prestamo) receivedBundle.getSerializable("prestamo_1_cliente");
                if (prestamo1 == null) return;
                id_prestamo = 1;
                solicitarPrestamoBundle.putString("id_prestamo", "1");
                solicitarPrestamoBundle.putString("monto_prestamo", tvMontoPrestamo1.getText().toString());
                solicitarPrestamoBundle.putString("saldo_prestamo", prestamo1.getSaldo() + "");
                solicitarPrestamoBundle.putSerializable("pagos_prestamo", prestamo1.getPagos());
                break;
            case R.id.btn_solicitar_prestamo_2:
                id_prestamo = 2;
                Prestamo prestamo2 = (Prestamo) receivedBundle.getSerializable("prestamo_2_cliente");
                solicitarPrestamoBundle.putString("id_prestamo", "2");
                solicitarPrestamoBundle.putString("monto_prestamo", tvMontoPrestamo2.getText().toString());
                solicitarPrestamoBundle.putString("saldo_prestamo", prestamo2.getSaldo() + "");
                break;
            case R.id.btn_solicitar_prestamo_3:
                id_prestamo = 3;
                Prestamo prestamo3 = (Prestamo) receivedBundle.getSerializable("prestamo_3_cliente");
                solicitarPrestamoBundle.putString("id_prestamo", "3");
                solicitarPrestamoBundle.putString("monto_prestamo", tvMontoPrestamo3.getText().toString());
                solicitarPrestamoBundle.putString("saldo_prestamo", prestamo3.getSaldo() + "");
                break;
            default:
                break;
        }
        Intent solicitarPrestamoIntent = new Intent(PrestamosActivity.this, SolicitarPrestamoActivity.class);
        solicitarPrestamoIntent.putExtras(solicitarPrestamoBundle);
        startActivityForResult(solicitarPrestamoIntent, id_prestamo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle prestamosReceivedBundle = getIntent().getExtras();
        int linea_credito_inicial = Integer.parseInt(prestamosReceivedBundle.getString("linea_credito_cliente"));

        int monto_prestamo_1 = Integer.parseInt(tvMontoPrestamo1.getText().toString());
        int monto_prestamo_2 = Integer.parseInt(tvMontoPrestamo2.getText().toString());
        int monto_prestamo_3 = Integer.parseInt(tvMontoPrestamo3.getText().toString());

        int restante = linea_credito_inicial - (monto_prestamo_1 + monto_prestamo_2 + monto_prestamo_3);
        tvLineaCredito.setText(restante + "");
    }
}