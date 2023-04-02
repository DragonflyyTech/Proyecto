package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pa.Base.BDDiario;
import com.example.pa.InfoDiario;
import com.example.pa.R;

import java.util.List;

public class Diario extends AppCompatActivity {

    private List<InfoDiario> lista;
    private Info info = new Info();

    Object object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diario);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregresar = findViewById(R.id.regresar);
        Button btnguardar = findViewById(R.id.btnenvia);

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diario.this, InicioA.class);
                startActivity(intent);
                finish();

            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                if( intent != null)
                {
                    if( intent.getExtras() != null ) {
                        object = intent.getExtras().get("Info");
                        if (object != null) {
                            if (object instanceof Info) {
                                info = (Info) object;
                            }
                        }
                    }
                }

                String titulo = String.valueOf(R.id.titulo);
                String conte = String.valueOf(R.id.contenido);

                RadioButton feliz = findViewById(R.id.feliz);
                RadioButton triste = findViewById(R.id.triste);
                RadioButton enojado = findViewById(R.id.enoajdo);
                RadioButton serio = findViewById(R.id.serio);


                if (titulo.length()==0 || conte.length()==0){
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

                    infoDiario.setTitulo(titulo);
                    infoDiario.setContenido(conte);
                    infoDiario.setId_user(info.getId_user());

                    BDDiario bdDiario = new BDDiario(Diario.this);
                    long id = bdDiario.saveDiario(infoDiario);
                    if(id>0){
                        Toast.makeText(Diario.this, "Guardado",Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(Diario.this, InicioA.class);
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