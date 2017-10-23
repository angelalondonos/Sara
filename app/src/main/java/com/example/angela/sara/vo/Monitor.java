package com.example.angela.sara.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Clase con datos necesarios de monitor
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 */

public class Monitor implements Parcelable{

    private String nombre;
    private String userName;
    private String telefono;
    private String semestre;
    private String lineaMonitoria;
    private String contrasena;
    private String lugarAsesoria;


    public Monitor(String nombre, String lineaMonitoria) {
        this.nombre = nombre;
        this.lineaMonitoria= lineaMonitoria;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getLineaMonitoria() {
        return lineaMonitoria;
    }

    public void setLineaMonitoria(String lineaMonitoria) {
        this.lineaMonitoria = lineaMonitoria;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getLugarAsesoria() {
        return lugarAsesoria;
    }

    public void setLugarAsesoria(String lugarAsesoria) {
        this.lugarAsesoria = lugarAsesoria;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}


