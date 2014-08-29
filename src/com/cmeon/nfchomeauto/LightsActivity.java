package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LightsActivity extends Activity implements AsyncTaskCompleteListener<String>
{
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    private NfcAdapter mNfcAdapter;

    // shows the results
    @Override
    public void onTaskComplete(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	    super.onCreate(savedInstanceState);
	    ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.hide();
	    setContentView(R.layout.lights);

        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter tech = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter tag  = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        try {
            ndef.addDataType("*/*");    /* Handles all MIME based dispatches.
                                       You should specify only the ones that you need. */
            tech.addDataType("*/*");
        }
        catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }

        intentFiltersArray = new IntentFilter[] {ndef, tech, tag};

        techListsArray = new String[][] { new String[] {
                NfcF.class.getName(),
                MifareClassic.class.getName(),
                MifareUltralight.class.getName(),
                Ndef.class.getName(),
                NdefFormatable.class.getName(),
                NfcA.class.getName(),
                NfcB.class.getName(),
                NfcV.class.getName()
        } };

        nfcIntentHandler(getIntent());
    }

    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            // Turn on torch
            toggleLamp("on");
        } else {
            // Turn off torch
            toggleLamp("off");
        }
    }

    protected void onPause() {
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }

    protected void onResume() {
        super.onResume();
        Log.d("state", "resume");
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent,
                intentFiltersArray, techListsArray);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        nfcIntentHandler(intent);
    }

    private void nfcIntentHandler(Intent intent) {
        String action = intent.getAction();
        Log.d("nfc", "nfc");
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Log.d("nfc", "nfc tech");
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String tagID = bin2hex(tag.getId());

            String tagMessage = Data.getIDInfo(tagID);

            // Handle pause and stop
            if (tagMessage.split(" ")[0].equals("action")) {
                Message msg = new Message(tagMessage);
                new HTTPGetTask(this).execute(msg.getStringUrl());
            }

            // Handle torch work
            if (tagMessage.split(" ")[0].equals("torch")) {
                Log.d("t", tagMessage);
                toggleLamp(tagMessage.split(" ")[1], tagMessage);
            }
        }
    }

    private static String bin2hex(byte[] data) {
        String result = "";
        if (data.length != 0) {
            StringBuilder sb = new StringBuilder();
            for (byte b : data) {
                sb.append(String.format("%02X", b));
            }
            result = sb.toString();
        }
        return result;
    }

    private void toggleLamp(String s) {
        toggleLamp(s, " ");
    }

    private void toggleLamp(String s, String extra) {
        Message msg = new Message("lamp " + s);
        new HTTPGetTask(this).execute(msg.getStringUrl());
        if (extra.equals("torch on")) {
            ((ToggleButton) findViewById(R.id.togglebutton)).setChecked(true);
        }
        if (extra.equals("torch off")) {
            ((ToggleButton) findViewById(R.id.togglebutton)).setChecked(false);
        }
    }

}
