package com.example.concredito.Objetos;

public class StatusObj {
    int id;
    short estatus;
    String observacionRechazo;

    public StatusObj(int id, short estatus, String observacionRechazo) {
        this.id = id;
        this.estatus = estatus;
        this.observacionRechazo = observacionRechazo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getEstatus() {
        return estatus;
    }

    public void setEstatus(short estatus) {
        this.estatus = estatus;
    }

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }
}
