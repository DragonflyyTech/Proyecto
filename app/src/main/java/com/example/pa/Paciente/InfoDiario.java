package com.example.pa.Paciente;

import java.io.Serializable;

public class InfoDiario implements Serializable {

    private int id_diario;
    private String contenido;
    private String emocion;
    private String titulo;

    private String fecha;
    private int id_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_diario() {
        return id_diario;
    }

    public void setId_diario(int id_diario) {
        this.id_diario = id_diario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getEmocion() {
        return emocion;
    }

    public void setEmocion(String emocion) {
        this.emocion = emocion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
