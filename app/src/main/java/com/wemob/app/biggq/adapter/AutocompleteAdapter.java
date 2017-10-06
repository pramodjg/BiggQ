package com.wemob.app.biggq.adapter;

/**
 * Created by admin on 9/15/2017.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.wemob.app.biggq.R;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.Celebrity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class AutocompleteAdapter extends ArrayAdapter implements Filterable {
    private ArrayList<Celebrity> mCelebrity;
    private String COUNTRY_URL = ApiLinks.searchCelebrity;


    public AutocompleteAdapter(Context context, int resource,ArrayList<Celebrity> celebrities) {
        super(context, resource);
        mCelebrity = celebrities;
    }

    @Override
    public int getCount() {
        return mCelebrity.size();
    }

    @Override
    public Celebrity getItem(int position) {
        return mCelebrity.get(position);
    }

    @Override
    public Filter getFilter() {
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null){
                    try{
                        //get data from the web
                        String term = constraint.toString();
                        mCelebrity = new CelebrityFilter().execute(term).get();
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Exception in Auto Adapter",Toast.LENGTH_LONG).show();
                    }

                    filterResults.values = mCelebrity;
                    filterResults.count = mCelebrity.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0){
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.auto_complet_layout,parent,false);

        //get Country


        TextView countryName = (TextView) view.findViewById(R.id.countryName);

        countryName.setText(mCelebrity.get(position).getName());

        return view;
    }

    //download mCountry list
    private class CelebrityFilter extends AsyncTask<String,Void,ArrayList<Celebrity>>{
        @Override
        protected ArrayList<Celebrity> doInBackground(String... params) {
            try {
                //Create a new COUNTRY SEARCH url Ex "search.php?term=india"
                String NEW_URL = ApiLinks.searchCelebrity +"?id="+params[0];
                Log.d("HUS", "JSON RESPONSE URL " + NEW_URL);

                URL url = new URL(NEW_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null){
                    sb.append(line).append("\n");
                }

                //parse JSON and store it in the list
                String jsonString =  sb.toString();
                ArrayList countryList = new ArrayList<>();

                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = jsonArray.getJSONObject(i);
                    //store the country name
                    Celebrity mcelebrity = new Celebrity();
                    mcelebrity.setName(jo.getString("name"));
                    mcelebrity.setId(jo.getString("email"));
                    mcelebrity.setEmail(jo.getString("email"));
                    mcelebrity.setProfile_pic(jo.getString("profile_pic"));
                    countryList.add(mcelebrity);
                }

                //return the countryList
                return countryList;

            } catch (Exception e) {
                Log.d("HUS", "EXCEPTION " + e);
                return null;
            }
        }
    }
}