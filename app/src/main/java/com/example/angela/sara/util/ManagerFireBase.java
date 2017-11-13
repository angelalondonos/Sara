package com.example.angela.sara.util;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.angela.sara.vo.Monitor;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

/**
 * Created by andres on 13/11/17.
 */

public class ManagerFireBase {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private static ManagerFireBase instancia;
    private Fragment fragment;
    private OnActualizarAdaptadorListener listener;

    public ManagerFireBase() {
    }

    private ManagerFireBase(Fragment fragment){
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference();
        this.fragment = fragment;
    }
    public static ManagerFireBase instanciar(Fragment fragment) {
        if (instancia == null) {
            instancia = new ManagerFireBase(fragment);
        }
        return instancia;
    }
    public void insertarMonitor(Monitor monitor){
        databaseRef.push().setValue(monitor);
    }

    public void escucharEventoFireBase(){
        databaseRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"agregado");
                Monitor monitor = dataSnapshot.getValue(Monitor.class);
                monitor.setId(dataSnapshot.getKey());

                listener.actualizarAdaptador(monitor);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"cambiado");
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.v(TAG,"eliminado");
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v(TAG,"moved");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v(TAG,"cancelar");
            }
        });
    }

    public interface OnActualizarAdaptadorListener{
        public void actualizarAdaptador(Monitor monitor);
    }
}
