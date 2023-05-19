package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pa.Base.BDEspe;
import com.example.pa.Login;
import com.example.pa.R;
import com.example.pa.SHA;

import java.util.ArrayList;
import java.util.List;

public class RegistroA extends AppCompatActivity {

    private Button btnregr;
    private EditText nombre;
    private EditText edad;
    private EditText email;
    private EditText user;
    private EditText contra;

    private EditText cedula;
    public static final String archivo = "registro.json";
    private static final String TAG = "Registro";

    Info info = new Info();

    String usr = null;
    String mail = null;
    List<Info> list = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonregre = findViewById(R.id.btn2regresar);
        buttonregre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroA.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnregr = findViewById(R.id.btn2reg);
        nombre = findViewById(R.id.reg2nom);
        edad = findViewById(R.id.reg2edad);
        email = findViewById(R.id.reg2mail);
        user = findViewById(R.id.reg2user);
        contra = findViewById(R.id.reg2pwd);
        cedula = findViewById(R.id.reg2cedula);

        btnregr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombre.getText().length() == 0){
                    nombre.setError("Campo de nombre vacio");
                    nombre.requestFocus();
                    return;
                }
                if(edad.getText().length() == 0){
                    edad.setError("Campo de edad vacio");
                    edad.requestFocus();
                    return;
                }
                if(Integer.parseInt(String.valueOf(edad.getText())) < 15){
                    edad.setError("Lo sentimos aún no tienes la edad suficiente para utilizar Psyche");
                    edad.requestFocus();
                    return;
                }
                if(email.getText().length() == 0){
                    email.setError("Campo de mail vacio");
                    email.requestFocus();
                    return;
                }
                if(contra.getText().length() == 0){
                    contra.setError("Campo de contraseña vacio");
                    contra.requestFocus();
                    return;
                }
                if(user.getText().length() == 0){
                    user.setError("Campo de usuario vacio");
                    user.requestFocus();
                    return;
                }
                if (cedula.getText().length() == 0){
                    cedula.setError("Campo de cedula vacio");
                    cedula.requestFocus();
                    return;
                }
                if (valuser(list, usr)){
                    user.setError("El nombre de usuario no esta disponible");
                    user.requestFocus();
                    return;
                }
                if(!PatternsCompat.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                    email.setError("Error de sintaxis en el mail");
                    email.requestFocus();
                    return;
                }

                info.setNombre(String.valueOf(nombre.getText()));
                info.setEdad(String.valueOf(edad.getText()));
                info.setMail(String.valueOf(email.getText()));
                info.setUsuario(String.valueOf(user.getText()));
                info.setContra(SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText()))));
                info.setCedula(String.valueOf(cedula.getText()));
                usr = String.valueOf(user.getText());
                mail = String.valueOf(email.getText());

                BDEspe bdEspe = new BDEspe(RegistroA.this);
                long id = bdEspe.saveUser(info);
                if (id > 0){
                    Toast.makeText(RegistroA.this, "Registro Guardado",Toast.LENGTH_LONG).show();
                    Intent intent4 = new Intent(RegistroA.this, Login.class);
                    startActivity(intent4);
                }
                else {
                    Toast.makeText(RegistroA.this, "Error usuario no disponible",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean valuser(List<Info>lista, String user) {
        Boolean u = Boolean.FALSE;
        for (Info info1 : lista) {
            if (info1.getUsuario().equals(user)) {
                u = Boolean.TRUE;
            }
        }
        return u;
    }
}