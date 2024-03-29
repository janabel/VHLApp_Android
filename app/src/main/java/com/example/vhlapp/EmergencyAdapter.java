package com.example.vhlapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmergencyAdapter extends BaseAdapter implements ListAdapter {

    Context context;
    ArrayList<Contact> data;
    private static LayoutInflater inflater = null;


    public EmergencyAdapter(Context context, ArrayList<Contact> arrayList) {
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
    public Contact getItem(int position) {
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

        v = inflater.inflate(R.layout.emergency_textview, null);
        tv = (TextView) v.findViewById(R.id.em_text);
        b = (FloatingActionButton) v.findViewById(R.id.delete_btn);
        b.setFocusable(false);
        b.setFocusableInTouchMode(false);
        v.setBackgroundColor(Color.rgb(255, 255, 255));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contactToCall = getItem(position);
                String phoneNumber = contactToCall.getNumber();
                Uri number = Uri.parse("tel:" + phoneNumber);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                v.getContext().startActivity(callIntent);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper( v.getContext().getApplicationContext(), "contacts.db", null, 1);
                Contact contactToDelete = getItem(position);
                dbHelper.deleteOne(contactToDelete);
                notifyDataSetChanged();
            }
        });

        tv.setText((String) data.get(position).getName());
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
