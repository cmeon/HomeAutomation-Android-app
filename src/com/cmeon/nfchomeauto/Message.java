package com.cmeon.nfchomeauto;

public class Message
{
    private String msg;
    private static final String YAP_SERVER_URL =
	"http://mmu-foe-capstone.appspot.com/control?group=15&msg=";
    
    public Message(String msg) {
	this.msg = msg;
    }

    public String getStringUrl() {
	return YAP_SERVER_URL + msg;
    }
}
