package com.example.smsreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Check if the received intent is for a new SMS message
        if (intent.getAction() != null && intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            // Extract the SMS message from the intent
            Bundle extras = intent.getExtras();
            if (extras != null) {
                // Extract the SMS message from the PDUs (protocol data units) in the extras
                Object[] pdus = (Object[]) extras.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        // Create an SmsMessage object from the PDU
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        if (smsMessage != null) {
                            // Get the sender's phone number and the message body
                            String sender = smsMessage.getOriginatingAddress();
                            String messageBody = smsMessage.getMessageBody();

                            // Display the incoming SMS message in a Toast
                            Toast.makeText(context, "Sender: " + sender + ", Message: " + messageBody, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        } else if (intent.getAction() != null && intent.getAction().equals("com.example.smsreceiverapp.SEND_MESSAGE")) {
            // Message sent from the app itself
            String message = intent.getStringExtra("message");
            if (message != null) {
                // Display the received message in a Toast
                Toast.makeText(context, "Message received: " + message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
