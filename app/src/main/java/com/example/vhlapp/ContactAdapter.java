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

import java.util.ArrayList;

//not currently used, just in case
public class ContactAdapter extends BaseAdapter implements ListAdapter {

    Context context;
    ArrayList<Contact> data;
    private static LayoutInflater inflater = null;


    public ContactAdapter(Context context, ArrayList<Contact> arrayList) {
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

        v = inflater.inflate(R.layout.mytextview, null);
        tv = v.findViewById(android.R.id.text1);
        v.setBackgroundColor(Color.rgb(255, 252, 201)); //pale yellow, can change on Override and super.getView

        tv.setText(data.get(position).toString());

        int fontSize = BaseActivity.getDefaultsInt("fontSize", context);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        v.setMinimumHeight(60);

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
