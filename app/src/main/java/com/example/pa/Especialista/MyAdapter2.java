package com.example.pa.Especialista;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.pa.Paciente.Info2;
import com.example.pa.Paciente.InfoDiario;
import com.example.pa.R;

import java.io.Serializable;
import java.util.List;

public class MyAdapter2 extends BaseAdapter implements Serializable {

    private List<InfoDiario> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter2(List<InfoDiario> list, Context context)
    {
        this.list = list;
        this.context = context;
        if( context != null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public boolean isEmptyOrNull( )
    {
        return list == null || list.size() == 0;
    }

    @Override
    public int getCount() {
        if (isEmptyOrNull()) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(isEmptyOrNull())
        {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView fecha = null;
        TextView emocion = null;
        TextView titulo = null;
        TextView contenido = null;
        CardView cardView = null;

        view = layoutInflater.inflate(R.layout.listadiarios, null);
        fecha = view.findViewById(R.id.textView57);
        emocion = view.findViewById(R.id.textView58);
        titulo = view.findViewById(R.id.textView59);
        contenido = view.findViewById(R.id.textView60);
        cardView = view.findViewById(R.id.cart2);

        fecha.setText("Fecha: "+ list.get(i).getFecha());
        emocion.setText("Emocion: "+ list.get(i).getEmocion());
        titulo.setText("Titulo: "+ list.get(i).getTitulo());
        contenido.setText("Contenido: "+ list.get(i).getContenido());

        return view;


    }
}
