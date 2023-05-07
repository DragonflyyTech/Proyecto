package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.pa.Base.BDEspe;
import com.example.pa.Base.BDUser;
import com.example.pa.Paciente.Info2;
import com.example.pa.Paciente.InicioA;
import com.example.pa.R;

public class Expediente extends AppCompatActivity {

    Info2 info2 = null;
    Info info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expediente);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregresar = findViewById(R.id.btn7regresar);
        Button diario = findViewById(R.id.revisarD);
        Button test = findViewById(R.id.revisarT);
        TextView textView = findViewById(R.id.textView40);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Expediente.this);
        info2 = bdUser.GetEspecialista(us);
        textView.setText(String.valueOf(info2.getUsuario()));

        Bundle inte1 = getIntent().getExtras();
        String uss = inte1.getString("UserE");
        BDEspe bdEspe = new BDEspe(Expediente.this);
        info = bdEspe.GetUsuario(uss);
        String ue = info.getUsuario();

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Expediente.this, InicioB.class);
                intent.putExtra("UserE", ue);
                startActivity(intent);
                finish();
            }
        });
    }
}