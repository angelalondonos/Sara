package com.example.angela.sara.Fragments;


import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.angela.sara.R;
import com.example.angela.sara.activity.Tabla;
import com.example.angela.sara.vo.Cita;
import com.example.angela.sara.vo.Monitor;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Clase que permite ver el detalle del monitor
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * A simple {@link Fragment} subclass.
 */
public class DetalleDeMonitorFragment extends Fragment {

    /**
     * creación de TextView
     */
    @BindView(R.id.txtview_nombre) protected TextView txtNombre;
    @BindView(R.id.txtview_linea_monitoria) protected TextView txtLinea;
    @BindView(R.id.contrasena_detalle) protected TextView txtContrasena;
    @BindView(R.id.userName_detalle) protected TextView txtUsename;
    @BindView(R.id.telefono_detalle) protected TextView txtTelefono;
    @BindView(R.id.semestre_detalle) protected TextView txtSemestre;
    @BindView(R.id.lugar_detalle) protected TextView txtLugarAsesoria;
    @BindView(R.id.imagen_detalle_monitoy) protected ImageView imagenDetalle;
    @BindView(R.id.button_compartir_facebook) protected ImageButton btnFacebook;
    ShareDialog shareDialog;
    private TwitterLoginButton btnloginTwitter;
    private ImageButton btnCompartirTwitter;

    /**
     * creación de un Monitor
     */
    private Monitor monitor;
    @BindView(R.id.button_horario_detalle) protected ImageButton btnHorario;

    View view;


    /**
     * Método contructor de la clase
     */
    public DetalleDeMonitorFragment() {
        // Required empty public constructor
    }


    /**
     * Método que permite inicar fragment_detalle_de_monitor
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_detalle_de_monitor, container, false);

        ButterKnife.bind(this, view);
        btnHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long startMillis = 0;

                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder,startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());

                startActivity(intent);
            }
        });


        return view;

    }

    /**
     * Método que permite mostrar el detalle del monitor
     * @param monitor
     */
    public void mostrarMonitor (Monitor monitor) {
        this.monitor = monitor;
        txtNombre.setText(monitor.getNombre());
        txtLinea.setText(monitor.getLineaMonitoria());
        txtContrasena.setText(monitor.getContrasena());
        txtUsename.setText(monitor.getUserName());
        txtTelefono.setText(monitor.getTelefono());
        txtLugarAsesoria.setText(monitor.getLugarAsesoria());
        txtSemestre.setText(monitor.getSemestre());

        Tabla tabla = new Tabla(this.getActivity(), (TableLayout) view.findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);

        ArrayList<Cita> citas = monitor.getCitas();
        citas.remove(0);

        for(Cita cita : citas)
        {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(cita.getIdentificacion_estudiante());
            elementos.add(cita.getNombre_estudiante());
            elementos.add(cita.getSemestre());
            tabla.agregarFilaTabla(elementos);

        }


        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(DetalleDeMonitorFragment.this, Arrays.asList("public_profile","user_friends"));

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent content = new ShareLinkContent.Builder()

                            .setContentUrl(Uri.parse("https://www.youtube.com/watch?v=jhUkGIsKvn0"))
                            .setQuote("Personajes")
                            .setShareHashtag(new ShareHashtag.Builder()
                                    .setHashtag("#Sara UQ")
                                    .build()).build();

                    shareDialog.show(content);
                }

            }

        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TwitterSession session;
        shareDialog = new ShareDialog(getActivity());

        btnCompartirTwitter = (ImageButton) getView().findViewById(R.id.btn_hacer_tuit);
        btnCompartirTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        URL url = new URL("https://www.youtube.com/watch?v=VV9IRQSxx6w");
                        TweetComposer.Builder builder = new TweetComposer.Builder(getContext())
                                .text(monitor.getNombre()).url(url);
                        builder.show();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
            }
        });
        btnloginTwitter = (TwitterLoginButton) getView().findViewById(R.id.twitter_login_button);
        btnloginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                btnloginTwitter.setVisibility(View.INVISIBLE);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btnloginTwitter.onActivityResult(requestCode, resultCode, data);
    }

}
