package com.example.concredito.Objetos;

public class ProspectosObj {
    private int _id;
    private String Nombre, ApellidoP, ApellidoM;
    private short Estatus;

    public ProspectosObj(int _id, String nombre, String apellidoP, String apellidoM, short estatus) {
        this._id = _id;
        Nombre = nombre;
        ApellidoP = apellidoP;
        ApellidoM = apellidoM;
        Estatus = estatus;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidoP() {
        return ApellidoP;
    }

    public void setApellidoP(String apellidoP) {
        ApellidoP = apellidoP;
    }

    public String getApellidoM() {
        return ApellidoM;
    }

    public void setApellidoM(String apellidoM) {
        ApellidoM = apellidoM;
    }

    public short getEstatus() {
        return Estatus;
    }

    public void setEstatus(short estatus) {
        Estatus = estatus;
    }
}
