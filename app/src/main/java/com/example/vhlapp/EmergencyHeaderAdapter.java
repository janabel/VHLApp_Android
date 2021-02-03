package com.example.vhlapp;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmergencyHeaderAdapter extends BaseAdapter implements ListAdapter {

    Context context;
    ArrayList<String> data;
    private static LayoutInflater inflater = null;

    public EmergencyHeaderAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.data = arrayList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tv;
        FloatingActionButton b;

        v = inflater.inflate(R.layout.emergency_header, null);
        tv = (TextView) v.findViewById(R.id.em_header);
        b = (FloatingActionButton) v.findViewById(R.id.add_btn);
        b.setFocusable(false);
        b.setFocusableInTouchMode(false);
        v.setBackgroundColor(Color.rgb(250, 128, 114));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click the plus to add contact", Toast.LENGTH_LONG).show();
            }
        });

        tv.setText(data.get(position));
        tv.setFocusable(false);
        tv.setFocusableInTouchMode(false);

        int fontSize = BaseActivity.getDefaultsInt("fontSize", context);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        v.setMinimumHeight(64);

        return v;
    }


    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    @Override
    public boolean isEnabled(int arg0)
    {
        return true;
    }


}
