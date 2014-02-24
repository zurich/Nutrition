package ca.badenhorst.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ca.badenhorst.nutrition.R;

/**
 * Created by wade on 24/02/14.
 */
public class FdGeneralArrayAdapter extends ArrayAdapter<NameValue> {
    private final int layoutResourceId;
    private final Context mContext;
    private final List<NameValue> data;

    public FdGeneralArrayAdapter(Context mContext, int layoutResourceId, List<NameValue> data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        NameValue objectItem = data.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView nameTextView = (TextView) convertView.findViewById(R.id.fd_general_left);
        TextView valueTextView = (TextView) convertView.findViewById(R.id.fd_general_right);
        nameTextView.setText(objectItem.getName());
        valueTextView.setText(objectItem.getValue());

        return convertView;

    }

}


