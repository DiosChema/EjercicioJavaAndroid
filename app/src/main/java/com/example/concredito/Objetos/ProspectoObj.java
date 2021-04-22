package com.example.concredito.Objetos;

import java.util.ArrayList;

public class ProspectoObj {
    int _id, CodigoPostal, Numero;
    long Telefono;
    short Estatus;
    String Nombre, ApellidoP, ApellidoM, Calle, Colonia, RFC, ObservacionRechazo;
    ArrayList<DocumentoObj> Documentos;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        CodigoPostal = codigoPostal;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public long getTelefono() {
        return Telefono;
    }

    public void setTelefono(long telefono) {
        Telefono = telefono;
    }

    public short getEstatus() {
        return Estatus;
    }

    public void setEstatus(short estatus) {
        Estatus = estatus;
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

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getObservacionRechazo() {
        return ObservacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        ObservacionRechazo = observacionRechazo;
    }

    public ArrayList<DocumentoObj> getDocumentos() {
        return Documentos;
    }

    public void setDocumentos(ArrayList<DocumentoObj> documentos) {
        this.Documentos = documentos;
    }

    public ProspectoObj(int _id, int codigoPostal, int numero, long telefono, short estatus, String nombre, String apellidoP, String apellidoM, String calle, String colonia, String RFC, String observacionRechazo, ArrayList<DocumentoObj> documento) {
        this._id = _id;
        CodigoPostal = codigoPostal;
        Numero = numero;
        Telefono = telefono;
        Estatus = estatus;
        Nombre = nombre;
        ApellidoP = apellidoP;
        ApellidoM = apellidoM;
        Calle = calle;
        Colonia = colonia;
        this.RFC = RFC;
        ObservacionRechazo = observacionRechazo;
        this.Documentos = documento;
    }
}
