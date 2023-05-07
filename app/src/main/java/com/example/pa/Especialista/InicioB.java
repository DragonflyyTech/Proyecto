package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pa.Base.BDEspe;
import com.example.pa.Base.BDService;
import com.example.pa.Base.BDUser;
import com.example.pa.Paciente.Info2;
import com.example.pa.Login;
import com.example.pa.R;

import java.util.ArrayList;
import java.util.List;

public class InicioB extends AppCompatActivity{

    String aux = null;
    Info info = null;
    Info2 info2 = new Info2();
    TextView textView;
    Object object = null;
    public static ListView listView;
    private List<Info2>list;

    ArrayList<String> listaInfo;
    ArrayList<Info2> usuarios;

    BDService bdService;
    public int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textView = findViewById(R.id.textViewusr);
        Button salir = findViewById(R.id.salir2);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("UserE");
        BDEspe bdEspe = new BDEspe(InicioB.this);
        info = bdEspe.GetUsuario(us);
        String uu = info.getUsuario();
        textView.setText("Bienvenido  " + uu);


        listView = findViewById(R.id.listview1);
        BDUser bdUser = new BDUser(this);
        ArrayList arrayAdapter = (ArrayList) bdUser.getEspecialistas();

        MyAdapter myAdapter = new MyAdapter(arrayAdapter, getBaseContext());
        listView.setAdapter(myAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TextView textView1 = view.findViewById(R.id.textUser);
                String us = (String) textView1.getText();
                Intent intent = new Intent(InicioB.this, Expediente.class);
                intent.putExtra("User", us);
                intent.putExtra("UserE", uu);
                startActivity(intent);
                finish();

            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioB.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void toast( int i )
    {
        String us = list.get(i).getUsuario();
        Intent intent = new Intent(InicioB.this, Expediente.class);
        intent.putExtra("User", us);
        startActivity(intent);
        finish();

    }

}