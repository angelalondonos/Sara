package com.example.angela.sara.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angela.sara.R;
import com.example.angela.sara.util.ManagerFireBase;
import com.example.angela.sara.vo.Monitor;

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

    
    private ImageButton btnHorario;
    /*
    * Atributo de clase ManagerFireBase
     */
    private ManagerFireBase managerFireBase;

    private EditText nombre;

    private EditText userName;

    private EditText contraseña;

    private EditText telefono;

    //semestre = spinner_semestre

    //linea = txt_linea_monitoria"

    private EditText lugar ;


    @BindView(R.id.button_horario_monitor) protected  ImageButton btnHorario;


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

        imagenMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        nombre = (EditText) view.findViewById(R.id.editText_nombre_monitor);

        userName = (EditText) view.findViewById(R.id.editText_userName_monitor);

        contraseña = (EditText) view.findViewById(R.id.editText_contrasena_monitor);

        telefono = (EditText) view.findViewById(R.id.editText_telefono_monitor);

        //semestre = spinner_semestre

        //linea = txt_linea_monitoria"

        lugar = (EditText) view.findViewById(R.id.editText_lugar_asesoria);

        managerFireBase = ManagerFireBase.instanciar(this);
        //managerFireBase.escucharEventoFireBase();


        btnCrearMonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managerFireBase.insertarMonitor(crearMonitor());
                Toast.makeText(getActivity(), getResources().getString(R.string.msg_btn_crear_monitor), Toast.LENGTH_SHORT).show();
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

        Monitor monitor = new Monitor(nombre.getText().toString(), userName.getText().toString(),
                telefono.getText().toString(), "Ultimo", "Programación", contraseña.getText().toString(),
                lugar.getText().toString());

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
}
