package com.example.angela.sara.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @author Angela Londono
 * @author Rodrigo Ramirez
 * @author Cristian Agudelo
 * Created by angela on 13/11/17.
 */

public class Utilidades {

    public final static String MIS_PREFERENCIAS = "MisPreferencias";
    public final static String LENGUAJE_DE_PREFERENCIA ="languaje_preferences";
    public final static String LENGUAJE_ES = "es";
    public final static String LENGUAJE_EN = "en";

    public static void cambiarIdioma(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS, context.MODE_PRIVATE);
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA,
                LENGUAJE_ES);
        if(language.equals(LENGUAJE_ES)){
            language = LENGUAJE_EN;
        }
        else if(language.equals(LENGUAJE_EN)){
            language = LENGUAJE_ES;
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LENGUAJE_DE_PREFERENCIA, language);
        editor.commit();
        obtenerLenguaje(context);
    }
    @SuppressWarnings("deprecation")
    public static void obtenerLenguaje(Context context) {
        SharedPreferences prefs =
                context.getSharedPreferences(MIS_PREFERENCIAS, context.MODE_PRIVATE);

        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA,
                LENGUAJE_ES);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config
                , null);
    }


    public static void getKeyHash(Context context) {
        try {
            PackageInfo info =
                    context.getPackageManager().getPackageInfo(context.getPackageName(),
                            PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(),
                        Base64.DEFAULT);
                Log.e("Mi clave HASH:", sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("prueba", "1 KeyHash Error: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d("prueba", "2 KeyHash Error: " + e.getMessage());
        }
    }
}
