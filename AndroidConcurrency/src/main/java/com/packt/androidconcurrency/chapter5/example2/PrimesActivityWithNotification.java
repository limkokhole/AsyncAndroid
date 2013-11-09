package com.packt.androidconcurrency.chapter5.example2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.packt.androidconcurrency.R;
import com.packt.androidconcurrency.chapter5.example4.PrimesIntentServiceWithBroadcast;

/**
 * This activity starts the IntentService but doesn't expect to
 * receive a result, so its nice and simple. The result will be
 * posted as a system-notification by the IntentService itself.
 */
public class PrimesActivityWithNotification extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch5_example3_layout);

        Button go = (Button)findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextView input = (TextView)findViewById(R.id.prime_to_find);
                String value = input.getText().toString();
                if (value.matches("[1-9]+[0-9]*")) {
                    // if the value is a number, trigger the loader to
                    // reload when appropriate.
                    triggerIntentService(Integer.parseInt(value));
                } else {
                    Toast.makeText(PrimesActivityWithNotification.this, "not a number!", 5000).show();
                }
            }
        });
    }

    private void triggerIntentService(int primeToFind) {
        Intent intent = new Intent(this, PrimesIntentServiceWithBroadcast.class);
        intent.putExtra(PrimesIntentServiceWithBroadcast.PARAM, primeToFind);
        startService(intent);
    }
}
