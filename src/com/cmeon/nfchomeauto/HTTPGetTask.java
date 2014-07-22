package com.cmeon.nfchomeauto;

import android.os.AsyncTask;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.impl.client.DefaultHttpClient;
import java.lang.StringBuilder;
import java.lang.Exception;
import android.util.Log;

public class HTTPGetTask extends AsyncTask<String, Void, String>
{

    private AsyncTaskCompleteListener<String> callback;

    public HTTPGetTask(AsyncTaskCompleteListener<String> callback) {
	this.callback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
	String url = strings[0];
	HttpClient client = new DefaultHttpClient();
	HttpGet   request = new HttpGet(url);
	StringBuilder builder = new StringBuilder();
	try {
	    HttpResponse response = client.execute(request);
	    HttpEntity entity = response.getEntity();

	    if (response.getStatusLine().getStatusCode() == 200) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
		String line;
		while ((line = reader.readLine()) != null) {
		    builder.append(line).append('\n');
		}

	    } else {
		// handle error loading data
	    }
	    entity.consumeContent();
	} catch (Exception ex) {
	    // handle exception
	}

	return builder.toString();
    }

    @Override
    protected void onPostExecute(String result) {
	callback.onTaskComplete(result);
    }
}
