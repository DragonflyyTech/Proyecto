package com.example.pa.Especialista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.pa.Base.BDEspe;
import com.example.pa.Info2;
import com.example.pa.Login;
import com.example.pa.Paciente.Info;
import com.example.pa.R;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class InicioB extends AppCompatActivity{

    String aux = null;
    Info info = null;
    TextView textView;
    Object object = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_b);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button citas = findViewById(R.id.btncitasE);
        Button chatE = findViewById(R.id.btnchatE);
        Button expediente = findViewById(R.id.btnexpediente);

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


        citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioB.this, CitasE.class);
                startActivity(intent);
                finish();
            }
        });

        chatE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioB.this, ChatE.class);
                startActivity(intent);
                finish();
            }
        });

        expediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InicioB.this, Expediente.class);
                startActivity(intent);
                finish();
            }
        });

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
                object = intent.getExtras().get("Info2");
                if (object != null) {
                    if (object instanceof Info) {
                        info = (Info) object;
                        textView.setText("Bienvenido  " + info.getUsuario());
                    }
                }
            }
        }

    }
}