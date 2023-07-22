package com.example.ahorravoltio;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Data_administrator {
    private File file;
    private ModelAgua objAgua;
    private ModelElectricidad objElectricidad;
    private int tipoUsuario;


    public Data_administrator(File file){
        this.file = file;
        tipoUsuario = 0;
    }

    public Data_administrator(File file, ModelAgua objAgua){
        this.file = file;
        this.objAgua = objAgua;
        tipoUsuario = 1;
    }
    public Data_administrator(File file, ModelElectricidad objElectricidad){
        this.file = file;
        this.objElectricidad = objElectricidad;
        tipoUsuario = 2;
    }

    public void saveData(){
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (tipoUsuario == 0) {
                bufferedWriter.write("{'correo':'jonier@gmail.com', 'password':'12345'}");
            }
            if (tipoUsuario == 1) {
                bufferedWriter.write("{'volumen':'"+objAgua.getVolumen()+"', 'precio':'"
                        +objAgua.getPrecio()+"', 'mes':'"+objAgua.getMes()+"'}");
            }
            bufferedWriter.close();
        }

        catch(Exception ex){
            Log.e("MyTag", ex.toString());
        }
    }
    public JSONObject readData(){
        try{
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String data = "";
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                data = line + "\n";
            }
            bufferReader.close();
            JSONObject dataJson = new JSONObject(data);
            return dataJson;
        }
        catch(Exception ex){
            Log.e("MyTag", ex.toString());
            return null;
        }
    }
}
