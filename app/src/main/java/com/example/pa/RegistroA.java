package com.example.pa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

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
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RegistroA extends AppCompatActivity {

    private Button btnregr;
    private EditText nombre;
    private EditText edad;
    private EditText email;
    private EditText user;
    private EditText contra;
    public static final String archivo = "registro.json";
    private static final String TAG = "Registro";

    Info info = null;
    Gson gson = null;
    String json = null;
    String usr = null;
    String mail = null;
    String mensaje = null;
    List<Info> list = new ArrayList<Info>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro2);

        Button buttonregre = findViewById(R.id.btn2regresar);
        buttonregre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroA.this, EligeUser.class);
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
        Leer();
        json2List(json);

        btnregr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info = new Info();
                info.setNombre(String.valueOf(nombre.getText()));
                info.setEdad(String.valueOf(edad.getText()));
                info.setMail(String.valueOf(email.getText()));
                info.setUsuario(String.valueOf(user.getText()));
                info.setContra(SHA.bytesToHex(SHA.createSha1(String.valueOf(contra.getText()))));
                usr = String.valueOf(user.getText());
                mail = String.valueOf(email.getText());

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
                if (valuser(list, usr)){
                    Toast.makeText( getApplicationContext() , "El nombre de usuraio no está disponibe" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if(!PatternsCompat.EMAIL_ADDRESS.matcher(mail).matches()){
                    Toast.makeText( getApplicationContext() , "Error de sintaxis en el mail" , Toast.LENGTH_LONG ).show();
                    return;
                }

                list2Json(info, list);
            }
        });
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
    public void object2Json(Info info) {

        Gson gson = null;
        String json = null;
        String mensaje = null;

        gson = new Gson();
        json = gson.toJson(info);
        if (json != null) {
            Log.d(TAG, json);
            mensaje = "object2Json OK";
        } else {
            mensaje = "Error object2Json";
        }
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }
    public void list2Json(Info info,List<Info> list )
    {
        Gson gson = null;
        String json = null;

        gson = new Gson();
        list.add(info);
        json = gson.toJson(list, ArrayList.class);
        if (json == null)
        {
            Log.d(TAG, "Error json");
        }
        else
        {
            Log.d(TAG, json);
            writeFile(json);
        }
        Toast.makeText(getApplicationContext(), "Registro Exitoso", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegistroA.this, Login.class);
        startActivity(intent);
    }
    private boolean writeFile(String text)
    {
        File file = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            file = getFile();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public boolean valuser(List<Info>list, String user){
        Boolean u = Boolean.FALSE;
        for (Info info1 : list){
            if(info1.getUsuario().equals(user)){
                u = Boolean.TRUE;
            }
        }
        return u;
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