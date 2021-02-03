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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmergencyAdapter extends BaseAdapter implements ListAdapter {

    Context context;
    ArrayList<String> data;
    private static LayoutInflater inflater = null;

    public EmergencyAdapter(Context context, ArrayList<String> arrayList) {
        // TODO Auto-generated constructor stub
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
    public int getViewTypeCount() {
        return 2;
    }
// Returns the number of types of Views that will be created ...

    public int getItemViewType(int position) {
        //call header = 0, contacts = 1
        String text = getItem(position);
        if (text == "Primary Care Physician"
            || text == "Caregiver"
            || text == "Specialists") {
            return 0;
        } else {
            return 1;
        }
    }
// Get the type of View that will be created ...

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tv;
        FloatingActionButton b;

        if (getItemViewType(position) == 0) {
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

        } else {
            v = inflater.inflate(R.layout.emergency_textview, null);
            tv = (TextView) v.findViewById(R.id.em_text);
            b = (FloatingActionButton) v.findViewById(R.id.delete_btn);
            b.setFocusable(false);
            b.setFocusableInTouchMode(false);
            v.setBackgroundColor(Color.rgb(255, 255, 255));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper( v.getContext().getApplicationContext(), "contacts.db", null, 1);
                    ArrayList<Contact> contactList = dbHelper.getAll();

                    Contact contact = dbHelper.getByName((String) tv.getText());
                    String phoneNumber = contact.getNumber();
                    Uri number = Uri.parse("tel:" + phoneNumber);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    v.getContext().startActivity(callIntent);
                }
            });

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper dbHelper = new DBHelper( v.getContext().getApplicationContext(), "contacts.db", null, 1);

                    View item = (View) b.getParent();
                    String name = (String) ((TextView) item.findViewById(R.id.em_text)).getText();
                    dbHelper.deleteOne(dbHelper.getByName(name));
                    notifyDataSetChanged();

                }
            });

        }

        tv.setText((String) data.get(position));
        tv.setFocusable(false);

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
