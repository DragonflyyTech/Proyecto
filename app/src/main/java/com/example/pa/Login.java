package com.example.pa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pa.Base.BDUser;
import com.example.pa.Base.BDEspe;
import com.example.pa.Especialista.InicioB;
import com.example.pa.Paciente.Info2;
import com.example.pa.Paciente.Registro;
import com.example.pa.Especialista.Info;
import com.example.pa.Paciente.InicioA;
import com.example.pa.Especialista.RegistroA;

import java.util.List;

public class Login extends AppCompatActivity {

    public static List<Info> list;
    public static List<Info2> lista;
    public static String TAG = "mensaje";
    public static String json = null;
    public static String usuario;
    public static String pass;
    public static String usuario2;
    public static String pass2;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonreg = findViewById(R.id.crearbtn);
        Button buttonin = findViewById(R.id.loginbtn);
        Button olvide = findViewById(R.id.olvidebtn);
        EditText user = findViewById(R.id.userlogin);
        EditText contra = findViewById(R.id.pswlogin);

        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elige();
            }
        });
        olvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this, Olvide.class);
                startActivity(intent);
                finish();
            }
        });
        buttonin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = String.valueOf(user.getText());
                pass = SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText())));
                Entrar(usuario, pass);
            }
        });
    }
    public void Entrar(String usuario, String pass){
        Boolean ingresar = Boolean.FALSE;

        BDEspe dbUsers = new BDEspe(Login.this);
        BDUser bdUser = new BDUser(Login.this);
        Info info = dbUsers.GetUsuario(usuario);
        Info2 info2 = bdUser.GetEspecialista(usuario);

        if(info!=null) {
            if (info.getContra().equals(pass)) {
                Intent intent = new Intent(Login.this, InicioB.class);
                intent.putExtra("UserE", usuario);
                startActivity(intent);
                ingresar = Boolean.TRUE;
            }
        }
            if (info2!=null){
                if (info2.getContra().equals(pass)){
                    Intent intent = new Intent(Login.this, InicioA.class);
                    //intent.putExtra("Info2", info2);
                    intent.putExtra("User", usuario);
                    startActivity(intent);
                    ingresar = Boolean.TRUE;
                }
            }

            if (ingresar == Boolean.FALSE) {
                Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }



    }

    public void elige(){

        dialogBuilder = new AlertDialog.Builder(this);
        final View Popview = getLayoutInflater().inflate(R.layout.popelige, null);

        Button btnesp = Popview.findViewById(R.id.btnEsp);
        Button btnpac = Popview.findViewById(R.id.btnPac);

        dialogBuilder.setView(Popview);
        dialog = dialogBuilder.create();
        dialog.show();

        btnesp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegistroA.class);
                startActivity(intent);
                finish();
                dialog.cancel();
            }

        });

        btnpac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
                finish();
                dialog.cancel();
            }
        });

    }
}