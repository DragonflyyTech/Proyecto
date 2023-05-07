package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.pa.Base.BDUser;
import com.example.pa.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregresar = findViewById(R.id.btn4regresar);
        Button btntest = findViewById(R.id.button);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Test.this);
        Info2 info2 = bdUser.GetEspecialista(us);
        String uu = info2.getUsuario();

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test.this, InicioA.class);
                intent.putExtra("User", uu);
                startActivity(intent);
                finish();
            }
        });

        btntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Test.this, Test1.class);
                intent2.putExtra("User", uu);
                startActivity(intent2);
                finish();
            }
        });
    }
}