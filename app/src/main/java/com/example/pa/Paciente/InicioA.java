package com.example.pa.Paciente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pa.Base.BDUser;
import com.example.pa.Login;
import com.example.pa.R;
import com.example.pa.SHA;

import java.util.ArrayList;
import java.util.List;

public class InicioA extends AppCompatActivity {

    String aux = null;
    Info2 info2 = null;
    Object object = null;
    String usr = null;
    List<Info2> lista = new ArrayList<Info2>();
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton buttondiario = findViewById(R.id.btndiario);
        ImageButton buttontest = findViewById(R.id.btntest);
        Button salir = findViewById(R.id.salir);
        Button editar = findViewById(R.id.btnedita);


        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(InicioA.this);
        info2 = bdUser.GetEspecialista(us);
        String uu = info2.getUsuario();
        editar.setText("Bienvenido  " + uu);

        buttondiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Diario.class);
                intent.putExtra("User", uu);
                startActivity(intent);
            }
        });
        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Test.class);
                intent.putExtra("User", uu);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Login.class);
                startActivity(intent);
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edita();
            }
        });
    }

    public void Edita(){

        dialogBuilder = new AlertDialog.Builder(this);
        final View Popview = getLayoutInflater().inflate(R.layout.popedita, null);

        Button Edita = Popview.findViewById(R.id.Editar);
        Button regresar = Popview.findViewById(R.id.btn8regresar);
        EditText name = Popview.findViewById(R.id.nuevonombre);
        EditText edad = Popview.findViewById(R.id.nuevaedad);
        EditText mail = Popview.findViewById(R.id.nuevomail);
        EditText user = Popview.findViewById(R.id.nuevouser);
        EditText contra = Popview.findViewById(R.id.nuevacontra);

        Bundle inte = getIntent().getExtras();
        String us = inte.getString("User");
        BDUser bdUser = new BDUser(InicioA.this);
        info2 = bdUser.GetEspecialista(us);

        dialogBuilder.setView(Popview);
        dialog = dialogBuilder.create();
        dialog.show();

        name.setText(info2.getNombre());
        edad.setText(info2.getEdad());
        mail.setText(info2.getMail());
        user.setText(info2.getUsuario());

        Edita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText())));
                String nuevous = String.valueOf(user.getText());

                if (name.getText().length() == 0) {
                    name.setError("Campo de nombre vacio");
                    name.requestFocus();
                    return;
                }
                if (edad.getText().length() == 0){
                    edad.setError("Campo de edad vacio");
                    edad.requestFocus();
                    return;
                }
                if(Integer.parseInt(String.valueOf(edad.getText())) < 15){
                    edad.setError("Lo sentimos aún no tienes la edad suficiente para utilizar Psyche");
                    edad.requestFocus();
                    return;
                }
                if ( mail.getText().length() == 0){
                    mail.setError("Campo de mail vacio");
                    mail.requestFocus();
                    return;
                }
                if (user.getText().length() == 0){
                    user.setError("Campo de usuario vacio");
                    user.requestFocus();
                    return;
                }
                if (contra.getText().length() == 0){
                    contra.setError("Campo de contraseña vacio");
                    contra.requestFocus();
                    return;
                }
                if (!PatternsCompat.EMAIL_ADDRESS.matcher(mail.getText()).matches()) {
                    mail.setError("Error de sintaxis en el mail");
                    mail.requestFocus();
                    return;
                }
                if (valuser(lista, usr)){
                    user.setError("El nombre de usuario no esta disponible");
                    user.requestFocus();
                    return;
                }

                boolean id = bdUser.EditarU(us, String.valueOf(name.getText()), String.valueOf(edad.getText()), String.valueOf(mail.getText()), nuevous, pass);
                if(id){
                    Toast.makeText(getApplicationContext(), "Datos Editados", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Se cerrará la sesión para visualizar los cambios", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InicioA.this, Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error al modificar, usuario no disponible", Toast.LENGTH_LONG).show();
                }
                dialog.cancel();

            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
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