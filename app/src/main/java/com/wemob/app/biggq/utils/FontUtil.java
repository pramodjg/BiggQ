package com.wemob.app.biggq.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by admin on 8/31/2017.
 */

public class FontUtil {
    private Context mcontext;
    private Typeface comfortaabold;
    private Typeface comfortaalight;
    private Typeface comfortaaregular;
    private Typeface latblackitalic;
    private Typeface latoblack;
    private Typeface latobold;
    private Typeface latbolditalic;
    private Typeface latohairline;
    private Typeface latohairlineitalic;
    private Typeface latoitalic;
    private Typeface latolight;
    private Typeface latlightitalic;
    private Typeface latoregular;
    private Typeface sourcesansproblack;
    private Typeface sourcesansproblackit;
    private Typeface sourcesansprobold;
    private Typeface sourcesansproboldit;
    private Typeface sourcesansproit;
    private Typeface sourcesansprolight;
    private Typeface sourcesansprolightit;
    private Typeface sourcesansproregular;
    private Typeface sourcesansprosemibold;
    private Typeface sourcesansprosemiboldit;
    private Typeface sourcesansproxtralight;
    private Typeface sourcesansproxtralightit;
    public FontUtil(Context context)
    {
        mcontext=context;
        comfortaabold=Typeface.createFromAsset(mcontext.getAssets(), "fonts/comfortaabold.ttf");
        comfortaalight=Typeface.createFromAsset(mcontext.getAssets(), "fonts/comfortaalight.ttf");
        comfortaaregular=Typeface.createFromAsset(mcontext.getAssets(), "fonts/comfortaaregular.ttf");
        latblackitalic=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latblackitalic.ttf");
        latoblack=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latoblack.ttf");
        latobold=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latobold.ttf");
        latbolditalic=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latobolditalic.ttf");
        latohairline=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latohairline.ttf");
        latohairlineitalic=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latohairlineitalic.ttf");
        latoitalic=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latoitalic.ttf");
        latolight=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latolight.ttf");
        latlightitalic=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latolightitalic.ttf");
        latoregular=Typeface.createFromAsset(mcontext.getAssets(), "fonts/latoregular.ttf");
        sourcesansproblack=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproblack.otf");
        sourcesansproblackit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproblackit.otf");
        sourcesansprobold=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansprobold.otf");
        sourcesansproboldit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproboldit.otf");
        sourcesansproit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproit.otf");
        sourcesansprolight=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansprolight.otf");
        sourcesansprolightit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansprolightit.otf");
        sourcesansproregular=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproregular.otf");
        sourcesansprosemibold=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansprosemibold.otf");
        sourcesansprosemiboldit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansprosemiboldit.otf");
        sourcesansproxtralight=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproxtralight.otf");
        sourcesansproxtralightit=Typeface.createFromAsset(mcontext.getAssets(), "fonts/sourcesansproxtralightit.otf");
    }

    public Context getMcontext() {
        return mcontext;
    }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    public Typeface getComfortaabold() {
        return comfortaabold;
    }

    public void setComfortaabold(Typeface comfortaabold) {
        this.comfortaabold = comfortaabold;
    }

    public Typeface getComfortaalight() {
        return comfortaalight;
    }

    public void setComfortaalight(Typeface comfortaalight) {
        this.comfortaalight = comfortaalight;
    }

    public Typeface getComfortaaregular() {
        return comfortaaregular;
    }

    public void setComfortaaregular(Typeface comfortaaregular) {
        this.comfortaaregular = comfortaaregular;
    }

    public Typeface getLatblackitalic() {
        return latblackitalic;
    }

    public void setLatblackitalic(Typeface latblackitalic) {
        this.latblackitalic = latblackitalic;
    }

    public Typeface getLatoblack() {
        return latoblack;
    }

    public void setLatoblack(Typeface latoblack) {
        this.latoblack = latoblack;
    }

    public Typeface getLatobold() {
        return latobold;
    }

    public void setLatobold(Typeface latobold) {
        this.latobold = latobold;
    }

