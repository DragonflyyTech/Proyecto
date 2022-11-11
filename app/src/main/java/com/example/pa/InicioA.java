package com.example.pa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class InicioA extends AppCompatActivity {

    String aux = null;
    Info info = null;
    TextView textView;
    Object object = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

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