package com.example.leohgz.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.leohgz.myapplication.R;
import java.util.List;

/**
 * Created by Leonardo on 22/7/2016.
 */
public class Adapter extends ArrayAdapter<String> {
        List<String> list;
        Context ctx;
        LayoutInflater inflater;

    public Adapter(Context context, int textViewResourceId, List<String> objects){

        super(context, textViewResourceId, objects);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        list=objects;

        ctx=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.textrow);

        textView.setText(list.get(position));

        return rowView;
    }
}
