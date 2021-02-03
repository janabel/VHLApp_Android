package com.example.vhlapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class EmergencyFragment extends Fragment {

    private int requestcode = 1;

    LinkedHashMap<String, String> map;
    ArrayList<String> arrayList;
    EmergencyAdapter emergencyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);
        ListView listview = (ListView) view.findViewById(R.id.listview_emergency);

        DBHelper dbHelper = new DBHelper( getActivity().getApplicationContext(), "contacts.db", null, 1);
        ArrayList<Contact> contactList = dbHelper.getAll();

        map = new LinkedHashMap<String, String>();

        map.put("Primary Care Physician", "");
        for (Contact element : contactList)
            if (element.getType().equals("Primary Care Physician")) {
                map.put(element.getName(), element.getNumber());
            }
        map.put("Caregiver", "");
        for (Contact element : contactList)
            if (element.getType().equals("Caregiver")) {
                map.put(element.getName(), element.getNumber());
            }
        map.put("Specialists", "");
        for (Contact element : contactList)
            if (element.getType().equals("Specialists")) {
                map.put(element.getName(), element.getNumber());
            }


        arrayList = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet())
            arrayList.add(entry.getKey());
        emergencyAdapter = new EmergencyAdapter(getActivity(), arrayList);
        listview.setAdapter(emergencyAdapter);

        listview.bringToFront();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList = (String) listview.getItemAtPosition(position);
                String phoneNumber = map.get(selectedFromList);
                Log.d("clicked?", "true");

                if (!phoneNumber.equals("")) {
                    Uri number = Uri.parse("tel:" + phoneNumber);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                } else {
                    Toast.makeText(getContext(), "Click the plus to add contact", Toast.LENGTH_LONG).show();
                }
            }
        });


        if( ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            requestStoragePermission();
        } else {
            Toast.makeText(getContext(), "You have already granted this permission", Toast.LENGTH_LONG);
        }

        // fixed
        return view;
    }


    //access contacts runtime permission (necessary)
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_CONTACTS)) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Allow Access to Contacts")
                    .setMessage("Press OK to allow VHLApp to access your phone contacts")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CONTACTS}, requestcode);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CONTACTS}, requestcode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == requestcode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


}
