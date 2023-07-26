package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private EditText correo;
    private EditText password;
    private Button btnLogear;
    private TextView registrarse;
    private Data_administrator objData;
    private File file;
    private JSONArray dataJson;
    private ModelUsuario objUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        inicializar();

        btnLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataJson = objData.readData();
                establecer_datos();
                boolean confirm = validar();
                if (confirm) {
                    Intent intentPrincipal = new Intent(MainActivity.this,
                            PantallaPrincipal.class);
                    startActivity(intentPrincipal);
                }
                else {
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegistrar = new Intent(MainActivity.this, PantallaRegistro.class);
                startActivity(intentRegistrar);
            }
        });
    }

    private void inicializar(){
        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtPassword);
        btnLogear = findViewById(R.id.btnLogear);
        registrarse = findViewById(R.id.txvRegistrarse);
        file = new File(this.getFilesDir(), "usuarios.json");
        objData = new Data_administrator(file);
        dataJson = new JSONArray();
        objUsuario = new ModelUsuario();
    }

    private boolean validar(){
        try {
            JSONObject objJson = dataJson.getJSONObject(0);
            if (objUsuario.getCorreo().equals(objJson.getString("correo")) &&
                    objUsuario.getPassword().equals(objJson.getString("password"))) {
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception ex){
            Log.e("MyTag", ex.toString());
            return false;
        }
    }

    private void establecer_datos(){
        objUsuario.setCorreo(correo.getText().toString());
        objUsuario.setPassword(password.getText().toString());

    }
}