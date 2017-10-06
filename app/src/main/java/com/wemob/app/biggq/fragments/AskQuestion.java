package com.wemob.app.biggq.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.afollestad.materialdialogs.MaterialDialog;

import com.wemob.app.biggq.R;
import com.wemob.app.biggq.adapter.AutocompleteAdapter;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.Celebrity;
import com.wemob.app.biggq.utils.Utils;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by admin on 8/31/2017.
 */

public class AskQuestion extends Fragment  implements CompoundButton.OnCheckedChangeListener{
    Button btnSend;
    AskCallbackListener mcallback;
    AutoCompleteTextView edCelebrityText;
    AutocompleteAdapter mAdapter;
    Celebrity current;
    EditText edQuestion,edOfferAmount;
    TextView edAmountToWatch;
    ToggleButton tglButton15,tglButton20,tglButton25,tglButtonFree;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerview=inflater.inflate(R.layout.ask_question_layout,null);
        return containerview;

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mcallback = (AskCallbackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FeedCallbackInterface");
        }
    }
    private void showConfirmation(String offerAmount) {

        mcallback.showConfirmationDialog(offerAmount);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edCelebrityText=(AutoCompleteTextView)view.findViewById(R.id.edCelebrityText);
        edQuestion=(EditText) view.findViewById(R.id.edQuestion);
        edOfferAmount=(EditText)view.findViewById(R.id.edOfferAmount);
        edAmountToWatch=(TextView) view.findViewById(R.id.adAnswerAmount);
        tglButtonFree=(ToggleButton) view.findViewById(R.id.btnFree);
        tglButton15=(ToggleButton)view.findViewById(R.id.btn15);
        tglButton20=(ToggleButton)view.findViewById(R.id.btn20);
        tglButton25=(ToggleButton)view.findViewById(R.id.btn25);
        tglButtonFree.setOnCheckedChangeListener(this);
        tglButton15.setOnCheckedChangeListener(this);
        tglButton20.setOnCheckedChangeListener(this);
        tglButton20.setOnCheckedChangeListener(this);
        edCelebrityText.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString();
                new getJson().execute(newText);
            }
        });
         edCelebrityText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String celebrityName = mAdapter.getItem(position).getName();
                 edCelebrityText.setText(celebrityName);
                 current=mAdapter.getItem(position);
             }
         });
        btnSend=(Button)view.findViewById(R.id.btnsend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edOfferAmount.getText().toString().equals("0") || edOfferAmount.getText().length()==0)
                {
                    Utils.createSnackBar(view,"Enter the amount you offer", Snackbar.LENGTH_LONG).show();
                    return;
                }
                showConfirmation(edOfferAmount.getText().toString());
            }
        });
    }

    private void showCelebrityFragment() {
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        switch (compoundButton.getId())
        {
            case R.id.btnFree:
                edAmountToWatch.setText("0");
                tglButton15.setChecked(false);
                tglButton20.setChecked(false);
                tglButton25.setChecked(false);
                break;
            case R.id.btn15:
                edAmountToWatch.setText("15");
                tglButtonFree.setChecked(false);
                tglButton20.setChecked(false);
                tglButton25.setChecked(false);
                break;
            case R.id.btn20:
                edAmountToWatch.setText("20");
                tglButtonFree.setChecked(false);
                tglButton15.setChecked(false);
                tglButton25.setChecked(false);
                break;
            case R.id.btn25:
                edAmountToWatch.setText("25");
                tglButtonFree.setChecked(false);
                tglButton15.setChecked(false);
                tglButton20.setChecked(false);
                break;
        }

    }


    public interface AskCallbackListener
    {
        public void showConfirmationDialog(String offerAmount);
        public void showCelebrityFragment(String key);
    }

    class getJson extends AsyncTask<String,String,ArrayList<Celebrity>> {

        ArrayList<Celebrity> celebriyList;
        public getJson()
        {

        }
        @Override
        protected ArrayList<Celebrity> doInBackground(String... key) {
            String newText = key[0];
            newText = newText.trim();
            newText = newText.replace(" ", "+");
            try{
                HttpClient hClient = new DefaultHttpClient();
                HttpGet hGet = new HttpGet(ApiLinks.searchCelebrity+"?id="+newText);
                ResponseHandler<String> rHandler = new BasicResponseHandler();
                String data = hClient.execute(hGet,rHandler);
                celebriyList = new ArrayList<Celebrity>();
                JSONArray jArray = new JSONArray(data);
                for(int i=0;i<jArray.length();i++){
                    Celebrity mcelebrity = new Celebrity();
                    mcelebrity.setName(jArray.getJSONObject(i).getString("name"));
                    mcelebrity.setId(jArray.getJSONObject(i).getString("email"));
                    mcelebrity.setEmail(jArray.getJSONObject(i).getString("email"));
                    mcelebrity.setProfile_pic(jArray.getJSONObject(i).getString("profile_pic"));
                    celebriyList.add(mcelebrity);
                }

            }catch(Exception e){
               Log.i("Error","Exception"+e.getMessage());

            }
            ((AppCompatActivity)getActivity()).runOnUiThread(new Runnable(){
                public void run(){
                    mAdapter = new AutocompleteAdapter(getContext(),R.layout.auto_complet_layout,celebriyList);
                    edCelebrityText.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            });
            return null;
        }

    }
}

