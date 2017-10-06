package com.wemob.app.biggq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wemob.app.biggq.HomePageWrapper;
import com.wemob.app.biggq.R;
import com.wemob.app.biggq.adapter.FeedRecycleAdapter;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.FeedObject;
import com.wemob.app.biggq.data.Person;
import com.wemob.app.biggq.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by admin on 8/17/2017.
 */

public class FeedFragment extends Fragment {

    RecyclerView feed_recycle_view;

    ArrayList<FeedObject> datatoshow;

    boolean fetchflags[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View content=inflater.inflate(R.layout.home_page_feed_layout,null);
        return content;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        feed_recycle_view=(RecyclerView)view.findViewById(R.id.recFeedList);
        datatoshow=new ArrayList<FeedObject>();
         fetchFeeds();




    }
    private void fetchFeeds()
    {
        String FeedIdLink= ApiLinks.feedURL+"?type=feeds";
        AsyncHttpClient feedclient=new AsyncHttpClient();
        feedclient.get(getContext(), FeedIdLink, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    parseFeedJSON(responseBody);
                } catch (Exception e) {
                    Utils.createSnackBar(feed_recycle_view,"Unable to Fetch Feeds 1"+e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                showError(responseBody);
            }
        });
    }

    private void parseFeedJSON(byte[] responseBody) throws  Exception{

        String stresponse=new String(responseBody);

        JSONArray feedIDArray=new JSONArray(stresponse);

        if(feedIDArray!=null)
        {

           for(int i=0;i<feedIDArray.length();i++)
           {

               JSONObject tempFeed=feedIDArray.getJSONObject(i);

               Toast.makeText(getContext(),tempFeed.getString("color_code").toString(),Toast.LENGTH_SHORT).show();

               FeedObject temp=new FeedObject();

               temp.setId(tempFeed.getInt("ID"));

               temp.setSubject(tempFeed.getString("celebrity_name"));

               temp.setShortdescription(tempFeed.getString("asked"));

               temp.setCelebrityid(tempFeed.getString("celebrity_id"));

               temp.setQuestionamount(tempFeed.getString("price"));

               temp.setAnsweramount(tempFeed.getString("share_price"));

               temp.setAnswervideo(tempFeed.getString("answer_link"));

               temp.setQuestionStatus(tempFeed.getInt("is_answered"));

               Person askedby=new Person();

               askedby.setId(tempFeed.getString("user_id"));

               askedby.setAskedDate(tempFeed.getString("timings"));

               askedby.setProfileImage(tempFeed.getString("user_profile_pic"));

               temp.setAskedBy(askedby);

               String colorcode=tempFeed.getString("color_code");
               colorcode=colorcode.replace("\t","");
               if(colorcode.startsWith("#")) {
                   temp.setColor_code(colorcode);

               }
               else
               {
                   temp.setColor_code("#"+colorcode);
                   colorcode="#"+colorcode;
               }



                temp.setCoverPic(tempFeed.getString("cover_pic"));
               temp.setViewcount("<font color='"+colorcode+"'>"+tempFeed.getString("views")+"</font> views");

               temp.setLast_answered(tempFeed.getString("answer_time"));

               temp.setFavcount(Integer.parseInt(tempFeed.getString("favorite_count")));

               temp.setCommentcount(0);

               temp.setButtonback(R.drawable.circle_pale);

               temp.setTextback(R.drawable.rounded_text_pale);

               temp.setTextamtback(R.drawable.rounded_text_amt_pale);

               temp.setTextcolor(0xFFFFFF);

               String purchaserList=tempFeed.getString("purchaser_list");
               Toast.makeText(getContext(),purchaserList.toString(),Toast.LENGTH_SHORT).show();
               String purchaser[]=purchaserList.split(",");
               for(int j=0;j<(purchaser.length-2);j++)
               {
                   temp.getPurchased().add(purchaser[j]);
               }


               datatoshow.add(temp);

           }

            FeedRecycleAdapter mAdapter = new FeedRecycleAdapter(getContext(),datatoshow,(HomePageWrapper)(AppCompatActivity)getActivity());

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

            feed_recycle_view.setLayoutManager(mLayoutManager);

            feed_recycle_view.setItemAnimator(new DefaultItemAnimator());

            feed_recycle_view.setAdapter(mAdapter);

        }
    }
    private void showError(byte[] responseBody,int pos) {
        Utils.createSnackBar(feed_recycle_view,"Unable to Fetch Feeds 2", Snackbar.LENGTH_SHORT).show();

    }
    private void showError(byte[] responseBody) {
        Utils.createSnackBar(feed_recycle_view,"Unable to Fetch Feeds 3", Snackbar.LENGTH_SHORT).show();

    }


}

