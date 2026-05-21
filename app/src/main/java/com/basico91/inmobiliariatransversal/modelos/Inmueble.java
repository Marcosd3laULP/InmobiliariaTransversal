package com.basico91.inmobiliariatransversal.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {

    private int IdInmueble;
    private String dirrecion;
    private String uso;
    private String tipo;
    private String ambientes;
    private String superficie;
    private String latitud;

    private String valor;
    private String imagen;
    private String longitud;
    private Propietario duenio;

    public Inmueble() {
    }

    public Inmueble(int idInmueble, String dirrecion, String uso, String tipo, String ambientes, String superficie, String latitud, String valor, String imagen, String longitud, Propietario duenio) {
        IdInmueble = idInmueble;
        this.dirrecion = dirrecion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.latitud = latitud;
        this.valor = valor;
        this.imagen = imagen;
        this.longitud = longitud;
        this.duenio = duenio;
    }

    public int getIdInmueble() {
        return IdInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        IdInmueble = idInmueble;
    }

    public String getDirrecion() {
        return dirrecion;
    }

    public void setDirrecion(String dirrecion) {
        this.dirrecion = dirrecion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(String ambientes) {
        this.ambientes = ambientes;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }
}
