package com.example.angela.sara.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.angela.sara.R;
import com.example.angela.sara.vo.Monitor;

import java.util.ArrayList;

/**
 * Created by andres on 16/11/17.
 */

public class MonitorAdapter extends ArrayAdapter<Monitor> {
    public MonitorAdapter(Context context, ArrayList<Monitor> monitores){
        super(context, 0, monitores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Monitor monitor = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_crear_citas, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(monitor.getNombre());
        tvHome.setText(monitor.getUserName());
        // Return the completed view to render on screen
        return convertView;
    }
}
