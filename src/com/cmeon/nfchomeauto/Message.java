package com.cmeon.nfchomeauto;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Message
{
    private final String msg;
    private static final String YAP_SERVER_URL =
	"http://mmu-foe-capstone.appspot.com/control?group=15&msg=";
    
    public Message(String msg) {
	    this.msg = msg;
    }

    public String getStringUrl() {
        String url = null;
        try {
            url = YAP_SERVER_URL + URLEncoder.encode(msg, "utf-8");
            Log.d("url", url);
        } catch (UnsupportedEncodingException e) {
            // handle exception
        }
        return url;
    }
}
