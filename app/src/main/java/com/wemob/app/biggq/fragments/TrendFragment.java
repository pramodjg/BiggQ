package com.wemob.app.biggq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

import com.daprlabs.cardstack.SwipeDeck;

import java.util.ArrayList;
import java.util.List;

import com.wemob.app.biggq.HomePageWrapper;
import com.wemob.app.biggq.R;
import com.wemob.app.biggq.data.FeedObject;
import com.wemob.app.biggq.data.Person;
import com.wemob.app.biggq.utils.FontUtil;

public class TrendFragment extends Fragment {
    private SlidingUpPanelLayout mLayout;
    private static final String TAG = "MainActivity";
    private SwipeDeck cardStack;
    private HomePageWrapper parentcontext;

    private SwipeDeckAdapter adapter;
    private ArrayList<String> testData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerview=inflater.inflate(R.layout.trending_page_layout,null);
        return containerview;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardStack = (SwipeDeck) view.findViewById(R.id.swipe_deck);
        cardStack.setHardwareAccelerationEnabled(true);

        testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        adapter = new SwipeDeckAdapter(testData, parentcontext);
        cardStack.setAdapter(adapter);

        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + position);
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + position);
            }

            @Override
            public void cardsDepleted() {
                Log.i("MainActivity", "no more cards");
            }

            @Override
            public void cardActionDown() {
                Log.i(TAG, "cardActionDown");
            }

            @Override
            public void cardActionUp() {
                Log.i(TAG, "cardActionUp");
            }

        });
