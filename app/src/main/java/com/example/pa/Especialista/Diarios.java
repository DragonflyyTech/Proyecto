package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pa.Base.BDDiario;
import com.example.pa.Base.BDEspe;
import com.example.pa.Base.BDUser;
import com.example.pa.Paciente.Info2;
import com.example.pa.R;

import java.util.ArrayList;

public class Diarios extends AppCompatActivity {

    Info2 info2 = null;
    Info info = null;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diarios);

        Button regresar = findViewById(R.id.btn9regresar);
        TextView textView = findViewById(R.id.dus);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Diarios.this);
        info2 = bdUser.GetEspecialista(us);
        textView.setText(String.valueOf(info2.getUsuario()));

        Bundle inte1 = getIntent().getExtras();
        String uss = inte1.getString("UserE");
        BDEspe bdEspe = new BDEspe(Diarios.this);
        info = bdEspe.GetUsuario(uss);
        String ue = info.getUsuario();


        listView = findViewById(R.id.listView2);
        int id = info2.getId_espe();
        BDDiario bdDiario = new BDDiario(this);
        ArrayList arrayAdapter = (ArrayList) bdDiario.getDiario(id);

        MyAdapter2 myAdapter2 = new MyAdapter2(arrayAdapter, getBaseContext());
        listView.setAdapter(myAdapter2);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diarios.this, Expediente.class);
                intent.putExtra("User", us);
                intent.putExtra("UserE", uss);
                startActivity(intent);
                finish();
            }
        });
    }
}