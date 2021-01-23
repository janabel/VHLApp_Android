package com.example.vhlapp;

import android.content.Intent;
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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandbookFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_handbook, container, false);

        ListView listview = (ListView) view.findViewById(R.id.listview_handbook);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("What is VHL?");
        arrayList.add("What is Cancer?");
        arrayList.add("Common VHL Manifestations");
        arrayList.add("How do people get VHL?");
        arrayList.add("Obtaining DNA Testing");
        arrayList.add("Early Detection");
        arrayList.add("General Recommendations for Screening");
        arrayList.add("Diagnosis and Treatment");

        arrayList.add("Possible VHL Manifestations");
        arrayList.add("VHL in the Brain and Spinal Cord");
        arrayList.add("VHL in the Pancreas");
        arrayList.add("VHL in the Kidneys");
        arrayList.add("Living Well with Reduced Kidney Function");
        arrayList.add("VHL in the Eye");
        arrayList.add("VHL in the Adrenal Glands");
        arrayList.add("VHL and Reproductive Health");
        arrayList.add("Hearing Changes and VHL");
        arrayList.add("VHL and the Liver");
        arrayList.add("VHL and the Lungs");

        arrayList.add("Healthy Living for the VHL Family");
        arrayList.add("Smoking and VHL");
        arrayList.add("Diet");
        arrayList.add("Physical Activity");
        arrayList.add("The VHL Athlete");
        arrayList.add("Emotional Health");

        arrayList.add("Discussing VHL with Your Family");
        arrayList.add("Adult Issues and Family Planning");
        arrayList.add("Talking with Children About VHL");
        arrayList.add("Teens and VHL");

        arrayList.add("VHL Research");
        arrayList.add("Glossary of Medical Terms");
        arrayList.add("Genetic Research and VHL");
        arrayList.add("Progress Toward a Cure");
        arrayList.add("You Can Be Part of Finding a Cure");

        arrayList.add("VHL Support Resources");
        arrayList.add("VHL Alliance's VISION");
        arrayList.add("VHL Alliance's MISSION");
        arrayList.add("Publications of the VHL Alliance");
        arrayList.add("Resources on the Internet");
        arrayList.add("Support VHL Alliance Efforts");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), HandbookPDF.class);
                startActivity(i);

            }
        });

        // fixed
        return view;
    }
}