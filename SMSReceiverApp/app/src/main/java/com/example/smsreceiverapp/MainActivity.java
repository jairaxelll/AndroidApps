package com.example.smsreceiverapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText editTextMessage;
    private Button buttonSend;

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

        // Initialize EditText and Button
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Set click listener for the Send button
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    // Send the message to SMSReceiver for processing
                    Intent intent = new Intent(MainActivity.this, SMSReceiver.class);
                    intent.setAction("com.example.smsreceiverapp.SEND_MESSAGE");
                    intent.putExtra("message", message);
                    sendBroadcast(intent);
                    Toast.makeText(MainActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
