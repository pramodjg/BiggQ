package com.wemob.app.biggq.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.wemob.app.biggq.BiggQ;
import com.wemob.app.biggq.HomePageWrapper;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.User;
import com.wemob.app.biggq.widget.HeaderView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.wemob.app.biggq.R;
import com.wemob.app.biggq.components.ProfileOddRowAdapter;
import com.wemob.app.biggq.components.TimelineRow;
import com.wemob.app.biggq.data.FeedObject;
import com.wemob.app.biggq.data.Person;

/**
 * Created by admin on 8/24/2017.
 */

public class ProfilePage extends Fragment implements AppBarLayout.OnOffsetChangedListener {
    @Bind(R.id.toolbar_header_view)
    protected HeaderView toolbarHeaderView;

    @Bind(R.id.float_header_view)
    protected HeaderView floatHeaderView;

    @Bind(R.id.appbar)
    protected AppBarLayout appBarLayout;

    @Bind(R.id.toolbar)
    protected LinearLayout toolbar;

    private boolean isHideToolbarView = false;


    RecyclerView profle_recycle_view;
    ImageButton askquestion;
    ProfileCallbackListener mcallback;
//
    View contentview;

    User currentUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        currentUser=(User)getArguments().getSerializable("CurrentUser");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentview=inflater.inflate(R.layout.profile_page_layout,null);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return contentview;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.profile_menu, menu);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         ButterKnife.bind(this,view);

        initUi();





        profle_recycle_view=(RecyclerView)view.findViewById(R.id.timeline_listView);
        ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();

        askquestion=(ImageButton)view.findViewById(R.id.img_ask);
        askquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcallback.showAskQuestionFragment();
            }
        });
// Create new timeline row (Row Id)
        TimelineRow myRow = new TimelineRow(0);

// To set the row Date (optional)
        myRow.setDate(new Date());
// To set the row Title (optional)
        myRow.setTitle("Title");
// To set the row Description (optional)
        myRow.setDescription("The publicity of the film is low key. Are you that confident about people watching it or is" +
                "it a strategy?");
// To set the row bitmap image (optional)
        myRow.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.question_ico));
// To set row Below Line Color (optional)
        myRow.setBellowLineColor(Color.argb(255, 0, 0, 0));
// To set row Below Line Size in dp (optional)
        myRow.setBellowLineSize(6);
// To set row Image Size in dp (optional)
        myRow.setImageSize(24);
// To set background color of the row image (optional)
        myRow.setBackgroundColor(Color.argb(255, 0, 0, 0));
// To set the Background Size of the row image in dp (optional)
        myRow.setBackgroundSize(28);
// To set row Date text color (optional)
        myRow.setDateColor(Color.argb(255, 0, 0, 0));
// To set row Title text color (optional)
        myRow.setTitleColor(Color.argb(255, 0, 0, 0));
// To set row Description text color (optional)
        myRow.setDescriptionColor(Color.argb(255, 0, 0, 0));


// Create new timeline row (Row Id)
        TimelineRow myans = new TimelineRow(1);

// To set the row Date (optional)
        myans.setDate(new Date());
// To set the row Title (optional)
        myans.setTitle("Title");
// To set the row Description (optional)
        myans.setDescription("The publicity of the film is low key. Are you that confident about people watching it or is" +
                "it a strategy?");
// To set the row bitmap image (optional)
        myans.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.answer_ico));
// To set row Below Line Color (optional)
        myans.setBellowLineColor(Color.argb(255, 0, 0, 0));
// To set row Below Line Size in dp (optional)
        myans.setBellowLineSize(6);
// To set row Image Size in dp (optional)
        myans.setImageSize(28);
// To set background color of the row image (optional)
        myans.setBackgroundColor(Color.argb(255, 0, 0, 0));
// To set the Background Size of the row image in dp (optional)
        myans.setBackgroundSize(24);
// To set row Date text color (optional)
        myans.setDateColor(Color.argb(255, 0, 0, 0));
// To set row Title text color (optional)
        myans.setTitleColor(Color.argb(255, 0, 0, 0));
// To set row Description text color (optional)
        myans.setDescriptionColor(Color.argb(255, 0, 0, 0));
        FeedObject feed_one=new FeedObject();
        feed_one.setSubject("DIA MIRZA");
        feed_one.setAnsweramount("$10");
        feed_one.setLast_answered("Answered 53 min ago,");
        feed_one.setViewcount("<font color='#28c083'>121 </font> views");
        feed_one.setShortdescription("The publicity of the film is low key. Are you that confident about people watching it or is" +
                "it a strategy?");
        Person askedby=new Person();
        askedby.setName("Rahul Sharma");
        askedby.setLocation("New Delhi");
        askedby.setProfilethumb((getContext().getResources().getDrawable(R.drawable.smallprofile)));
        feed_one.setAskedBy(askedby);
        feed_one.setFavcount(93);
        feed_one.setSharecount(84);
        feed_one.setCommentcount(70);
        feed_one.setThumbnail(getContext().getResources().getDrawable(R.drawable.card_thumb_image));
        feed_one.setButtonback(R.drawable.circle_green);
        feed_one.setTextback(R.drawable.rounded_text_green);
        feed_one.setTextcolor(0x28c083);
        feed_one.setTextamtback(R.drawable.rounded_text_amt_green);
        myans.setFeed(feed_one);

// Add the new row to the list
        timelineRowsList.add(myRow);
        timelineRowsList.add(myans);

        ProfileOddRowAdapter mAdapter = new ProfileOddRowAdapter(getContext(),timelineRowsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        profle_recycle_view.setLayoutManager(mLayoutManager);
        profle_recycle_view.setItemAnimator(new DefaultItemAnimator());
        profle_recycle_view.setAdapter(mAdapter);
        profle_recycle_view.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        profle_recycle_view.addItemDecoration(itemDecoration);




    }

    @Override
    public void onDestroy() {


        super.onDestroy();
    }

    private void initUi() {
        appBarLayout.addOnOffsetChangedListener(this);

        toolbarHeaderView.bindTo(currentUser.getName(), "NA",currentUser.getProfilePic());
        floatHeaderView.bindTo(currentUser.getName(), "NA",currentUser.getProfilePic());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mcallback = (ProfileCallbackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FeedCallbackInterface");
        }
    }



    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

    public interface ProfileCallbackListener
{
    public void showAskQuestionFragment();
}
}
