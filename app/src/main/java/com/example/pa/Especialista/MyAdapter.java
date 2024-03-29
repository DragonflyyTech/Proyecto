package com.example.pa.Especialista;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pa.Paciente.Info2;
import com.example.pa.R;

import java.io.Serializable;
import java.util.List;

public class MyAdapter extends BaseAdapter implements Serializable {

    private List<Info2> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter(List<Info2> list, Context context)
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

        TextView textView = null;
        TextView textView1 = null;

        view = layoutInflater.inflate(R.layout.listausers, null);
        textView = view.findViewById(R.id.textName);
        textView1 = view.findViewById(R.id.textUser);
        textView.setText("Nombre " + String.valueOf(list.get(i).getNombre()));
        textView1.setText(String.valueOf(list.get(i).getUsuario()));

        Intent intent = new Intent( view.getContext(), Expediente.class);
        intent.putExtra("User",String.valueOf(list.get(i).getUsuario()));

        return view;
    }
}
