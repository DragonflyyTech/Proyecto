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
import com.example.pa.Base.BDTest;
import com.example.pa.Base.BDUser;
import com.example.pa.Paciente.Info2;
import com.example.pa.R;

import java.util.ArrayList;

public class Testes extends AppCompatActivity {

    Info2 info2 = null;
    Info info = null;
    public static ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testes);

        Button regresar = findViewById(R.id.btn10regresar);
        TextView textView = findViewById(R.id.tus);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Testes.this);
        info2 = bdUser.GetEspecialista(us);
        textView.setText(String.valueOf(info2.getUsuario()));

        Bundle inte1 = getIntent().getExtras();
        String uss = inte1.getString("UserE");
        BDEspe bdEspe = new BDEspe(Testes.this);
        info = bdEspe.GetUsuario(uss);
        String ue = info.getUsuario();

        listView = findViewById(R.id.listView3);
        int id = info2.getId_espe();
        BDTest bdTest = new BDTest(this);
        ArrayList arrayAdapter = (ArrayList) bdTest.getTest(id);

        MyAdapter3 myAdapter3 = new MyAdapter3(arrayAdapter, getBaseContext());
        listView.setAdapter(myAdapter3);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Testes.this, Expediente.class);
                intent.putExtra("User", us);
                intent.putExtra("UserE", ue);
                startActivity(intent);
                finish();
            }
        });
    }
}