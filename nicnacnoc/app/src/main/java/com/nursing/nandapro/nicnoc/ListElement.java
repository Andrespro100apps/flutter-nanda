package com.nursing.nandapro.nicnoc;

import android.widget.TextView;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String dominio;
    public String titulo;
    public String clases;
    public String diagnostico;
    public boolean esFavorito;
    public String texto;

    public ListElement(String dominio, String titulo, String clases, String diagnostico, String texto, boolean esFavorito) {
        this.dominio = dominio;
        this.titulo = titulo;
        this.clases = clases;
        this.diagnostico = diagnostico;
        this.texto = texto;
        this.esFavorito = esFavorito;
    }

    public ListElement(TextView resivo1, String hola, String tres, String cuatro) {

    }

    public String getDominio() {
        return dominio;
    }
    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getClases() {
        return clases;
    }
    public void setClases(String clases) {
        this.clases = clases;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean getEsFavorito(){return esFavorito; }
    public void setEsFavorito(Boolean esFavorito){this.esFavorito = esFavorito; }
}
