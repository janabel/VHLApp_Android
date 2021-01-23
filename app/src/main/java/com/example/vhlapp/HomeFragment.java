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

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ListView listview = (ListView) view.findViewById(R.id.listview_homepage);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Homepage");
        arrayList.add("Clinical Care Center Feedback Form");
        arrayList.add("MyVHL");
        arrayList.add("VHL Manifestations");
        arrayList.add("News and Events");
        arrayList.add("VHL Facts");
        arrayList.add("Find a VHL Clinical Care Center");
        arrayList.add("Active Surveillance Guidelines");
        arrayList.add("VHL Handbook");
        arrayList.add("Connect");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayList);

        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String whereClause = "position = " + position;
                Log.d("whereclause: ", whereClause);
                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause( whereClause );

                Backendless.Data.of( "WebLinks" ).find( queryBuilder,
                        new AsyncCallback<List<Map>>(){
                            @Override
                            public void handleResponse( List<Map> webLink )
                            {
                                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                                Log.d("success?", "yes");
                                Map URLmap = webLink.get(0);
                                String URL = (String) URLmap.get("URL");

                                Intent i = new Intent(getActivity(), WebHomepage.class);
                                i.putExtra("URL", URL);
                                startActivity(i);

                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                                String error = fault.getCode();
                                Log.d("error: ", error);
                                Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        // fixed
        return view;


    }
}