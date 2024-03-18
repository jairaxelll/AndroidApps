package com.example.aplicaciones; // Replace with your actual package name

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class Producto {
    private String nombre;
    private String descripcion;
    private String urlCompra;

    public Producto(String nombre, String descripcion, String urlCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlCompra = urlCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlCompra() {
        return urlCompra;
    }
}