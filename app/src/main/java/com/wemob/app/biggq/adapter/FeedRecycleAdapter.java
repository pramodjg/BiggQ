package com.wemob.app.biggq.adapter;

/**
 * Created by admin on 8/17/2017.
 */

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.wemob.app.biggq.BiggQ;
import com.wemob.app.biggq.HomePageWrapper;
import com.wemob.app.biggq.R;
import com.wemob.app.biggq.VideoScreen;
import com.wemob.app.biggq.animation.MBounceInterpolator;
import com.wemob.app.biggq.apiHandler.ApiLinks;
import com.wemob.app.biggq.data.FeedObject;
import com.wemob.app.biggq.utils.FontUtil;

public class FeedRecycleAdapter extends RecyclerView.Adapter<FeedRecycleAdapter.FeedViewHolder> {

    private List<FeedObject> feedList;
    private int backgroundDrawable;
    private int backgroundImageDrawable;
    private Context mcontext;
    boolean pressed;
    private Animation bounceanim;
    MBounceInterpolator interpolator;
    FontUtil fontutil;
    int currentPosition=0;

    HomePageWrapper parentobj;


    public class FeedViewHolder extends RecyclerView.ViewHolder {
        public TextView title, askedcaption, subject,amount,answeredby,viewcount,content,askedby,askedloc,favourite,share, comment;
        public ImageView smallprofthumbnail,feedthumbnailimg;
        public VideoView feedthumbnail;
        public ImageButton imgplay;


        public FeedViewHolder(View view) {
            super(view);
            bounceanim = AnimationUtils.loadAnimation(mcontext, R.anim.bounce);


            // Use bounce interpolator with amplitude 0.2 and frequency 20
            interpolator = new MBounceInterpolator(0.1, 10);
            bounceanim.setInterpolator(interpolator);
            bounceanim.setDuration(300);
            bounceanim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    callVideoView();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            feedthumbnail = (VideoView) view.findViewById(R.id.imgthumbnail);
            feedthumbnailimg = (ImageView) view.findViewById(R.id.imgthumbnailview);
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




        }
    }


    public FeedRecycleAdapter(Context context, List<FeedObject> feedList, HomePageWrapper parent) {
        this.feedList = feedList;
        mcontext=context;
        fontutil=new FontUtil(mcontext);
        parentobj=parent;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_card_view, parent, false);

        return new FeedViewHolder(itemView);
    }

    public void setDrawable(int drawable)
    {
        backgroundDrawable=drawable;
    }
    public void setImageDrawable(int drawable)
    {
        backgroundImageDrawable=drawable;
    }
    @Override
    public void onBindViewHolder(final FeedViewHolder holder, final int position) {



        FeedObject feed = feedList.get(position);
        holder.subject.setText(feed.getSubject());
        holder.subject.setTextSize(14.0f);
        holder.subject.setBackgroundResource(feed.getTextback());
        holder.amount.setText(feed.getAnsweramount());
        holder.amount.setTextSize(14.0f);
        holder.amount.setBackgroundResource(feed.getTextamtback());
        holder.feedthumbnail.setVideoURI(Uri.parse(ApiLinks.videobasepath+feed.getAnswervideo()));
        holder.answeredby.setText(feed.getLast_answered());
        holder.answeredby.setTextSize(12.0f);
        holder.viewcount.setText(Html.fromHtml(feed.getViewcount()));
        holder.viewcount.setTextSize(12.0f);
        holder.content.setText(feed.getShortdescription());
        holder.content.setTextSize(16.0f);
        holder.askedby.setText(feed.getAskedBy().getName());
        holder.askedby.setTextSize(14.0f);
        holder.askedloc.setText(feed.getAskedBy().getLocation());
        holder.askedloc.setTextSize(12.0f);
        holder.askedcaption.setTextSize(12.0f);
        holder.favourite.setText(feed.getFavcount()+"");
        holder.share.setText(feed.getSharecount()+"");
        holder.comment.setText(feed.getCommentcount()+"");


        Glide.with(parentobj).load(ApiLinks.baseURL+feed.getCoverPic()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.feedthumbnailimg));

       // Glide.with(parentobj).load(ApiLinks.videobasepath+feedList.get(currentPosition).getAnswervideo()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.feedthumbnailimg));
        holder.smallprofthumbnail.setImageDrawable(feed.getAskedBy().getProfilethumb());
        if(!feed.getAskedBy().getProfileImage().equals("NA")) {
            Glide.with(parentobj).load(ApiLinks.baseURL+"img-profile/"+feed.getAskedBy().getProfileImage()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.smallprofthumbnail) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(parentobj.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    holder.smallprofthumbnail.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
        int color=Color.parseColor(feed.getColor_code().trim());
        Drawable mDrawable=parentobj.getResources().getDrawable(R.drawable.circle_green);
        Drawable mDrawableTextName=parentobj.getResources().getDrawable(R.drawable.rounded_text_green);
        Drawable mDrawableAmount=parentobj.getResources().getDrawable(R.drawable.rounded_text_amt_green);
        mDrawableTextName.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));

        mDrawableAmount.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));

        mDrawable.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
        holder.imgplay.setBackground(mDrawable);



        holder.subject.setTypeface(fontutil.getLatobold());
        holder.subject.setBackground(mDrawableTextName);
        holder.amount.setTypeface(fontutil.getLatobold());
        holder.amount.setBackground(mDrawableAmount);
        holder.answeredby.setTypeface(fontutil.getSourcesansprolightit());
        holder.viewcount.setTypeface(fontutil.getLatoregular());
        holder.content.setTypeface(fontutil.getSourcesansproregular());
        holder.askedcaption.setTypeface(fontutil.getSourcesansprolightit());
        holder.favourite.setTypeface(fontutil.getLatoregular());
        holder.share.setTypeface(fontutil.getLatoregular());
        holder.comment.setTypeface(fontutil.getLatoregular());
        holder.imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(bounceanim);
                currentPosition=position;


            }
        });

//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }



    @Override
    public int getItemCount() {
        return feedList.size();
    }

private void callVideoView() {
    ArrayList purchasers=feedList.get(currentPosition).getPurchased();
    BiggQ appObj=(BiggQ)parentobj.getApplication();
    for(int i=0;i<purchasers.size();i++)
    {
        if(!appObj.getCurrentUser().getId().trim().toLowerCase().equals(purchasers.get(i).toString().trim().toLowerCase()))
        {
            showAlert(feedList.get(currentPosition).getAnsweramount());
        }
        else
        {
            Intent videointent=new Intent(mcontext, VideoScreen.class);
            videointent.putExtra("videolink",ApiLinks.videobasepath+feedList.get(currentPosition).getAnswervideo());
            mcontext.startActivity(videointent);

        }

    }


}

    private void showAlert(String answeramount) {
        parentobj.showConfirmationDialog(answeramount);
    }

}

