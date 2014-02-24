package ca.badenhorst.nutrition;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.List;

/**
 * Created by wade on 21/02/14.
 */
public class HttpGetFoodTask extends AsyncTask<Void, Void, List<String>> {

    private ListActivity activity;
    private ResponseHandler<List<String>> responseHandler = null;
    private String url = null;

    AndroidHttpClient mClient = null;

    public HttpGetFoodTask(String url, ResponseHandler<List<String>> handler, ListActivity activity) {
        this.url = url;
        this.responseHandler = handler;
        this.activity = activity;

        mClient = AndroidHttpClient.newInstance("");
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        HttpGet request = new HttpGet(url);
        try {
            return mClient.execute(request, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        if (null != mClient)
            mClient.close();
        activity.setListAdapter(new ArrayAdapter<String>(
                activity,
                R.layout.list_item, result));
    }

}

