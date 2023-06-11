package com.tarea05.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tarea05.myapplication.modelos.Pago;

import java.util.ArrayList;

public class PagoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pago> pagos;
    TextView tv_monto_pago, tv_fecha_pago;

    public PagoAdapter(Context context, ArrayList<Pago> pagos) {
        this.context = context;
        this.pagos = pagos;
    }

    @Override
    public int getCount() {
        return pagos.size();
    }

    @Override
    public Object getItem(int i) {
        return pagos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.pago_item, viewGroup, false);
        }

        tv_monto_pago = view.findViewById(R.id.tv_monto_pago);
        tv_fecha_pago = view.findViewById(R.id.tv_fecha_pago);
        Pago pago = this.pagos.get(i);

        tv_monto_pago.setText(pago.getMonto() + "");
        tv_fecha_pago.setText(pago.getFecha());

        return view;
    }
}
