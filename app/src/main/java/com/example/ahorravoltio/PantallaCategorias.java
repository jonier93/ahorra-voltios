package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaCategorias extends AppCompatActivity {
    private Button btnAgua;
    private Button btnElectricidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_categorias);
        inicializar();

        btnAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgua = new Intent(PantallaCategorias.this, PantallaAgua.class);
                startActivity(intentAgua);
            }
        });

        btnElectricidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentElectricidad = new Intent(PantallaCategorias.this, PantallaElectricidad.class);
                startActivity(intentElectricidad);
            }
        });
    }

    private void inicializar(){
        btnAgua = (Button) findViewById(R.id.btnAgua);
        btnElectricidad = findViewById(R.id.btnElectricidad);
    }
}