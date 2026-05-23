package com.basico91.inmobiliariatransversal.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {

    private int IdInmueble;
    private String dirrecion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double superficie;
    private String latitud;

    private double valor;
    private String imagen;
    private String longitud;

    private int idPropietario;
    private Propietario duenio;

    private boolean disponible;

    private boolean tieneContratoVigente;

    public Inmueble() {
    }

    public Inmueble(int idInmueble, String dirrecion, String uso, String tipo, int ambientes, double superficie, String latitud, double valor, String imagen, String longitud, int idPropietario, Propietario duenio, boolean disponible, boolean tieneContratoVigente) {
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
        this.idPropietario = idPropietario;
        this.duenio = duenio;
        this.disponible = disponible;
        this.tieneContratoVigente = tieneContratoVigente;
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

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
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

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Propietario getDuenio() {
        return duenio;
    }

    public void setDuenio(Propietario duenio) {
        this.duenio = duenio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isTieneContratoVigente() {
        return tieneContratoVigente;
    }

    public void setTieneContratoVigente(boolean tieneContratoVigente) {
        this.tieneContratoVigente = tieneContratoVigente;
    }
}

