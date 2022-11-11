package com.example.pa;
import static com.example.pa.Registro.archivo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private List<Info> list;
    public static String TAG = "mensaje";
    public static String json = null;
    public static String usuario;
    public static String pass;
    public static String usuario2;
    public static String pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonreg = findViewById(R.id.crearbtn);
        Button buttonin = findViewById(R.id.loginbtn);
        Button button2in = findViewById(R.id.login2btn);
        EditText user = findViewById(R.id.userlogin);
        EditText contra = findViewById(R.id.pswlogin);
        Leer();
        json2List(json);

        buttonreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, EligeUser.class);
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
        button2in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario2 = String.valueOf(user.getText());
                pass2 = SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText())));
                Entrar2(usuario2, pass2);
            }
        });
    }
    public void Entrar(String usuario, String pass){
        Boolean ingresar = Boolean.FALSE;
        for (Info info : list){
            if(info.getUsuario().equals(usuario) && info.getContra().equals(pass)){
                Intent intent = new Intent(Login.this, InicioA.class);
                intent.putExtra("Info", info);
                startActivity(intent);
                ingresar = Boolean.TRUE;
            }
        }
        if (ingresar==Boolean.FALSE){
            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }
    public void Entrar2(String usuario2, String pass2){
        Boolean ingresar = Boolean.FALSE;
        for (Info info : list){
            if(info.getUsuario().equals(usuario) && info.getContra().equals(pass)){
                Intent intent = new Intent(Login.this, InicioB.class);
                intent.putExtra("Info", info);
                startActivity(intent);
                ingresar = Boolean.TRUE;
            }
        }
        if (ingresar==Boolean.FALSE){
            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
        }
    }
    public boolean Leer(){
        if(!isFileExits()){
            return false;
        }
        File file = getFile();
        FileInputStream fileInputStream = null;
        byte[] bytes = null;
        bytes = new byte[(int)file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            json=new String(bytes);
            Log.d(TAG,json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void json2List( String json )
    {

        Gson gson = null;
        String mensaje = null;
        if (json == null || json.length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Error json null or empty", Toast.LENGTH_LONG).show();
            return;
        }
        gson = new Gson();
        Type listType = new TypeToken<ArrayList<Info>>(){}.getType();
        list = gson.fromJson(json, listType);
        if (list == null || list.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "Error list is null or empty", Toast.LENGTH_LONG).show();
            return;
        }
    }

    private File getFile( )
    {
        return new File( getDataDir() , archivo );
    }

    private boolean isFileExits( )
    {
        File file = getFile( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }
}