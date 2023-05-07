package com.example.pa.Paciente;

import java.io.Serializable;

public class Info2 implements Serializable {

    private int id_espe;
    private String nombre;
    private String edad;
    private String usuario;
    private String mail;
    private String contra;

    public int getId_espe() {
        return id_espe;
    }

    public void setId_espe(int id_espe) {
        this.id_espe = id_espe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
