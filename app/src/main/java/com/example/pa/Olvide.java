package com.example.pa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pa.Base.BDEspe;
import com.example.pa.Base.BDUser;
import com.example.pa.Paciente.Info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class Olvide extends AppCompatActivity {

    public static List<Info> list;
    public static List<Info2> lista;
    public static String TAG = "Hola";
    public static String TOG = "Error";
    public String usuario = null;
    public String correo,msj,pass, nuevapass,nueva2;

    public static final String KEY = "+4xij6jQRSBdCymMxweza/uMYo+o0EUg";
    public MyDesUtil myDesUtil= new MyDesUtil().addStringKeyBase64(KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnRecupera = findViewById(R.id.Recibir);
        EditText user = findViewById(R.id.olvideuser);
        EditText mail = findViewById(R.id.olvidemail);

        list = Login.list;
        lista = Login.lista;

        btnRecupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = String.valueOf(user.getText());
                correo = String.valueOf(mail.getText());
                BDUser bdUsers = new BDUser(Olvide.this);
                BDEspe bdEspe = new BDEspe(Olvide.this);
                Info2 user = bdUsers.GetEspecialista(usuario, correo);
                Info espe = bdEspe.GetUsuario(usuario, correo);

                if (user!= null){
                    if(usuario.length()==0 || correo.length()==0){
                        Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                    }else {
                        if (user == null) {
                            Toast.makeText(getApplicationContext(), "El usuario o correo no existen", Toast.LENGTH_LONG).show();
                        } else {
                            correo = user.getMail();
                            pass = user.getContra();
                            //nuevapass = String.format("%d", (int) (Math.random() * 1000));
                            nuevapass = "";
                            for (int i = 0; i < 5; i++){
                                Random random = new Random();
                                char ch = (char) (random.nextInt(26) + 'a');
                                nuevapass += String.valueOf(ch);
                            }
                            nuevapass += String.format("%d",(int)(Math.random()*1000));
                            nueva2 = SHA.bytesToHex(SHA.createSha1(nuevapass));
                            msj = "<!DOCTYPE html>\n" +
                                    "<html lang=\"en\">\n" +
                                    "\n" +
                                    "<head>\n" +
                                    "    <meta charset=\"UTF-8\">\n" +
                                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                    "    <title>Olvide Contraseña</title>\n" +
                                    "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                                    "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                                    "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap\" rel=\"stylesheet\">\n" +
                                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\n" +
                                    "    <style>\n" +
                                    "        body{\n" +
                                    "            font-family: 'Montserrat', sans-serif;\n" +
                                    "            align-content: center;\n" +
                                    "        }\n" +
                                    "        img{\n" +
                                    "            width: 350px;\n" +
                                    "        }\n" +
                                    "        .centrado{\n" +
                                    "            display:flex;\n" +
                                    "            justify-content: center;\n" +
                                    "            align-items: center;\n" +
                                    "        }\n" +
                                    "        \n" +
                                    "    </style>\n" +
                                    "</head>\n" +
                                    "<body>\n" +
                                    "    <div class=\"container centrado\">\n" +
                                    "        <div class=\"p-4\">\n" +
                                    "            <div class=\"p-4 centrado\" style=\"background: linear-gradient(50deg, #00B1D9,#049DBF,#005D98); border-radius: 15px;\">\n" +
                                    "                <h2 style=\"font-weight: 800; color: white;\">Recupera tu contraseña</h2>\n" +
                                    "            </div>\n" +
                                    "            \n" +
                                    "            <hr>\n" +
                                    "            <img src=\"https://www.puppies.com.au/uploads/1/0/5/8/105867835/corgi-cat-500-500px_orig.png\" alt=\"Perrito\">\n" +
                                    "            <div>\n" +
                                    "                <p style=\"font-size: 30px;\" class=\"centrado\">Tu nueva contraseña es:</p>\n" +
                                    "                <p style=\"font-size: 30px; font-weight: 800;\" class=\"centrado\"> " + nuevapass + "</p>\n" +
                                    "        </div>\n" +
                                    "\n" +
                                    "        </div>\n" +
                                    "        \n" +
                                    "    </div>\n" +
                                    "</html>";
                            correo = myDesUtil.cifrar(correo);
                            msj = myDesUtil.cifrar(msj);
                            boolean f = bdUsers.EditEspecialista(usuario, nueva2);
                            if(f){
                                if(Enviar(correo,msj))
                                {
                                    Toast.makeText(getApplicationContext(), "Contraseña Enviada", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Olvide.this, Login.class);
                                    startActivity(intent);
                                }
                                else{Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();}

                            }else{
                                Toast.makeText(getApplicationContext(), "Error al enviar correo", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
                else {
                    if(espe!=null){
                        if(usuario.length()==0 || correo.length()==0){
                            Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                        }else {
                            if (espe == null) {
                                Toast.makeText(getApplicationContext(), "El usuario o correo no existen", Toast.LENGTH_LONG).show();
                            } else {
                                correo = espe.getMail();
                                pass = espe.getContra();
                                //nuevapass = String.format("%d", (int) (Math.random() * 1000));
                                nuevapass = "";
                                for (int i = 0; i < 5; i++){
                                    Random random = new Random();
                                    char ch = (char) (random.nextInt(26) + 'a');
                                    nuevapass += String.valueOf(ch);
                                }
                                nuevapass += String.format("%d",(int)(Math.random()*1000));
                                nueva2 = SHA.bytesToHex(SHA.createSha1(nuevapass));
                                msj = "<!DOCTYPE html>\n" +
                                        "<html lang=\"en\">\n" +
                                        "\n" +
                                        "<head>\n" +
                                        "    <meta charset=\"UTF-8\">\n" +
                                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                        "    <title>Olvide Contraseña</title>\n" +
                                        "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                                        "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                                        "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap\" rel=\"stylesheet\">\n" +
                                        "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD\" crossorigin=\"anonymous\">\n" +
                                        "    <style>\n" +
                                        "        body{\n" +
                                        "            font-family: 'Montserrat', sans-serif;\n" +
                                        "            align-content: center;\n" +
                                        "        }\n" +
                                        "        img{\n" +
                                        "            width: 350px;\n" +
                                        "        }\n" +
                                        "        .centrado{\n" +
                                        "            display:flex;\n" +
                                        "            justify-content: center;\n" +
                                        "            align-items: center;\n" +
                                        "        }\n" +
                                        "        \n" +
                                        "    </style>\n" +
                                        "</head>\n" +
                                        "<body>\n" +
                                        "    <div class=\"container centrado\">\n" +
                                        "        <div class=\"p-4\">\n" +
                                        "            <div class=\"p-4 centrado\" style=\"background: linear-gradient(50deg, #00B1D9,#049DBF,#005D98); border-radius: 15px;\">\n" +
                                        "                <h2 style=\"font-weight: 800; color: white;\">Recupera tu contraseña</h2>\n" +
                                        "            </div>\n" +
                                        "            \n" +
                                        "            <hr>\n" +
                                        "            <img src=\"https://www.puppies.com.au/uploads/1/0/5/8/105867835/corgi-cat-500-500px_orig.png\" alt=\"Perrito\">\n" +
                                        "            <div>\n" +
                                        "                <p style=\"font-size: 30px;\" class=\"centrado\">Tu nueva contraseña es:</p>\n" +
                                        "                <p style=\"font-size: 30px; font-weight: 800;\" class=\"centrado\"> " + nuevapass + "</p>\n" +
                                        "        </div>\n" +
                                        "\n" +
                                        "        </div>\n" +
                                        "        \n" +
                                        "    </div>\n" +
                                        "</html>";
                                correo = myDesUtil.cifrar(correo);
                                msj = myDesUtil.cifrar(msj);
                                boolean f = bdEspe.EditUser(usuario, nueva2);
                                if(f){
                                    if(Enviar(correo,msj))
                                    {
                                        Toast.makeText(getApplicationContext(), "Contraseña Enviada", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Olvide.this, Login.class);
                                        startActivity(intent);
                                    }
                                    else{Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();}

                                }else{
                                    Toast.makeText(getApplicationContext(), "Error al enviar correo", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                    }
                }


            }
        });
    }
    public boolean Enviar( String correo ,String msj)
    {
        JsonObjectRequest jsonObjectRequest = null;
        JSONObject jsonObject = null;
        String url = "https://us-central1-nemidesarrollo.cloudfunctions.net/envio_correo";
        RequestQueue requestQueue = null;
        if( correo == null || correo.length() == 0 )
        {
            return false;
        }
        jsonObject = new JSONObject( );
        try
        {
            jsonObject.put("correo" , correo );
            jsonObject.put("mensaje", msj);
            String obj = jsonObject.toString();
            Log.i(TAG, obj);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.i(TAG, response.toString());
            }
        } , new  Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e  (TAG, error.toString());
            }
        } );
        requestQueue = Volley.newRequestQueue( getBaseContext() );
        requestQueue.add(jsonObjectRequest);

        return true;
    }
}