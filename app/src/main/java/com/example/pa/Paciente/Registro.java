package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pa.Base.BDUser;
import com.example.pa.Info2;
import com.example.pa.Login;
import com.example.pa.R;
import com.example.pa.SHA;

import java.util.ArrayList;
import java.util.List;

public class Registro extends AppCompatActivity {

    private Button btnregr;
    private EditText nombre;
    private EditText edad;
    private EditText email;
    private EditText user;
    private EditText contra;
    public static final String archivo = "registro.json";
    private static final String TAG = "Registro";

    Info2 info2 = new Info2();
    String usr = null;
    String mail = null;
    String mensaje = null;
    List<Info2> lista = new ArrayList<Info2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button buttonregresar = findViewById(R.id.btnregresar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        buttonregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btnregr = findViewById(R.id.btnreg);
        nombre = findViewById(R.id.regnom);
        edad = findViewById(R.id.regedad);
        email = findViewById(R.id.regmail);
        user = findViewById(R.id.reguser);
        contra = findViewById(R.id.regpwd);

        btnregr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombre.getText().length() == 0){
                    Toast.makeText( getApplicationContext() , "Campo de nombre vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(edad.getText().length() == 0){
                    Toast.makeText( getApplicationContext() , "Campo de edad vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(email.getText().length() == 0){
                    Toast.makeText( getApplicationContext() , "Campo de mail vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(contra.getText().length() == 0){
                    Toast.makeText( getApplicationContext() , "Campo de contraseña vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(user.getText().length() == 0){
                    Toast.makeText( getApplicationContext() , "Campo de usuario vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if (valuser(lista, usr)){
                    Toast.makeText( getApplicationContext() , "El nombre de usuraio no está disponibe" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(!PatternsCompat.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                    Toast.makeText( getApplicationContext() , "Error de sintaxis en el mail" , Toast.LENGTH_LONG ).show();
                    return;
                }

                info2.setNombre(String.valueOf(nombre.getText()));
                info2.setEdad(String.valueOf(edad.getText()));
                info2.setMail(String.valueOf(email.getText()));
                info2.setUsuario(String.valueOf(user.getText()));
                info2.setContra(SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText()))));
                usr = String.valueOf(user.getText());
                mail = String.valueOf(email.getText());

                BDUser bdUser = new BDUser(Registro.this);
                long id = bdUser.saveEspecialista(info2);
                if (id > 0){
                    Toast.makeText(Registro.this, "Registro Guardado",Toast.LENGTH_LONG).show();
                    Intent intent4 = new Intent(Registro.this, Login.class);
                    startActivity(intent4);
                }
                else {
                    Toast.makeText(Registro.this, "Error usuario no disponible",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public boolean valuser(List<Info2>lista, String user) {
        Boolean u = Boolean.FALSE;
        for (Info2 info1 : lista) {
            if (info1.getUsuario().equals(user)) {
                u = Boolean.TRUE;
            }
        }
        return u;
    }

}