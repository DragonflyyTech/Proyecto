package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pa.Base.BDUser;
import com.example.pa.Base.BDTest;
import com.example.pa.Especialista.Info;
import com.example.pa.R;

import java.util.List;

public class Test1 extends AppCompatActivity {

    private List<InfoTest> lista;
    private Info info = new Info();
    Object object = null;

    EditText pregunta1, pregunta2, pregunta3, pregunta4, pregunta5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregresar = findViewById(R.id.btn7regresar);
        Button enviar = findViewById(R.id.btnenvi);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Test1.this);
        Info2 info2 = bdUser.GetEspecialista(us);
        String uu = info2.getUsuario();

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Test1.this, Test.class);
                intent.putExtra("User", uu);
                startActivity(intent);
                finish();
            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pregunta1 = findViewById(R.id.Pregunta1);
                pregunta2 = findViewById(R.id.Pregunta2);
                pregunta3 = findViewById(R.id.Pregunta3);
                pregunta4 = findViewById(R.id.Pregunta4);
                pregunta5 = findViewById(R.id.Pregunta5);

                if (pregunta1.getText().length()==0 || pregunta2.getText().length()==0 || pregunta3.getText().length()==0 || pregunta4.getText().length()==0 || pregunta5.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_LONG).show();
                }
                else{

                    InfoTest infoTest = new InfoTest();

                    infoTest.setId_user(info.getId_user());
                    infoTest.setPregunta1(String.valueOf(pregunta1.getText()));
                    infoTest.setPregunta2(String.valueOf(pregunta2.getText()));
                    infoTest.setPregunta3(String.valueOf(pregunta3.getText()));
                    infoTest.setPregunta4(String.valueOf(pregunta4.getText()));
                    infoTest.setPregunta5(String.valueOf(pregunta5.getText()));

                    BDTest bdTest = new BDTest(Test1.this);
                    long id = bdTest.saveTest(infoTest);
                    if(id>0){
                        Toast.makeText(Test1.this, "Guardado",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(Test1.this, InicioA.class);
                        intent2.putExtra("User", uu);
                        startActivity(intent2);
                    }else
                    {
                        Toast.makeText(Test1.this, "No se ha podido guardar",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}