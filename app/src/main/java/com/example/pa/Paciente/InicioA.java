package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.pa.Base.BDUser;
import com.example.pa.Info2;
import com.example.pa.Login;
import com.example.pa.R;

import java.util.List;

public class InicioA extends AppCompatActivity {

    String aux = null;
    Info2 info2 = null;
    Object object = null;

    private List<Info> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton buttondiario = findViewById(R.id.btndiario);
        ImageButton buttontest = findViewById(R.id.btntest);
        Button salir = findViewById(R.id.salir);
        Button butt = findViewById(R.id.button2);


        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(InicioA.this);
        info2 = bdUser.GetEspecialista(us);
        String uu = info2.getUsuario();
        butt.setText("Bienvenido  " + uu);

        buttondiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Diario.class);
                intent.putExtra("User", uu);
                startActivity(intent);
            }
        });
        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Test.class);
                intent.putExtra("User", uu);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Login.class);
                startActivity(intent);
            }
        });
    }
}