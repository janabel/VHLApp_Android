package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EmergencyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate view
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);

        //create listview, arraylist, and adapter to link the two
        ListView listview = (ListView) view.findViewById(R.id.listview_emergency);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Physician");
        arrayList.add("Caregiver");
        arrayList.add("Specialists");
        arrayList.add("Contact 1");
        arrayList.add("Contact 2");
        arrayList.add("Contact 3");
        arrayList.add("Contact 4");
        arrayList.add("Contact 5");
        arrayList.add("Contact 6");
        arrayList.add("Contact 7");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.mytextview, arrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);
                int fontSize = BaseActivity.getDefaults("fontSize", getContext());
                // Set the text size to progress value for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                view.setMinimumHeight(64);

                return view;
            }
        };

        //set adapter on listview
        listview.setAdapter(arrayAdapter);

        // fixed
        return view;

    }
}
