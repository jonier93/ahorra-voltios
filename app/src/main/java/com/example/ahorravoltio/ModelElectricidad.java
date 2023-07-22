package com.example.ahorravoltio;

import android.widget.EditText;
import android.widget.Spinner;

public class ModelElectricidad {
    private double kilovatio;
    private double precio;
    private String mes;

    public double getKilovatio() {
        return kilovatio;
    }

    public void setKilovatio(double kilovatio) {
        this.kilovatio = kilovatio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
