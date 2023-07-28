package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class PantallaEstadisticas extends AppCompatActivity {
    private Spinner categoria;
    private Button btnConsult;
    private TextView tabla[][];
    private Data_administrator objData;
    private File objFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_estadisticas);
        inicializar();
        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(categoria.getSelectedItem().equals("Agua")) {
                    objFile = new File(getFilesDir(), "datosAgua.json");
                    objData = new Data_administrator(objFile);
                    JSONArray jsonData = objData.readData();
                    llenar_tabla(jsonData, 1);
                }
                else {
                    objFile = new File(getFilesDir(), "datosElectricidad.json");
                    objData = new Data_administrator(objFile);
                    JSONArray jsonData = objData.readData();
                    llenar_tabla(jsonData, 2);
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
        tabla[0][0].setText("Mes");
        tabla[0][1].setText("Consumo");
        tabla[0][2].setText("Precio");
    }
    private void llenar_tabla(JSONArray jsonData, int tipoElemento){
        try{
            if (tipoElemento == 1) {
                for (int i = 0; i < jsonData.length(); i++) {
                    Log.i("MyTag", jsonData.length() + "");
                    JSONObject objJson = jsonData.getJSONObject(i);
                    tabla[i + 1][0].setText(objJson.getString("mes"));
                    tabla[i + 1][1].setText(String.valueOf(objJson.getDouble("volumen")));
                    tabla[i + 1][2].setText(String.valueOf(objJson.getDouble("precio")));
                }
            }
            else {
                for (int i = 0; i < jsonData.length(); i++) {
                    Log.i("MyTag", jsonData.length() + "");
                    JSONObject objJson = jsonData.getJSONObject(i);
                    tabla[i + 1][0].setText(objJson.getString("mes"));
                    tabla[i + 1][1].setText(String.valueOf(objJson.getDouble("kilovatios")));
                    tabla[i + 1][2].setText(String.valueOf(objJson.getDouble("precio")));
                }
            }
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
        }
    }
}
