package com.example.ahorravoltio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class PantallaRegistro extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText usuario;
    private EditText password;
    private Button btnRegistrar;
    private CheckBox checkRegistrar;
    private ModelUsuario objUsuario;
    private Data_administrator objData;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);
        inicializar();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRegistrar.isChecked()) {
                    establecer_usuario();
                    objData = new Data_administrator(file, objUsuario);
                    objData.saveData();
                    Toast.makeText(PantallaRegistro.this, "Usuario creado", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(PantallaRegistro.this, "Debe primero aceptar los terminos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void inicializar(){
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmailUser);
        usuario = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtPasswordUser);
        btnRegistrar = findViewById(R.id.btnRegistrarUser);
        checkRegistrar = findViewById(R.id.checkRegistrar);
        objUsuario = new ModelUsuario();
        file = new File(getFilesDir(), "usuarios.json");
    }

    private void establecer_usuario(){
        objUsuario.setName(name.getText().toString());
        objUsuario.setCorreo(email.getText().toString());
        objUsuario.setUsuario(usuario.getText().toString());
        objUsuario.setPassword(password.getText().toString());
    }
}