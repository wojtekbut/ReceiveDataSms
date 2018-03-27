package com.wojtek.butrym.receivedatasms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyDataSmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String str = "";

        if (bundle != null){
            // Retrieve the Binary SMS data
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // For every SMS message received (although multipart is not supported with binary)
            //for (int i=0; i<msgs.length; i++) {
                byte[] data = null;

                msgs[0] = SmsMessage.createFromPdu((byte[]) pdus[0]);

                str += "Binary SMS from " + msgs[0].getOriginatingAddress() + " :";

                str += "\nBINARY MESSAGE: ";

                // Return the User Data section minus the
                // User Data Header (UDH) (if there is any UDH at all)
                data = msgs[0].getUserData();

                // Generally you can do away with this for loop
                // You'll just need the next for loop
                //for (int index=0; index < data.length; index++) {
                   // str += Byte.toString(data[index]);
                //}

                //str += "\nTEXT MESSAGE (FROM BINARY): ";

                for (int index=0; index < data.length; index++) {
                    str += Character.toString((char) data[index]);
                }

                str += "\n";
            }

        Log.e("dostałem i wyswietlam: ", str);

            Toast toast = Toast.makeText(context,"Dostalem Data Smsa\n" + str,Toast.LENGTH_LONG);
        toast.show();
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