//        cardStack.setLeftImage(R.id.left_image);
//        cardStack.setRightImage(R.id.right_image);
        mLayout = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
        mLayout.setAnchorPoint(0.15f);
        mLayout.setPanelState(PanelState.ANCHORED);
        mLayout.addPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }

        });
        mLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != PanelState.HIDDEN) {
                        mLayout.setPanelState(PanelState.HIDDEN);

                    }
                    else if(mLayout.getPanelState() != PanelState.EXPANDED)
                    {
                        mLayout.setPanelState(PanelState.EXPANDED);
                    }
                    else {
                        mLayout.setPanelState(PanelState.COLLAPSED);

                    }

            }
                return false;
        }});
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(PanelState.COLLAPSED);
            }
        });
    }

    @Override
    public void onPause() {
        if (mLayout != null &&
                (mLayout.getPanelState() == PanelState.EXPANDED || mLayout.getPanelState() == PanelState.ANCHORED)) {
            mLayout.setPanelState(PanelState.COLLAPSED);
            super.onPause();
        } else {
            super.onPause();
        }
    }


    public HomePageWrapper getParentcontext() {
        return parentcontext;
    }

    public void setParentcontext(HomePageWrapper parentcontext) {
        this.parentcontext = parentcontext;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_swipe_deck, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public class SwipeDeckAdapter extends BaseAdapter {

        private final FontUtil fontutil;
        private List<String> data;
        private HomePageWrapper context;
        FeedObject feed;
        public TextView title, askedcaption, subject,amount,answeredby,viewcount,content,askedby,askedloc,favourite,share, comment;
        public ImageView feedthumbnail,smallprofthumbnail;
        public ImageButton imgplay;
        public SwipeDeckAdapter(List<String> data, HomePageWrapper context) {
            this.data = data;
            this.context = context;
            fontutil=new FontUtil(getContext());
            feed =new FeedObject();
            feed.setSubject("DIA MIRZA");
            feed.setAnsweramount("$10");
            feed.setLast_answered("Answered 53 min ago,");
            feed.setViewcount("<font color='#9b59b6'>121 </font> views");
            feed.setShortdescription("The publicity of the film is low key. Are you that confident about people watching it or is" +
                    "it a strategy?");
            Person askedby=new Person();
            askedby.setName("Rahul Sharma");
            askedby.setLocation("New Delhi");
            askedby.setProfilethumb((getContext().getResources().getDrawable(R.drawable.smallprofile)));
            feed.setAskedBy(askedby);
            feed.setFavcount(93);
            feed.setSharecount(84);
            feed.setCommentcount(70);
            feed.setThumbnail(getContext().getResources().getDrawable(R.drawable.card_thumb_image));
            feed.setButtonback(R.drawable.circle_violet);
            feed.setTextback(R.drawable.rounded_text_violet);
            feed.setTextamtback(R.drawable.rounded_text_amt_violet);
            feed.setTextcolor(0x9b59b6);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = ((AppCompatActivity)getActivity()).getLayoutInflater();
                // normally use a viewholder
                view = inflater.inflate(R.layout.trending_cardview, parent, false);
            }
            feedthumbnail = (ImageView) view.findViewById(R.id.imgthumbnail);
            smallprofthumbnail = (ImageView) view.findViewById(R.id.imgsmallprof);
            subject=(TextView) view.findViewById(R.id.txtsubject);
            answeredby=(TextView) view.findViewById(R.id.txtanswered);
            viewcount=(TextView)view.findViewById(R.id.txtviewcount);
            content=(TextView)view.findViewById(R.id.txtcontent);
            amount = (TextView) view.findViewById(R.id.txtamount);
            askedby = (TextView) view.findViewById(R.id.txtaskpname);
            askedloc= (TextView) view.findViewById(R.id.txtaskpersonloc);
            favourite= (TextView) view.findViewById(R.id.txtfav);
            share= (TextView) view.findViewById(R.id.txtshare);
            comment= (TextView) view.findViewById(R.id.txtcomment);
            imgplay= (ImageButton) view.findViewById(R.id.imgplay);
            askedcaption=(TextView)view.findViewById(R.id.txtaskedby);
            subject.setText(feed.getSubject());
            subject.setTextSize(14.0f);
            subject.setBackgroundResource(feed.getTextback());
            amount.setText(feed.getAnsweramount());
            amount.setTextSize(14.0f);
            amount.setBackgroundResource(feed.getTextamtback());
            feedthumbnail.setImageDrawable(feed.getThumbnail());
            answeredby.setText(feed.getLast_answered());
            answeredby.setTextSize(12.0f);
            viewcount.setText(Html.fromHtml(feed.getViewcount()));
            viewcount.setTextSize(12.0f);
            content.setText(feed.getShortdescription());
            content.setTextSize(16.0f);
            askedby.setText(feed.getAskedBy().getName());
            askedby.setTextSize(14.0f);
            askedloc.setText(feed.getAskedBy().getLocation());
            askedloc.setTextSize(12.0f);
            askedcaption.setTextSize(12.0f);
            favourite.setText(feed.getFavcount()+"");
            share.setText(feed.getSharecount()+"");
            comment.setText(feed.getCommentcount()+"");
            smallprofthumbnail.setImageDrawable(feed.getAskedBy().getProfilethumb());
            imgplay.setBackgroundResource(feed.getButtonback());



            subject.setTypeface(fontutil.getLatobold());
            amount.setTypeface(fontutil.getLatobold());
            answeredby.setTypeface(fontutil.getSourcesansprolightit());
            viewcount.setTypeface(fontutil.getLatoregular());
            content.setTypeface(fontutil.getSourcesansproregular());
            askedcaption.setTypeface(fontutil.getSourcesansprolightit());
            favourite.setTypeface(fontutil.getLatoregular());
            share.setTypeface(fontutil.getLatoregular());
            comment.setTypeface(fontutil.getLatoregular());
            //((TextView) v.findViewById(R.id.textView2)).setText(data.get(position));
//            ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
//            Picasso.with(context).load(R.drawable.food).fit().centerCrop().into(imageView);
//            TextView textView = (TextView) v.findViewById(R.id.sample_text);
//            String item = (String)getItem(position);
//            textView.setText(item);
//
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            return view;
        }
    }
}
