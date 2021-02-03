package com.example.vhlapp;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ContactsFragment extends Fragment {

    LinkedHashMap<String, String> map;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ListView listview = (ListView) view.findViewById(R.id.listview_contacts);

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        map = new LinkedHashMap<String, String>();

        while (cursor.moveToNext()) {
            String number = null;
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            //get Phone numbers:
            Cursor phones = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones.moveToNext()) {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phones.close();

            map.put(name, number);

        }
        cursor.close();

        arrayList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet())
            arrayList.add(entry.getKey());

        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.mytextview, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);
                int fontSize = BaseActivity.getDefaultsInt("fontSize", getContext());
                // Set the text size to progress value for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                view.setMinimumHeight(48);
                tv.setBackgroundColor(Color.rgb(255,252,201));

                return view;
            }
        };

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedFromList = (String) listview.getItemAtPosition(position);
                String phoneNumber = map.get(selectedFromList);
                String contactType = BaseActivity.getDefaultsString("contact type", getContext());

                Contact contact = new Contact(position, selectedFromList, contactType, phoneNumber);

                try {
                    DBHelper dbHelper = new DBHelper( getActivity().getApplicationContext(), "contacts.db", null, 1);
                    dbHelper.addOne(contact);
                    Log.d("Clicked?", "true");
                    getActivity().onBackPressed();

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
                    Log.d("Clicked?", "false");
                }
            }
        });

        // fixed
        return view;
    }


}
