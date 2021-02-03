package com.example.vhlapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;


public class EmergencyFragment extends Fragment {

    private int requestcode = 1;

    LinkedHashMap<String, String> map;
    ArrayList<String> arrayList;
    EmergencyAdapter emergencyAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        Alert.makeAlert("Emergency Contacts", getContext());

        ListView physicianHeader = (ListView) view.findViewById(R.id.header_physician);
        ListView physicianlv = (ListView) view.findViewById(R.id.listview_physician);
        ListView caregiverHeader = (ListView) view.findViewById(R.id.header_caregiver);
        ListView caregiverlv = (ListView) view.findViewById(R.id.listview_caregiver);
        ListView specialistHeader = (ListView) view.findViewById(R.id.header_specialists);
        ListView specialistlv = (ListView) view.findViewById(R.id.listview_specialists);

        ArrayList<String> PCP = new ArrayList<>();
        PCP.add("Primary Care Physician");
        EmergencyHeaderAdapter pcpAdapter = new EmergencyHeaderAdapter(getActivity(), PCP);
        physicianHeader.setAdapter(pcpAdapter);

        ArrayList<String> CG = new ArrayList<>();
        CG.add("Caregiver");
        EmergencyHeaderAdapter cgAdapter = new EmergencyHeaderAdapter(getActivity(), CG);
        caregiverHeader.setAdapter(cgAdapter);

        ArrayList<String> SP = new ArrayList<>();
        SP.add("Specialists");
        EmergencyHeaderAdapter spAdapter = new EmergencyHeaderAdapter(getActivity(), SP);
        specialistHeader.setAdapter(spAdapter);


        //making the arrayLists from contacts
        DBHelper dbHelper = new DBHelper( getActivity().getApplicationContext(), "contacts.db", null, 1);
        ArrayList<Contact> contactList = dbHelper.getAll();

        ArrayList<Contact> physicianList = new ArrayList<>();
        for (Contact element : contactList) {
            if (element.getType().equals("Primary Care Physician")) {
                physicianList.add(element);
            }
        }
        EmergencyAdapter physicianAdapter = new EmergencyAdapter(getActivity(), physicianList);
        physicianlv.setAdapter(physicianAdapter);


        ArrayList<Contact> caregiverList = new ArrayList<>();
        for (Contact element : contactList) {
            if (element.getType().equals("Caregiver")) {
                caregiverList.add(element);
            }
        }
        EmergencyAdapter caregiverAdapter = new EmergencyAdapter(getActivity(), caregiverList);
        caregiverlv.setAdapter(caregiverAdapter);


        ArrayList<Contact> specialistList = new ArrayList<>();
        for (Contact element : contactList) {
            if (element.getType().equals("Specialists")) {
                specialistList.add(element);
            }
        }
        EmergencyAdapter specialistAdapter = new EmergencyAdapter(getActivity(), specialistList);
        specialistlv.setAdapter(specialistAdapter);

//
//        map = new LinkedHashMap<String, String>();
//
//        map.put("Primary Care Physician", "");
//        for (Contact element : contactList)
//            if (element.getType().equals("Primary Care Physician")) {
//                map.put(element.getName(), element.getNumber());
//            }
//        map.put("Caregiver", "");
//        for (Contact element : contactList)
//            if (element.getType().equals("Caregiver")) {
//                map.put(element.getName(), element.getNumber());
//            }
//        map.put("Specialists", "");
//        for (Contact element : contactList)
//            if (element.getType().equals("Specialists")) {
//                map.put(element.getName(), element.getNumber());
//            }

        physicianHeader.bringToFront();
        physicianlv.bringToFront();
        caregiverHeader.bringToFront();
        caregiverlv.bringToFront();
        specialistHeader.bringToFront();
        specialistlv.bringToFront();


        if( ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            requestStoragePermission();
        } else {
            //Toast.makeText(getContext(), "You have already granted this permission", Toast.LENGTH_LONG).show();
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
