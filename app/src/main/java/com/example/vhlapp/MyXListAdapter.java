package com.example.vhlapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyXListAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<String> connect;
    HashMap<String, ArrayList<String>> connectOptions;

    public MyXListAdapter(Context context, ArrayList<String> connect, HashMap<String, ArrayList<String>> connectOptions) {
        this.context = context;
        this.connect = connect;
        this.connectOptions = connectOptions;
    }


    @Override
    public int getGroupCount() {
        return connect.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return connectOptions.get(connect.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return connect.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return connectOptions.get(connect.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String connect = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_parent, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.textParent);
        tv.setText(connect);
        tv.setBackgroundColor(Color.rgb(102, 102, 255));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String connectOptions = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_child, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.textChild);
        tv.setText(connectOptions);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
