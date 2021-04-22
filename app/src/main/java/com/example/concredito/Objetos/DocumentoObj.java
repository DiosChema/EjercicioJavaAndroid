package com.example.concredito.Objetos;

import android.net.Uri;

import java.io.File;

public class DocumentoObj
{
    String nombre;
    String ruta;

    public DocumentoObj(String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
