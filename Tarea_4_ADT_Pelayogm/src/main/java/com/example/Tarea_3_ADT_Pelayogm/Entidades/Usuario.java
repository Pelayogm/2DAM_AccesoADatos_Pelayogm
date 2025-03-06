package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private boolean usuario;
    private boolean estadoSesion;
    private String nombreUsuario;
    private int idUsuarioInterfaz;

    public boolean isEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(boolean estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    protected void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdUsuarioInterfaz() {
        return idUsuarioInterfaz;
    }

    public void setIdUsuarioInterfaz(int idUsuarioInterfaz) {
        this.idUsuarioInterfaz = idUsuarioInterfaz;
    }
}
