package ca.badenhorst.nutrition;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import ca.badenhorst.utils.NameValue;
import ca.badenhorst.utils.NameValueArrayAdapter;

public class ListFoodDescriptionActivity extends ListActivity {

    private String foodGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            foodGroup = extras.getString("food_group");
        }

        if (foodGroup != null || !("".equals(foodGroup.trim()))) {
            String url = null;
            try {
                url = getString(R.string.host) + "food?food_group=" + URLEncoder.encode(foodGroup, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            new GetFoodListTask(url, new ListFoodDescResponseHandler()).execute();
        }
    }

    protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id){

        TextView tv = (TextView) v.findViewById(R.id.textViewItem);
        if (tv != null){
            Intent foodDesc = new Intent(ListFoodDescriptionActivity.this,FoodDescriptionActivity.class);
            foodDesc.putExtra("ndb_no", (String)tv.getTag());
            startActivity(foodDesc);
        }
    }

    private class GetFoodListTask extends AsyncTask<Void, Void, List<NameValue>> {
        private final String url;
        private final ListFoodDescResponseHandler responseHandler;
        private AndroidHttpClient mClient;

        public GetFoodListTask(String url, ListFoodDescResponseHandler listFoodDescResponseHandler) {
            this.url = url;
            this.responseHandler = listFoodDescResponseHandler;
        }

        @Override
        protected List<NameValue> doInBackground(Void... voids) {
            HttpGet request = new HttpGet(url);
            try {
                mClient = AndroidHttpClient.newInstance("");
                return mClient.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(List<NameValue> result) {
            if (null != mClient)
                mClient.close();
            setListAdapter(new NameValueArrayAdapter(
                    ListFoodDescriptionActivity.this,
                    R.layout.text_view_item, result));
        }
    }
}

 class ListFoodDescResponseHandler implements ResponseHandler<List<NameValue>> {
    private static final String NDB_NO_TAG = "ndb_no";
    private static final String SHORT_DESC_TAG = "short_desc";

    @Override
    public List<NameValue> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        List<NameValue> result = new ArrayList<NameValue>();
        String JSONResponse = new BasicResponseHandler()
                .handleResponse(response);
        try {

            // Get top-level JSON Object - a Map
            JSONArray responseArray = (JSONArray) new JSONTokener(
                    JSONResponse).nextValue();

            for (int i = 0; i < responseArray.length(); i++) {
                JSONObject jsonas = responseArray.getJSONObject(i);
                result.add(new NameValue(jsonas.getString(NDB_NO_TAG),jsonas.getString(SHORT_DESC_TAG)));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}



