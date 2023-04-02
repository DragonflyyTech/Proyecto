package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pa.R;

public class Expediente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expediente);

        Button buttonregresar = findViewById(R.id.btn7regresar);

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Expediente.this, InicioB.class);
                startActivity(intent);
                finish();
            }
        });
    }
}