package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.*;
import android.os.Bundle;
import android.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends Activity implements AsyncTaskCompleteListener<String>
{
    private PendingIntent pendingIntent;
    public final static String EXTRA_MESSAGE = "com.cmeon.nfchomeauto.MESSAGE";
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    private NfcAdapter mNfcAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	    ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.main);
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

    @Override
    public void onTaskComplete(String result) {
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
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

            // Route video messages to MovieInfo and play movie
            if (tagMessage.split(" ")[0].equals("video")) {
                Log.d("m","m");
                String movieId = tagMessage.split(" ")[1];
                Message msg = new Message("play_movie " + movieId);
                new HTTPGetTask(MainActivity.this).execute(msg.getStringUrl());

                Integer pos = Arrays
                        .asList(Data.movieIds)
                        .indexOf(movieId);

                startAnotherActivity(pos.toString(), MovieInfo.class);
            }

            // Handle pause and stop
            if (tagMessage.split(" ")[0].equals("action")) {
                Message msg = new Message(tagMessage);
                new HTTPGetTask(MainActivity.this).execute(msg.getStringUrl());
            }

            // Handle torch work
            if (tagMessage.split(" ")[0].equals("torch")) {
                Message msg = new Message(tagMessage);
                new HTTPGetTask(MainActivity.this).execute(msg.getStringUrl());
                startAnotherActivity(tagMessage, LightsActivity.class);
            }
        }
    }

    private void startAnotherActivity(String message, Class NewActivity) {
        Intent intent = new Intent(this, NewActivity);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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

    public void openVideosActivity(View view) {
	startOtherActivity(VideosActivity.class);
    }

    public void openMusicActivity(View view) {
	startOtherActivity(MusicActivity.class);
    }

    public void openPhotosActivity(View view) {
	startOtherActivity(PhotosActivity.class);
    }

    public void openLightsActivity(View view) {
        startOtherActivity(LightsActivity.class);
    }

    void startOtherActivity(Class cls) {
	Intent intent = new Intent(MainActivity.this, cls);
	startActivity(intent);
    }
}
