package ca.badenhorst.nutrition;

import android.app.ListActivity;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ca.badenhorst.nutrition.model.FoodDescription;
import ca.badenhorst.utils.FdGeneralArrayAdapter;
import ca.badenhorst.utils.NameValue;
import ca.badenhorst.utils.NameValueArrayAdapter;

public class FoodDescriptionActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String ndb_no = extras.getString("ndb_no");
        String url = getResources().getString(R.string.host) + "food?ndb_no=" + ndb_no;
        new HttpGetFoodDescriptionTask(url, new JSONResponseHandler()).execute();
    }

    private class HttpGetFoodDescriptionTask extends AsyncTask<Void,Void,FoodDescription> {
        private final JSONResponseHandler responseHandler;
        private final String url;

        public HttpGetFoodDescriptionTask(String url, JSONResponseHandler jsonResponseHandler) {
            this.url = url;
            this.responseHandler = jsonResponseHandler;
        }

        @Override
        protected FoodDescription doInBackground(Void... voids) {
            HttpGet get = new HttpGet(url);
            AndroidHttpClient mClient = AndroidHttpClient.newInstance("");
            try {
                return mClient.execute(get, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(FoodDescription result){
            List<NameValue> list = new ArrayList<NameValue>();
            list.add(new NameValue("Group",result.getFood_group_desc()));
            list.add(new NameValue("Name",result.getShort_desc()));
            list.add(new NameValue("Desc",result.getLong_desc()));
            setListAdapter(new FdGeneralArrayAdapter(
                    FoodDescriptionActivity.this,
                    R.layout.food_description_general_list_item, list));
        }
    }

    private class JSONResponseHandler implements ResponseHandler<FoodDescription> {

        private static final String NDB_NO_TAG = "ndb_no";
        private static final String FOOD_GROUP_DESC_TAG = "food_group_desc";
        private static final String SHORT_DESC_TAG = "short_desc";
        private static final String LONG_DESC_TAG = "long_desc";
        private static final String NUTRIENTS_TAG = "nutrients";
        private static final String SAMPLE_SIZE_TAG = "sampleSizes";

        @Override
        public FoodDescription handleResponse(HttpResponse response)
                throws IOException {
            FoodDescription result = new FoodDescription();
            String JSONResponse = new BasicResponseHandler()
                    .handleResponse(response);
            try {

                // Get top-level JSON Object - a Map
                JSONObject responseObject = (JSONObject) new JSONTokener(
                        JSONResponse).nextValue();

                result.setNdb_no((String) responseObject.get(NDB_NO_TAG));
                result.setFood_group_desc((String)responseObject.get(FOOD_GROUP_DESC_TAG));
                result.setShort_desc((String)responseObject.get(SHORT_DESC_TAG));
                result.setLong_desc((String) responseObject.get(LONG_DESC_TAG));

//                JSONArray nutrients = responseObject.getJSONArray(NUTRIENTS_TAG);
//                result.add("got " + nutrients.length() + " nutrients back");
//
//                JSONArray sampleSizes = responseObject.getJSONArray(SAMPLE_SIZE_TAG);
//                result.add("got " + sampleSizes.length() + " sampleSizes back");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }


}



