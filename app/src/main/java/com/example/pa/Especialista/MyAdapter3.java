package com.example.pa.Especialista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.pa.Paciente.InfoDiario;
import com.example.pa.Paciente.InfoTest;
import com.example.pa.R;

import java.io.Serializable;
import java.util.List;

public class MyAdapter3 extends BaseAdapter implements Serializable {

    private List<InfoTest> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAdapter3(List<InfoTest> list, Context context)
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

        TextView pregunta1 = null;
        TextView pregunta2 = null;
        TextView pregunta3 = null;
        TextView pregunta4 = null;
        TextView pregunta5 = null;
        CardView cardView = null;

        view = layoutInflater.inflate(R.layout.listatest, null);
        pregunta1 = view.findViewById(R.id.textView61);
        pregunta2 = view.findViewById(R.id.textView62);
        pregunta3 = view.findViewById(R.id.textView63);
        pregunta4 = view.findViewById(R.id.textView64);
        pregunta5 = view.findViewById(R.id.textView65);

        pregunta1.setText("1: "+ list.get(i).getPregunta1());
        pregunta2.setText("2: "+ list.get(i).getPregunta2());
        pregunta3.setText("3: "+ list.get(i).getPregunta3());
        pregunta4.setText("4: "+ list.get(i).getPregunta4());
        pregunta5.setText("5: "+ list.get(i).getPregunta5());

        return view;


    }
}
