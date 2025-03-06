package com.example.Tarea_3_ADT_Pelayogm.Credenciales;

public class CredencialUsuario {
    public final String usuarioLogin;
    public String contrasenaLogin;
    public final String rolUsuario;
    public final int idUsuario;

    public CredencialUsuario(String usuarioLogin, String contrasenaLogin, String rolUsuario, int idUsuario) {
        this.usuarioLogin = usuarioLogin;
        this.contrasenaLogin = contrasenaLogin;
        this.rolUsuario = rolUsuario;
        this.idUsuario = idUsuario;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public String getContrasenaLogin() {
        return contrasenaLogin;
    }

    public void setContrasenaLogin(String contrasenaLogin) {
        this.contrasenaLogin = contrasenaLogin;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
