package com.example.pa.Paciente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.pa.Base.BDService;
import com.example.pa.Base.BDUsers;
import com.example.pa.R;
import com.example.pa.Test;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class InicioA extends AppCompatActivity {

    String aux = null;
    //Info info = null;

    public static Info info = null;
    TextView textView;
    Object object = null;

    private List<Info> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttondiario = findViewById(R.id.btndiario);
        Button buttoncitas = findViewById(R.id.btncitasE);
        Button buttontest = findViewById(R.id.btnexpediente);
        Button buttonchatap = findViewById(R.id.btnchatE);

        ImageCarousel carousel = findViewById(R.id.carousel);
        //carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();

        list.add(
                new CarouselItem(
                        R.drawable.a
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.b
                )
        );
        list.add(
                new CarouselItem(
                        R.drawable.c
                )
        );
        carousel.setData(list);



        textView = findViewById(R.id.textViewusr);

        Intent intent = getIntent();
        if( intent != null)
        {
            aux = intent.getStringExtra("Usuario" );
            if( aux != null && aux.length()> 0 )
            {
                textView.setText(aux);
            }
            if( intent.getExtras() != null ) {
                object = intent.getExtras().get("Info");
                if (object != null) {
                    if (object instanceof Info) {
                        info = (Info) object;
                        textView.setText("Bienvenido  " + info.getUsuario());
                    }
                }
            }
        }

        buttondiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Diario.class);
                startActivity(intent);
            }
        });

        buttoncitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, CitasP.class);
                startActivity(intent);
            }
        });
        buttontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, Test.class);
                startActivity(intent);
            }
        });
        buttonchatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioA.this, ChatP.class);
                startActivity(intent);
            }
        });
    }
}