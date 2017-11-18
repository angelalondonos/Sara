package com.example.angela.sara.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.angela.sara.R;
import com.example.angela.sara.activity.SaraActivity;
import com.example.angela.sara.util.ManagerFireBase;
import com.example.angela.sara.vo.Cita;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 *
 * Fragmento CrearMonitor
 */
public class CrearMonitorFragment extends Fragment {


    /**
     * creación de un ImageView
     */
    @BindView(R.id.imagen_detalle) protected ImageView imagenMonitor;
    /**
     * creación de un Button
     */
    @BindView(R.id.btn_agregar_monitor) protected  Button btnCrearMonitor;
    /**
     * Creación de ImageButton
     */


    /*
    * Atributo de clase ManagerFireBase
     */
    private ManagerFireBase managerFireBase;

    @BindView(R.id.editText_nombre_monitor) protected  EditText nombre;
    @BindView(R.id.editText_userName_monitor) protected  EditText userName;
    @BindView(R.id.editText_contrasena_monitor) protected  EditText contraseña;
    @BindView(R.id.editText_telefono_monitor) protected  EditText telefono;
    @BindView(R.id.spinner_semestre) protected  Spinner semestre;
    @BindView(R.id.spinner_linea) protected  Spinner linea_monitoria;
    @BindView(R.id.editText_lugar_asesoria) protected  EditText lugar;
    @BindView(R.id.button_horario_monitor) protected  ImageButton btnHorario;

    private ArrayList<Cita> citas;



    /**
     * Constructor vacio de la clase CrearMonitorFragment
     */
    public CrearMonitorFragment() {
        // Required empty public constructor
    }

    /**
     * Método que permite inicar fragment_crear_monitor
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_crear_monitor, container, false);
        ButterKnife.bind(this, view);

        citas = new ArrayList<Cita>();

        imagenMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        managerFireBase = ManagerFireBase.getInstancia();
        //managerFireBase.escucharEventoFireBase();


        btnCrearMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Monitor monitor = crearMonitor();
                managerFireBase.insertarMonitor(monitor);
                Log.e("Citas Monitor", monitor.getCitas().toString());

                Toast.makeText(getActivity(), getResources().getString(R.string.msg_btn_crear_monitor), Toast.LENGTH_SHORT).show();
                remplazarFragmento(((SaraActivity)getActivity()).getListaDeMonitoresFragment());

            }
        });

        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                intent.putExtra(Events.DESCRIPTION,  getResources().getString(R.string.msg_calendario_estado));
                intent.putExtra(Events.EVENT_LOCATION, getResources().getString(R.string.msg_universidad));
                intent.putExtra(Events.RRULE, "FREQ=YEARLY");

                startActivity(intent);
            }
        }); //utiliza el onclick listener global
        return view;
    }

    private Monitor crearMonitor() {

        Cita cita =new Cita("nombre_estudiante", "identificacion_estudiante", "semestre", "lineaMonitoria");
        citas.add(cita);
        Monitor monitor = new Monitor(nombre.getText().toString(), userName.getText().toString(),
                telefono.getText().toString(), semestre.getSelectedItem().toString(),
                linea_monitoria.getSelectedItem().toString(), contraseña.getText().toString(),
                lugar.getText().toString(), citas);

        return monitor;
    }


    /**
     * Método que pemite cargar imagen y elegir el lugar donde esta la foto
     */
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, getResources().getString(R.string.msg_btn_cargar_imagen)),10);
    }

    /**
     * Método que permite
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            Uri path = data.getData();
            imagenMonitor.setImageURI(path);
        }
    }

    private void remplazarFragmento(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

    }
}
