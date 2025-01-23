package com.example.Tarea_3_ADT_Pelayogm.Administradores;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;

public class Admin extends Usuario {
    private int id;
    private boolean sesionAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSesionAdmin() {
        return sesionAdmin;
    }

    public void setSesionAdmin(boolean sesionAdmin) {
        this.sesionAdmin = sesionAdmin;
    }

    public Admin(int id) {
        setEstadoSesion(true);
        setUsuario(true);
        this.id = id;
    }
}
