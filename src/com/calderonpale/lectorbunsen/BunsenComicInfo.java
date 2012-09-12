package com.calderonpale.lectorbunsen;

import net.bytten.comicviewer.IComicInfo;
import android.net.Uri;

public class BunsenComicInfo implements IComicInfo {

    public Uri img;
    public int num;
    public String title = "", alt = "";
    public boolean bookmarked;

    @Override
    public String getAlt() {
        return alt;
    }

    @Override
    public String getId() {
        return Integer.toString(num);
    }

    @Override
    public Uri getImage() {
        return img;
    }

    @Override
    public String getNextId() {
        int n = num + 1;

        return Integer.toString(n);
    }

    @Override
    public String getPrevId() {
        int n = num - 1;

        return Integer.toString(n);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return "http://heroeslocales.com/bunsen/"+getId()+"/";
    }

    @Override
    public boolean isBookmarked() {
        return bookmarked;
    }

    @Override
    public void setBookmarked(boolean b) {
        bookmarked = b;
    }

}
