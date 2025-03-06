package com.example.Tarea_3_ADT_Pelayogm.Administradores;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;

import java.io.Serializable;

public class AdminTorneos extends Usuario implements Serializable {
    private long idUsuario;
    private boolean isAdminTorneos = true;
    private String nombreAdminTorneo;
    private String contrasenaAdminTorneo;

    public AdminTorneos() {
        setEstadoSesion(true);
    }

    public AdminTorneos(String nombreAdminTorneo, int idUsuario) {
        this.nombreAdminTorneo = nombreAdminTorneo;
        this.idUsuario = idUsuario;
        setEstadoSesion(true);
        setIdUsuarioInterfaz(idUsuario);
    }

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo, long idUsuario) {
        setNombreUsuario(nombreAdminTorneo);
        setEstadoSesion(true);
        this.idUsuario = idUsuario;
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
        setIdUsuarioInterfaz((int) idUsuario);
    }

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo) {
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getNombreAdminTorneo() {
        return nombreAdminTorneo;
    }
}
