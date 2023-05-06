package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.pa.Base.BDEspe;
import com.example.pa.Paciente.Info;
import com.example.pa.R;

public class InicioB extends AppCompatActivity{

    String aux = null;
    Info info = null;
    TextView textView;
    Object object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textView = findViewById(R.id.textViewusr);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDEspe bdEspe = new BDEspe(InicioB.this);
        info = bdEspe.GetUsuario(us);
        String uu = info.getUsuario();
        textView.setText("Bienvenido  " + uu);

    }
}