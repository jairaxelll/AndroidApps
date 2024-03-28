package com.example.smsreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;



public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Verificar si la acción recibida es la de un mensaje SMS entrante
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            // Obtener el mensaje SMS entrante
            Bundle extras = intent.getExtras();
            if (extras != null) {
                // Extraer el contenido del mensaje SMS
                Object[] pdus = (Object[]) extras.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        if (smsMessage != null) {
                            String sender = smsMessage.getOriginatingAddress();
                            String messageBody = smsMessage.getMessageBody();

                            // Manejar el mensaje SMS entrante (por ejemplo, mostrarlo en un TextView o en un Toast)
                            Toast.makeText(context, "Remitente: " + sender + ", Mensaje: " + messageBody, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }
}
