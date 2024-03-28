package com.example.smsreceiverapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SMSAdapter adapter;
    private SMSReceiver smsReceiver;
    private List<String> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize SMS list and adapter
        smsList = new ArrayList<>();
        adapter = new SMSAdapter(smsList);
        recyclerView.setAdapter(adapter);

        // Register SMSReceiver to listen for incoming SMS messages
        smsReceiver = new SMSReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister SMSReceiver when the activity is destroyed
        unregisterReceiver(smsReceiver);
    }

    // Method to update the RecyclerView with incoming SMS messages
    public void updateSMSList(String message) {
        smsList.add(message);
        adapter.notifyDataSetChanged();
    }
}
