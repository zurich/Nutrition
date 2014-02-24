package ca.badenhorst.nutrition;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FoodGroupActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = getString(R.string.host)+"foodgroup";
        new HttpGetFoodTask(url, new FoodGroupResponseHandler(), this).execute();
    }

    protected void onListItemClick(android.widget.ListView l, android.view.View v, int position, long id){
        if (v instanceof TextView){
            TextView tv = (TextView) v;
            Intent listFoodDesc = new Intent(FoodGroupActivity.this,ListFoodDescriptionActivity.class);
            listFoodDesc.putExtra("food_group", tv.getText());
            startActivity(listFoodDesc);
        }
    }
    private class FoodGroupResponseHandler implements ResponseHandler<List<String>> {

        @Override
        public List<String> handleResponse(HttpResponse response)
                throws IOException {
            List<String> result = new ArrayList<String>();
            String JSONResponse = new BasicResponseHandler()
                    .handleResponse(response);
            try {

                // Get top-level JSON Object - a Map
                JSONObject responseObject = (JSONObject) new JSONTokener(
                        JSONResponse).nextValue();
                Iterator keys = responseObject.keys();
                while (keys.hasNext())
                {
                    String item = (String) keys.next();
                    result.add(responseObject.getString(item));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
