package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private EditText correo;
    private EditText password;
    private Button btnLogear;
    private Data_administrator objData;
    private File file;
    private JSONObject dataJson;
    private ModelUsuario objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        inicializar();

        btnLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objData.saveData();
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
    }

    private void inicializar(){
        correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtPassword);
        btnLogear = findViewById(R.id.btnLogear);
        file = new File(this.getFilesDir(), "usuarios.json");
        objData = new Data_administrator(file);
        dataJson = new JSONObject();
        objUsuario = new ModelUsuario();
    }

    private boolean validar(){
        try {
            if (objUsuario.getCorreo().equals(dataJson.getString("correo")) &&
                    objUsuario.getPassword().equals(dataJson.getString("password"))) {
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