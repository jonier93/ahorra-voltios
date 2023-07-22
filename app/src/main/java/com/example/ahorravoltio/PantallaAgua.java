package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class PantallaAgua extends AppCompatActivity {
    private EditText volumen;
    private EditText precio;
    private Spinner mes;

    private ModelAgua objAgua;

    private Button btnRegister;
    private Data_administrator objData;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_agua);
        inicializar();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                establecer_datos();
                objData = new Data_administrator(file, objAgua);
                objData.saveData();
                Toast.makeText(PantallaAgua.this, "Dato registrado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void inicializar(){
        volumen = findViewById(R.id.txtVolumen);
        precio = findViewById(R.id.txtPrecioAgua);
        mes = findViewById(R.id.optMesAgua);
        String arrayMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
        "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter spiner_mes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayMes);
        mes.setAdapter(spiner_mes);
        btnRegister = findViewById(R.id.btnRegistrarAgua);
        objAgua = new ModelAgua();
        file = new File(getFilesDir(), "datosAgua.json");
    }
    private void establecer_datos(){
        objAgua.setVolumen(Double.parseDouble(volumen.getText().toString()));
        objAgua.setPrecio(Double.parseDouble(precio.getText().toString()));
        objAgua.setMes(mes.getSelectedItem().toString());
    }
}