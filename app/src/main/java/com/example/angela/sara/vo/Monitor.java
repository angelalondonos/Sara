package com.example.angela.sara.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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
    private String id;
    private ArrayList<Cita> citas;

    public Monitor(){}

    public Monitor(String nombre, String userName, String telefono, String semestre, String lineaMonitoria, String contrasena, String lugarAsesoria, ArrayList<Cita> citas) {
        this.nombre = nombre;
        this.userName = userName;
        this.telefono = telefono;
        this.semestre = semestre;
        this.lineaMonitoria = lineaMonitoria;
        this.contrasena = contrasena;
        this.lugarAsesoria = lugarAsesoria;
        this.citas = citas;
    }

    public Monitor(String nombre, String lineaMonitoria) {
        this.nombre = nombre;
        this.lineaMonitoria= lineaMonitoria;
    }

    protected Monitor(Parcel in) {
        nombre = in.readString();
        userName = in.readString();
        telefono = in.readString();
        semestre = in.readString();
        lineaMonitoria = in.readString();
        contrasena = in.readString();
        lugarAsesoria = in.readString();
    }

    public void setId(String id) {
        this.id = id;
    }


    public static final Creator<Monitor> CREATOR = new Creator<Monitor>() {
        @Override
        public Monitor createFromParcel(Parcel in) {
            return new Monitor(in);
        }

        @Override
        public Monitor[] newArray(int size) {
            return new Monitor[size];
        }
    };

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
        parcel.writeString(contrasena);
        parcel.writeString(nombre);
        parcel.writeString(lineaMonitoria);
        parcel.writeString(lugarAsesoria);
        parcel.writeString(semestre);
        parcel.writeString(userName);
    }
}


