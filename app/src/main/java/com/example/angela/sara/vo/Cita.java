package com.example.angela.sara.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by andres on 15/11/17.
 */

public class Cita implements Parcelable {

    private String nombre_estudiante;
    private String identificacion_estudiante;
    private String semestre;
    private String lineaMonitoria;

    public Cita() {
    }

    public Cita(String nombre_estudiante, String identificacion_estudiante, String semestre, String lineaMonitoria) {
        this.nombre_estudiante = nombre_estudiante;
        this.identificacion_estudiante = identificacion_estudiante;
        this.semestre = semestre;
        this.lineaMonitoria = lineaMonitoria;
    }

    protected Cita(Parcel in) {
        nombre_estudiante = in.readString();
        identificacion_estudiante = in.readString();
        semestre = in.readString();
        lineaMonitoria = in.readString();
    }

    /**
     * Método encargado de crear al título académico con base al Parcel recibido,
     * también es necesario para enviar array para la lectura de arrays enviadas
     * por medio del Parcel
     */
    public static final Creator<Cita> CREATOR = new Creator<Cita>() {
        @Override
        public Cita createFromParcel(Parcel in) {
            return new Cita(in);
        }

        @Override
        public Cita[] newArray(int size) {
            return new Cita[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre_estudiante);
        parcel.writeString(identificacion_estudiante);
        parcel.writeString(semestre);
        parcel.writeString(lineaMonitoria);
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public void setNombre_estudiante(String nombre_estudiante) {
        this.nombre_estudiante = nombre_estudiante;
    }

    public String getIdentificacion_estudiante() {
        return identificacion_estudiante;
    }

    public void setIdentificacion_estudiante(String identificacion_estudiante) {
        this.identificacion_estudiante = identificacion_estudiante;
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

    public static Creator<Cita> getCREATOR() {
        return CREATOR;
    }
}
