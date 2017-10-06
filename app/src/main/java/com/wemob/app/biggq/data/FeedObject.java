package com.wemob.app.biggq.data;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by admin on 8/17/2017.
 */

public class FeedObject implements Serializable {

    private String subject;
    private Drawable thumbnail;
    private String answeramount;
    private String last_answered;
    private String viewcount;
    private String shortdescription;
    private Person askedBy;
    private int favcount;
    private int sharecount;
    private int commentcount;
    private String celebrityid;
    private String questionamount;
    private String answervideo;
    private int id;
    private int questionStatus;
    private String coverPic;
    private ArrayList<String> purchased;

    public int getTextback() {
        return textback;
    }

    public void setTextback(int textback) {
        this.textback = textback;
    }

    public int getButtonback() {
        return buttonback;
    }

    public void setButtonback(int buttonback) {
        this.buttonback = buttonback;
    }

    public int getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    private int textback;
    private int buttonback;
    private int textcolor;
    private int textamtback;
    private String color_code;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Drawable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAnsweramount() {
        return answeramount;
    }

    public void setAnsweramount(String answeramount) {
        this.answeramount = answeramount;
    }

    public String getLast_answered() {
        return last_answered;
    }

    public void setLast_answered(String last_answered) {
        this.last_answered = last_answered;
    }

    public String getViewcount() {
        return viewcount;
    }

    public void setViewcount(String viewcount) {
        this.viewcount = viewcount;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public Person getAskedBy() {
        return askedBy;
    }

    public void setAskedBy(Person askedBy) {
        this.askedBy = askedBy;
    }

    public int getFavcount() {
        return favcount;
    }

    public void setFavcount(int favcount) {
        this.favcount = favcount;
    }

    public int getSharecount() {
        return sharecount;
    }

    public void setSharecount(int sharecount) {
        this.sharecount = sharecount;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public FeedObject()
    {
        askedBy=new Person();
        purchased=new ArrayList<>();
    }

    public int getTextamtback() {
        return textamtback;
    }

    public void setTextamtback(int textamtback) {
        this.textamtback = textamtback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCelebrityid() {
        return celebrityid;
    }

    public void setCelebrityid(String celebrityid) {
        this.celebrityid = celebrityid;
    }

    public String getQuestionamount() {
        return questionamount;
    }

    public void setQuestionamount(String questionamount) {
        this.questionamount = questionamount;
    }

    public String getAnswervideo() {
        return answervideo;
    }

    public void setAnswervideo(String answervideo) {
        this.answervideo = answervideo;
    }

    public void setQuestionStatus(int questionStatus) {
        this.questionStatus = questionStatus;
    }

    public ArrayList<String> getPurchased() {
        return purchased;
    }

    public void setPurchased(ArrayList<String> purchased) {
        this.purchased = purchased;
    }

    public void seColorCode(String color_code) {
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }
}
