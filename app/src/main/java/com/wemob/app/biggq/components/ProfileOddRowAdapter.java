package com.wemob.app.biggq.components;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.wemob.app.biggq.R;
import com.wemob.app.biggq.animation.MBounceInterpolator;
import com.wemob.app.biggq.data.FeedObject;
import com.wemob.app.biggq.utils.FontUtil;

/**
 * Created by admin on 9/6/2017.
 */

public class ProfileOddRowAdapter  extends RecyclerView.Adapter<ProfileOddRowAdapter.ProfileOddViewHolder> {

    private Context mcontext;
    private Resources res;
    private List<TimelineRow> RowDataList;
    private String AND;
    private Animation bounceanim;
    MBounceInterpolator interpolator;
    FontUtil fontutil;
    public static final int ITEM_TYPE_QUESTION = 0;
    public static final int ITEM_TYPE_ANSWER = 1;
    public ProfileOddRowAdapter(Context context, List<TimelineRow> rowdata) {
        RowDataList = rowdata;
        mcontext=context;
        fontutil=new FontUtil(mcontext);
    }



    @Override
    public ProfileOddViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==ITEM_TYPE_QUESTION)
        {
            View  itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ctimeline_row, parent, false);
            return new ProfileOddViewHolder(itemView,viewType);
        }
        else
        {
            View  itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ctimeline_ans_row, parent, false);
            return new ProfileOddViewHolder(itemView,viewType);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if ((position+1)%2!=0) {
            return ITEM_TYPE_QUESTION;
        } else {
            return ITEM_TYPE_ANSWER;
        }
    }

    @Override
    public void onBindViewHolder(ProfileOddViewHolder holder, int position) {
        TimelineRow row = RowDataList.get(position);
        final int itemType = getItemViewType(position);


        if(itemType==ITEM_TYPE_QUESTION)
        {
             final float scale = mcontext.getResources().getDisplayMetrics().density;

            holder.rowDate.setText("datetext");
            if (row.getDateColor() != 0)
                holder.rowDate.setTextColor(row.getDateColor());

            if (row.getDescription() == null)
                holder.rowDescription.setVisibility(View.GONE);
            else {
                holder.rowDescription.setText(row.getDescription());
                if (row.getDescriptionColor() != 0)
                    holder.rowDescription.setTextColor(row.getDescriptionColor());
            }





        }
        else
        {



            final float scale = mcontext.getResources().getDisplayMetrics().density;

            holder.ansrowDate.setText("Date Text");
            if (row.getDateColor() != 0)
                holder.ansrowDate.setTextColor(row.getDateColor());

            FeedObject feed = RowDataList.get(position).getFeed();
            holder.subject.setText(feed.getSubject());
            holder.subject.setTextSize(14.0f);
            holder.subject.setBackgroundResource(feed.getTextback());
            holder.amount.setText(feed.getAnsweramount());
            holder.amount.setTextSize(14.0f);
            holder.amount.setBackgroundResource(feed.getTextamtback());
            holder.feedthumbnail.setImageDrawable(feed.getThumbnail());
            holder.favourite.setText(feed.getFavcount()+"");
            holder.share.setText(feed.getSharecount()+"");
            holder.comment.setText(feed.getCommentcount()+"");
            holder.imgplay.setBackgroundResource(feed.getButtonback());
            holder.imgplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.startAnimation(bounceanim);


                }
            });






        }




    }

    @Override
    public int getItemCount() {
        return RowDataList.size();
    }

    public class ProfileOddViewHolder extends RecyclerView.ViewHolder {
        TextView ansrowDate;

        View backgroundView,ansbackgroundView;
        TextView rowDate,rowDescription;
        ImageView feedthumbnail;
        TextView subject;
        TextView amount ;
        TextView favourite;
        TextView share;
        TextView comment;
        ImageButton imgplay;



        public ProfileOddViewHolder(View convertView,int itemType) {
            super(convertView);
            rowDate = (TextView) convertView.findViewById(R.id.crowDate);
            rowDescription = (TextView) convertView.findViewById(R.id.crowDesc);
            ansrowDate = (TextView) convertView.findViewById(R.id.anscrowDate);

            if(itemType==1) {

                feedthumbnail = (ImageView) convertView.findViewById(R.id.imgthumbnail);
                subject = (TextView) convertView.findViewById(R.id.txtsubject);
                amount = (TextView) convertView.findViewById(R.id.txtamount);
                favourite = (TextView) convertView.findViewById(R.id.txtfav);
                share = (TextView) convertView.findViewById(R.id.txtshare);
                comment = (TextView) convertView.findViewById(R.id.txtcomment);
                imgplay = (ImageButton) convertView.findViewById(R.id.imgplay);
            }

        }
    }

//    private String getPastTime(Date date) {
//
//        if (date == null) return "";
//        StringBuilder dateText = new StringBuilder();
//        Date today = new Date();
//        long diff = (today.getTime() - date.getTime()) / 1000;
//
//        long years = diff / (60 * 60 * 24 * 30 * 12);
//        long months = (diff / (60 * 60 * 24 * 30)) % 12;
//        long days = (diff / (60 * 60 * 24)) % 30;
//        long hours = (diff / (60 * 60)) % 24;
//        long minutes = (diff / 60) % 60;
//        long seconds = diff % 60;
//
//        if (years > 0) {
//            appendPastTime(dateText, years, R.plurals.years, months, R.plurals.months);
//        } else if (months > 0) {
//            appendPastTime(dateText, months, R.plurals.months, days, R.plurals.days);
//        } else if (days > 0) {
//            appendPastTime(dateText, days, R.plurals.days, hours, R.plurals.hours);
//        } else if (hours > 0) {
//            appendPastTime(dateText, hours, R.plurals.hours, minutes, R.plurals.minutes);
//        } else if (minutes > 0) {
//            appendPastTime(dateText, minutes, R.plurals.minutes, seconds, R.plurals.seconds);
//        } else if (seconds >= 0) {
//            dateText.append(res.getQuantityString(R.plurals.seconds, (int) seconds, (int) seconds));
//        }
//
//        return dateText.toString();
//    }
//
//    private void appendPastTime(StringBuilder s,
//                                long timespan, int nameId,
//                                long timespanNext, int nameNextId) {
//
//        s.append(res.getQuantityString(nameId, (int) timespan, timespan));
//        if (timespanNext > 0) {
//            s.append(' ').append(AND).append(' ');
//            s.append(res.getQuantityString(nameNextId, (int) timespanNext, timespanNext));
//        }
//    }
//
//    private ArrayList<TimelineRow> rearrangeByDate(ArrayList<TimelineRow> objects) {
//        if (objects.get(0) == null) return objects;
//        int size = objects.size();
//        for (int i = 0; i < size - 1; i++) {
//            for (int j = i + 1; j < size; j++) {
//                if(objects.get(i).getDate()!= null && objects.get(j).getDate() != null)
//                    if (objects.get(i).getDate().compareTo(objects.get(j).getDate()) <= 0)
//                        Collections.swap(objects, i, j);
//            }
//
//        }
//        return objects;
//    }
}

