package com.example.vhlapp;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Alert {

    public static void makeAlert(String alertTitle, Context context) {

        HashMap<String, String> alerts = new HashMap<>();
        alerts.put("Settings", "Manage all your settings within VHLApp.");
        alerts.put("VHLA Handbook", "Click a section title to view the handbook page.");
        alerts.put("View Web Links", "Click a section to open corresponding VHLA website pages and social media accounts.");
        alerts.put("Create Appointments", "Click the add button to create an appointment");
        alerts.put("Emergency Contacts", "Click the add button to select physicians, caregivers, and specialists from your contacts. Call a contact by pressing their name.");


        boolean showappinfo = BaseActivity.getDefaultsBoolean("app info", context);
        if (showappinfo) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(alertTitle);
            builder.setMessage((String) alerts.get(alertTitle));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
}
