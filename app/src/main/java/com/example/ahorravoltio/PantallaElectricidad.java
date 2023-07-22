package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PantallaElectricidad extends AppCompatActivity {
    private EditText kilovatio;
    private EditText precio;
    private Spinner mes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_electricidad);
        inicializar();
    }

    private void inicializar(){
        kilovatio = findViewById(R.id.txtKilovatio);
        precio = findViewById(R.id.txtPrecioEnergia);
        mes = findViewById(R.id.optMesEnergia);
        String arrayMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter spiner_mes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayMes);
        mes.setAdapter(spiner_mes);
    }
}