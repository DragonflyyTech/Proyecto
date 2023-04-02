package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pa.R;

public class CitasE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_e);

        Button buttonregresar = findViewById(R.id.btn6regresar);

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CitasE.this, InicioB.class);
                startActivity(intent);
                finish();
            }
        });
    }
}