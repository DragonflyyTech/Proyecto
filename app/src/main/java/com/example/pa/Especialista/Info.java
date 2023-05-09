package com.example.pa.Especialista;

import com.example.pa.Paciente.InfoDiario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Info implements Serializable {

    private int id_user;
    private String nombre;
    private String edad;
    private String usuario;
    private String mail;
    private String contra;

    private String cedula;
    private List<InfoDiario> diarios = new ArrayList<>();

    public Info(){

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<InfoDiario> getDiarios() {
        return diarios;
    }

    public void setDiarios(List<InfoDiario> diarios) {
        this.diarios = diarios;
    }
}