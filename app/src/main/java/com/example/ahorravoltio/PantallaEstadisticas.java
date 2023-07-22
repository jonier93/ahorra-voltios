package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PantallaEstadisticas extends AppCompatActivity {
    private Spinner categoria;
    private Button btnConsult;
    private TextView tabla[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);
        inicializar();
        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoria.getSelectedItem().equals("Agua")) {
                    llenar_tabla();
                }
                else {

                }
            }
        });

    }
    private void inicializar(){
        categoria = findViewById(R.id.spCategoria);
        String arrayData [] = {"Agua", "Electricidad"};
        ArrayAdapter<String> arrayCategoria = new ArrayAdapter<>(PantallaEstadisticas.this,
                android.R.layout.simple_spinner_item, arrayData);
        categoria.setAdapter(arrayCategoria);
        btnConsult = findViewById(R.id.btnConsult);
        int filas = 4;
        int columnas = 3;
        tabla = new TextView[filas][columnas];
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                String txv_name = "txv"+i+j;
                int txv_id = getResources().getIdentifier(txv_name, "id", getPackageName());
                tabla[i][j] = findViewById(txv_id);
            }
        }
    }
    private void llenar_tabla(){
        tabla[0][0].setText("Hola");
        tabla[0][1].setText("Jonier");
        tabla[0][2].setText("Porras");
    }
}
