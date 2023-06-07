package com.tarea05.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tarea05.myapplication.actividades.AccionesActivity;
import com.tarea05.myapplication.modelos.Banco;
import com.tarea05.myapplication.modelos.Cliente;

public class MainActivity extends AppCompatActivity {

    private Button btn_acciones_1, btn_acciones_2, btn_acciones_3, btn_acciones_4, btn_acciones_5;
    private TextView tv_dni_1, tv_dni_2, tv_dni_3, tv_dni_4, tv_dni_5,
                tv_cliente_1, tv_cliente_2, tv_cliente_3, tv_cliente_4, tv_cliente_5;
    
    private Banco objBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View includeCliente1 = findViewById(R.id.include_cliente_1);
        View includeCliente2 = findViewById(R.id.include_cliente_2);
        View includeCliente3 = findViewById(R.id.include_cliente_3);
        View includeCliente4 = findViewById(R.id.include_cliente_4);
        View includeCliente5 = findViewById(R.id.include_cliente_5);

        tv_dni_1 = includeCliente1.findViewById(R.id.tv_dni);
        tv_dni_2 = includeCliente2.findViewById(R.id.tv_dni);
        tv_dni_3 = includeCliente3.findViewById(R.id.tv_dni);
        tv_dni_4 = includeCliente4.findViewById(R.id.tv_dni);
        tv_dni_5 = includeCliente5.findViewById(R.id.tv_dni);
        
        tv_cliente_1 = includeCliente1.findViewById(R.id.tv_cliente);
        tv_cliente_2 = includeCliente2.findViewById(R.id.tv_cliente);
        tv_cliente_3 = includeCliente3.findViewById(R.id.tv_cliente);
        tv_cliente_4 = includeCliente4.findViewById(R.id.tv_cliente);
        tv_cliente_5 = includeCliente5.findViewById(R.id.tv_cliente);
        

        btn_acciones_1 = includeCliente1.findViewById(R.id.btn_acciones);
        btn_acciones_2 = includeCliente2.findViewById(R.id.btn_acciones);
        btn_acciones_3 = includeCliente3.findViewById(R.id.btn_acciones);
        btn_acciones_4 = includeCliente4.findViewById(R.id.btn_acciones);
        btn_acciones_5 = includeCliente5.findViewById(R.id.btn_acciones);

        agregarOnClickListener(btn_acciones_1, 0);
        agregarOnClickListener(btn_acciones_2, 1);
        agregarOnClickListener(btn_acciones_3, 2);
        agregarOnClickListener(btn_acciones_4, 3);
        agregarOnClickListener(btn_acciones_5, 4);

        // crear clientes en banco
        listarClientes();

        // pintar datos de clientes en pantalla
        cargarDatos();
    }

    private void agregarOnClickListener(Button btn_to_attach, int idCliente) {
        btn_to_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irAccionesIntent = new Intent(MainActivity.this, AccionesActivity.class);
                Cliente selectedCliente = objBanco.getCliente(idCliente);
                Bundle objBundle = new Bundle();
                objBundle.putInt("id_cliente", idCliente);
                objBundle.putString("dni_cliente", selectedCliente.getDni());
                objBundle.putString("nombres_cliente", selectedCliente.getNombres());
                objBundle.putString("apellidos_cliente", selectedCliente.getApellidos());
                objBundle.putString("linea_credito_cliente", selectedCliente.getLinea_credito());

                irAccionesIntent.putExtras(objBundle);

                startActivityForResult(irAccionesIntent, idCliente);
            }
        });
    }

    private void listarClientes() {
        this.objBanco = new Banco();
        this.objBanco.setObjCliente1(new Cliente("12345678", "Jose Armando", "Benitez Flores", ""));
        this.objBanco.setObjCliente2(new Cliente("87654321", "Gabriela Josefina", "Morales Duares", ""));
        this.objBanco.setObjCliente3(new Cliente("12345678", "Ana Clara", "Flores Morante", ""));
        this.objBanco.setObjCliente4(new Cliente("87612333", "Maria Luisa", "Corrales Ortiz", ""));
        this.objBanco.setObjCliente5(new Cliente("78901234", "Julio Cesar", "Castro Paredes", ""));
    }
    
    private void cargarDatos() {
        Cliente objCliente1 = this.objBanco.getObjCliente1();
        tv_dni_1.setText("DNI: " + objCliente1.getDni());
        tv_cliente_1.setText(objCliente1.getNombres() + " " + objCliente1.getApellidos());

        Cliente objCliente2 = this.objBanco.getObjCliente2();
        tv_dni_2.setText("DNI: " + objCliente2.getDni());
        tv_cliente_2.setText(objCliente2.getNombres() + " " + objCliente2.getApellidos());

        Cliente objCliente3 = this.objBanco.getObjCliente3();
        tv_dni_3.setText("DNI: " + objCliente3.getDni());
        tv_cliente_3.setText(objCliente3.getNombres() + " " + objCliente3.getApellidos());

        Cliente objCliente4 = this.objBanco.getObjCliente4();
        tv_dni_4.setText("DNI: " + objCliente4.getDni());
        tv_cliente_4.setText(objCliente4.getNombres() + " " + objCliente4.getApellidos());

        Cliente objCliente5 = this.objBanco.getObjCliente4();
        tv_dni_5.setText("DNI: " +objCliente5.getDni());
        tv_cliente_5.setText(objCliente5.getNombres() + " " + objCliente4.getApellidos());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle receivedBundle = data.getExtras();
            String dniCliente = receivedBundle.getString("dni_cliente");
            String apellidosCliente = receivedBundle.getString("apellidos_cliente");
            String nombresCliente = receivedBundle.getString("nombres_cliente");
            String lineaCreditoCliente = receivedBundle.getString("linea_credito_cliente");

            Log.println(Log.ASSERT, "ac_result:: ", lineaCreditoCliente);

            objBanco.getCliente(requestCode)
                    .setApellidos(apellidosCliente)
                    .setNombres(nombresCliente)
                    .setDni(dniCliente)
                    .setLinea_credito(lineaCreditoCliente);

            cargarDatos();
        }
    }
}