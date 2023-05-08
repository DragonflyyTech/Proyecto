package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pa.Base.BDDiario;
import com.example.pa.Base.BDUser;
import com.example.pa.Especialista.Info;
import com.example.pa.R;

import java.util.Calendar;
import java.util.List;

public class Diario extends AppCompatActivity {

    private List<InfoDiario> lista;
    private Info2 info2 = new Info2();
    Object object = null;

    EditText titulo, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregresar = findViewById(R.id.regresar);
        Button btnguardar = findViewById(R.id.btnenvia);
        TextView fecha = findViewById(R.id.textViewFecha);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(Diario.this);
        info2 = bdUser.GetEspecialista(us);
        String uu = info2.getUsuario();
        int id = info2.getId_espe();

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Diario.this, InicioA.class);
                intent.putExtra("User", uu);
                startActivity(intent);
                finish();

            }
        });

        Calendar calendar = Calendar.getInstance();
        int dianow = calendar.get(Calendar.DATE);
        int mesnow = calendar.get(Calendar.MONTH);
        int anionow = calendar.get(Calendar.YEAR);
        mesnow++;
        fecha.setText("Fecha: " + dianow + " / "+mesnow+" / "+anionow);

        titulo = findViewById(R.id.titulo);
        contenido = findViewById(R.id.contenido);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioButton feliz = findViewById(R.id.feliz);
                RadioButton triste = findViewById(R.id.triste);
                RadioButton enojado = findViewById(R.id.enoajdo);
                RadioButton serio = findViewById(R.id.serio);


                if (titulo.getText().length()==0 || contenido.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_LONG).show();
                }
                else{
                    InfoDiario infoDiario = new InfoDiario();

                    if (feliz.isChecked()==true){
                        infoDiario.setEmocion(String.valueOf(feliz.getText()));
                    }
                    if (triste.isChecked()==true){
                        infoDiario.setEmocion(String.valueOf(triste.getText()));
                    }
                    if (enojado.isChecked()==true){
                        infoDiario.setEmocion(String.valueOf(enojado.getText()));
                    }
                    if (serio.isChecked()==true){
                        infoDiario.setEmocion(String.valueOf(serio.getText()));
                    }

                    infoDiario.setTitulo(String.valueOf(titulo.getText()));
                    infoDiario.setContenido(String.valueOf(contenido.getText()));
                    infoDiario.setId_user(id);
                    infoDiario.setFecha((String) fecha.getText());

                    BDDiario bdDiario = new BDDiario(Diario.this);
                    long id = bdDiario.saveDiario(infoDiario);
                    if(id>0){
                        Toast.makeText(Diario.this, "Guardado",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(Diario.this, InicioA.class);
                        intent2.putExtra("User", uu);
                        startActivity(intent2);
                    }else
                    {
                        Toast.makeText(Diario.this, "No se ha podido guardar",Toast.LENGTH_LONG).show();
                    }


                }



            }
        });


    }
}