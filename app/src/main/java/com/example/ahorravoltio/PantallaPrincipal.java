package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PantallaPrincipal extends AppCompatActivity {
    private ImageButton btnEstadisticas;
    private ImageButton btnCategorias;
    private ImageButton btnConsejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        inicializar();

        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCategorias = new Intent(PantallaPrincipal.this, PantallaCategorias.class);
                startActivity(intentCategorias);
            }
        });

        btnEstadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEstadisticas = new Intent(PantallaPrincipal.this, PantallaEstadisticas.class);
                startActivity(intentEstadisticas);
            }
        });
    }

    private void inicializar(){
        btnEstadisticas = findViewById(R.id.btnEstadisticas);
        btnCategorias = findViewById(R.id.btnCategorias);
        btnConsejos = findViewById(R.id.btnConsejos);
    }
}