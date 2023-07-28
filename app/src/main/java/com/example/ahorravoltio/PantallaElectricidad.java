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

public class PantallaElectricidad extends AppCompatActivity {
    private EditText kilovatio;
    private EditText precio;
    private Spinner mes;
    private Button btnRegistrar;
    private ModelElectricidad objElectricidad;
    private Data_administrator objData;
    private File objFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_electricidad);
        inicializar();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    establecer_datos();
                    objData = new Data_administrator(objFile, objElectricidad);
                    objData.saveData();
                    Toast.makeText(PantallaElectricidad.this, "Dato registrado", Toast.LENGTH_SHORT).show();
                }
                catch(Exception ex) {
                    Toast.makeText(PantallaElectricidad.this, "Todos los campos deben ser diligenciados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inicializar(){
        kilovatio = findViewById(R.id.txtKilovatio);
        precio = findViewById(R.id.txtPrecioEnergia);
        mes = findViewById(R.id.optMesEnergia);
        String arrayMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        ArrayAdapter spiner_mes = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayMes);
        mes.setAdapter(spiner_mes);
        btnRegistrar = findViewById(R.id.btnRegistrarElect);
        objElectricidad = new ModelElectricidad();
        objFile = new File(getFilesDir(), "datosElectricidad.json");
    }

    private void establecer_datos(){
        objElectricidad.setPrecio(Double.parseDouble(precio.getText().toString()));
        objElectricidad.setKilovatio(Double.parseDouble(kilovatio.getText().toString()));
        objElectricidad.setMes(mes.getSelectedItem().toString());
    }
}