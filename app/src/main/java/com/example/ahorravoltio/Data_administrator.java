package com.example.ahorravoltio;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
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
    private ModelUsuario objUsuario;
    private int tipoUsuario;


    public Data_administrator(File file){
        this.file = file;
    }

    public Data_administrator(File file, ModelUsuario objUsuario){
        this.file = file;
        this.objUsuario = objUsuario;
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
            if(!file.exists()) {
                Log.i("MyTag","El archivo no existe");
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                if (tipoUsuario == 0) {
                    bufferedWriter.write("[{'name':'"+objUsuario.getName()+"', " +
                            "'correo':'"+objUsuario.getCorreo()+"', " +
                            "'usuario': '"+objUsuario.getUsuario()+"', " +
                            "'password':'"+objUsuario.getPassword()+"'}]");
                }
                else if (tipoUsuario == 1) {
                    bufferedWriter.write("[{'volumen':'"+objAgua.getVolumen()+"', 'precio':'"
                            +objAgua.getPrecio()+"', 'mes':'"+objAgua.getMes()+"'}]");
                }
                else if (tipoUsuario == 2){
                    bufferedWriter.write("[{'kilovatios':"+objElectricidad.getKilovatio()+"," +
                            " 'precio':"+objElectricidad.getPrecio()+", " +
                            "'mes':'"+objElectricidad.getMes()+"'}]");
                }
                bufferedWriter.close();
            }
            else {
                Log.i("MyTag", "El archivo ya existe");
                JSONArray jsonData = readData();
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                if (tipoUsuario == 0) {
                    String stringData = jsonData.toString();
                    String stringDataProcess = stringData.substring(1,stringData.length()-1);
                    String newUser = "{'name':'"+objUsuario.getName()+"', " +
                            "'correo':'"+objUsuario.getCorreo()+"', " +
                            "'usuario': '"+objUsuario.getUsuario()+"', " +
                            "'password':'"+objUsuario.getPassword()+"'}";
                    bufferedWriter.write("[" +stringDataProcess + ", " + newUser + "]");
                }
                else if (tipoUsuario == 1) {
                    String stringData = jsonData.toString();
                    String stringData_process = stringData.substring(1, stringData.length()-1);
                    String newAgua = "{'volumen':'"+objAgua.getVolumen()+"', 'precio':'"
                            +objAgua.getPrecio()+"', 'mes':'"+objAgua.getMes()+"'}";
                    bufferedWriter.write("["+ stringData_process + ", " + newAgua +"]");
                }
                else if (tipoUsuario == 2){
                    String stringData = jsonData.toString();
                    String stringData_process = stringData.substring(1, stringData.length()-1);
                    String newElectricidad = "{'kilovatios':"+ objElectricidad.getKilovatio()+", " +
                            "'precio':"+objElectricidad.getPrecio()+", 'mes':'"+objElectricidad.getMes()+"'}";
                    bufferedWriter.write("["+stringData_process + ", " + newElectricidad+"]");
                }
                bufferedWriter.close();
            }
        }

        catch(Exception ex){
            Log.e("MyTag", ex.toString());
        }
    }
    public JSONArray readData(){
        try{
            FileReader fileReader = new FileReader(file.getAbsoluteFile());
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line = bufferReader.readLine();
            bufferReader.close();
            JSONArray dataJson = new JSONArray(line);
            return dataJson;
        }
        catch(Exception ex){
            Log.e("MyTag", ex.toString());
            return null;
        }
    }
}
