package com.example.vhlapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class HandbookFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("What is VHL?", 12);
        map.put("What is Cancer?", 13);
        map.put("Commonly Occurring VHL Manifestations", 14);
        map.put("How do people get VHL?", 16);
        map.put("Obtaining DNA Testing", 17);
        map.put("Early Detection", 19);
        map.put("General Recommendations for Screening", 21);
        map.put("Diagnosis and Treatment", 24);
        map.put("Possible VHL Manifestations", 30);
        map.put("VHL in the Brain and Spinal Cord", 30);
        map.put("VHL in the Pancreas", 36);
        map.put("VHL in the Kidneys", 43);
        map.put("Living Well with Reduced Kidney Function", 47);
        map.put("VHL in the Eye", 52);
        map.put("VHL in the Adrenal Glands (Pheochromocytomas)", 53);
        map.put("VHL and Reproductive Health", 61);
        map.put("Hearing Changes and VHL", 67);
        map.put("VHL and the Liver", 69);
        map.put("VHL and the Lungs", 70);
        map.put("Healthy Living for the VHL Family", 72);
        map.put("Smoking and VHL", 73);
        map.put("Diet", 75);
        map.put("Physical Activity", 86);
        map.put("The VHL Athlete", 88);
        map.put("Emotional Health", 90);
        map.put("Discussing VHL with Your Family", 102);
        map.put("Adult Issues and Family Planning", 103);
        map.put("Talking with Children About VHL", 104);
        map.put("Teens and VHL", 106);
        map.put("VHL Research", 114);
        map.put("Genetic Research and VHL", 114);
        map.put("Progress Toward a Cure", 116);
        map.put("You Can Be Part of Finding a Cure", 119);
        map.put("Glossary of Medical Terms", 124);
        map.put("VHL Support Resources", 136);
        map.put("VHL Alliance’s VISION", 137);
        map.put("VHL Alliance’s MISSION", 137);
        map.put("Publications of the VHL Alliance", 137);
        map.put("Resources on the Internet", 137);
        map.put("Support VHL Alliance Efforts", 138);

        View view = inflater.inflate(R.layout.fragment_handbook, container, false);
        ListView listview = (ListView) view.findViewById(R.id.listview_handbook);

        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : map.entrySet())
            arrayList.add(entry.getKey());

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                arrayList);

// idk how to save the following code lol colors keep changing while scrolling??
//        {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
////                View v0 = listview.getChildAt(0 -
////                        listview.getFirstVisiblePosition());
////                if (v0 != null) {
////                    v0.setBackgroundColor(Color.rgb(102,102,255));
//
//                View view = super.getView(position, convertView, parent);
//                int Position = position - listview.getFirstVisiblePosition();
//
//                if (Position == 0 || Position == 8 || Position == 19 || Position == 25 || Position == 29 || Position == 34) {
//                    view.setBackgroundColor(Color.rgb(102,102,255));
//                }
//                return view;
//            }
//        };

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedFromList = (String) listview.getItemAtPosition(position);
                int pageNumber = map.get(selectedFromList);

                Intent i = new Intent(getActivity(), HandbookPDF.class);
                i.putExtra("pageNumber", pageNumber);
                startActivity(i);

            }
        });

        // fixed
        return view;

    }

}