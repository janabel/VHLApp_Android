package com.example.vhlapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class HandbookFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //alert made if show app info is checked
        Alert.makeAlert("VHLA Handbook", getContext());

        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("What is VHL?", 12); //color
        map.put("What is Cancer?", 13);
        map.put("Commonly Occurring VHL Manifestations", 14);
        map.put("How do people get VHL?", 16);
        map.put("Obtaining DNA Testing", 17);
        map.put("Early Detection", 19);
        map.put("General Recommendations for Screening", 21);
        map.put("Diagnosis and Treatment", 24);
        map.put("Possible VHL Manifestations", 30); //color
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
        map.put("Healthy Living for the VHL Family", 72); //color
        map.put("Smoking and VHL", 73);
        map.put("Diet", 75);
        map.put("Physical Activity", 86);
        map.put("The VHL Athlete", 88);
        map.put("Emotional Health", 90);
        map.put("Discussing VHL with Your Family", 102); //color
        map.put("Adult Issues and Family Planning", 103);
        map.put("Talking with Children About VHL", 104);
        map.put("Teens and VHL", 106);
        map.put("VHL Research", 114); //color
        map.put("Genetic Research and VHL", 114);
        map.put("Progress Toward a Cure", 116);
        map.put("You Can Be Part of Finding a Cure", 119);
        map.put("Glossary of Medical Terms", 124);
        map.put("VHL Support Resources", 136); //color
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.mytextview, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);
                int fontSize = BaseActivity.getDefaultsInt("fontSize", getContext());
                // Set the text size to progress value for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                view.setMinimumHeight(64);

                if (tv.getText() ==  "What is VHL?"
                        || tv.getText() == "Possible VHL Manifestations"
                        || tv.getText() == "Healthy Living for the VHL Family"
                        || tv.getText() == "Discussing VHL with Your Family"
                        || tv.getText() == "VHL Research"
                        || tv.getText() == "VHL Support Resources") {
                    tv.setBackgroundColor(Color.rgb(177, 156, 217));
                } else {
                    tv.setBackgroundColor( getResources().getColor(android.R.color.white));
                }

                return view;
            }
        };

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