package com.example.pa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class InicioA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button buttondiario = findViewById(R.id.btndiario);
        Button buttoncitas = findViewById(R.id.btncitas);

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

    }
}