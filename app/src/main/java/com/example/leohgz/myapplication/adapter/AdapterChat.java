package com.example.leohgz.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Adapter;

import com.example.leohgz.myapplication.Chat.Chat;
import com.example.leohgz.myapplication.R;

import java.util.List;

/**
 * Created by leohgz on 23/7/2016.
 */
public class AdapterChat extends BaseAdapter {

    private List<Chat> objs;
    private Context ctx;
    private LayoutInflater inflater;

    public AdapterChat(List<Chat> obj, Context ctx){
        objs = obj;
        this.ctx = ctx;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.row_layout,null,false);

        TextView textView = (TextView)convertView.findViewById(R.id.textrow);

        Chat chat =objs.get(position);

        textView.setText(chat.getText());

        if (chat.owns()){

            convertView.setBackgroundColor(Color.CYAN);

        }
        return convertView;
    }
}