    public Typeface getLatbolditalic() {
        return latbolditalic;
    }

    public void setLatbolditalic(Typeface latbolditalic) {
        this.latbolditalic = latbolditalic;
    }

    public Typeface getLatohairline() {
        return latohairline;
    }

    public void setLatohairline(Typeface latohairline) {
        this.latohairline = latohairline;
    }

    public Typeface getLatohairlineitalic() {
        return latohairlineitalic;
    }

    public void setLatohairlineitalic(Typeface latohairlineitalic) {
        this.latohairlineitalic = latohairlineitalic;
    }

    public Typeface getLatoitalic() {
        return latoitalic;
    }

    public void setLatoitalic(Typeface latoitalic) {
        this.latoitalic = latoitalic;
    }

    public Typeface getLatolight() {
        return latolight;
    }

    public void setLatolight(Typeface latolight) {
        this.latolight = latolight;
    }

    public Typeface getLatlightitalic() {
        return latlightitalic;
    }

    public void setLatlightitalic(Typeface latlightitalic) {
        this.latlightitalic = latlightitalic;
    }

    public Typeface getLatoregular() {
        return latoregular;
    }

    public void setLatoregular(Typeface latoregular) {
        this.latoregular = latoregular;
    }

    public Typeface getSourcesansproblack() {
        return sourcesansproblack;
    }

    public void setSourcesansproblack(Typeface sourcesansproblack) {
        this.sourcesansproblack = sourcesansproblack;
    }

    public Typeface getSourcesansproblackit() {
        return sourcesansproblackit;
    }

    public void setSourcesansproblackit(Typeface sourcesansproblackit) {
        this.sourcesansproblackit = sourcesansproblackit;
    }

    public Typeface getSourcesansprobold() {
        return sourcesansprobold;
    }

    public void setSourcesansprobold(Typeface sourcesansprobold) {
        this.sourcesansprobold = sourcesansprobold;
    }

    public Typeface getSourcesansproboldit() {
        return sourcesansproboldit;
    }

    public void setSourcesansproboldit(Typeface sourcesansproboldit) {
        this.sourcesansproboldit = sourcesansproboldit;
    }

    public Typeface getSourcesansproit() {
        return sourcesansproit;
    }

    public void setSourcesansproit(Typeface sourcesansproit) {
        this.sourcesansproit = sourcesansproit;
    }

    public Typeface getSourcesansprolight() {
        return sourcesansprolight;
    }

    public void setSourcesansprolight(Typeface sourcesansprolight) {
        this.sourcesansprolight = sourcesansprolight;
    }

    public Typeface getSourcesansprolightit() {
        return sourcesansprolightit;
    }

    public void setSourcesansprolightit(Typeface sourcesansprolightit) {
        this.sourcesansprolightit = sourcesansprolightit;
    }

    public Typeface getSourcesansproregular() {
        return sourcesansproregular;
    }

    public void setSourcesansproregular(Typeface sourcesansproregular) {
        this.sourcesansproregular = sourcesansproregular;
    }

    public Typeface getSourcesansprosemibold() {
        return sourcesansprosemibold;
    }

    public void setSourcesansprosemibold(Typeface sourcesansprosemibold) {
        this.sourcesansprosemibold = sourcesansprosemibold;
    }

    public Typeface getSourcesansprosemiboldit() {
        return sourcesansprosemiboldit;
    }

    public void setSourcesansprosemiboldit(Typeface sourcesansprosemiboldit) {
        this.sourcesansprosemiboldit = sourcesansprosemiboldit;
    }

    public Typeface getSourcesansproxtralight() {
        return sourcesansproxtralight;
    }

    public void setSourcesansproxtralight(Typeface sourcesansproxtralight) {
        this.sourcesansproxtralight = sourcesansproxtralight;
    }

    public Typeface getSourcesansproxtralightit() {
        return sourcesansproxtralightit;
    }

    public void setSourcesansproxtralightit(Typeface sourcesansproxtralightit) {
        this.sourcesansproxtralightit = sourcesansproxtralightit;
    }
}
