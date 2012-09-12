package com.calderonpale.lectorbunsen;

import java.util.regex.Pattern;

import net.bytten.comicviewer.IComicDefinition;
import net.bytten.comicviewer.IComicProvider;

import android.net.Uri;

public class BunsenComicDefinition implements IComicDefinition {

    static public final Pattern
        bunsenHomePattern = Pattern.compile(
            "http://(www\\.)?heroeslocales\\.com(/)?"),
        comicUrlPattern = Pattern.compile(
            "http://(www\\.)?heroeslocales\\.com/bunsen//([0-9]+)(/)?"),
        archiveUrlPattern = Pattern.compile(
            "http://(www\\.)?heroeslocales\\.com/bunsen/category/comics(/)?");

    private BunsenComicProvider provider;
    
    public BunsenComicDefinition() {
        provider = new BunsenComicProvider(this);
    }
    
    @Override
    public Uri getArchiveUrl() {
        return Uri.parse("http://www.heroeslocales.com/bunsen/category/comics");
    }

    @Override
    public String getAuthorLinkText() {
        return "Web oficial de Bunsen";
    }

    @Override
    public Uri getAuthorLinkUrl() {
        return Uri.parse("www.heroeslocales.com/bunsen");
    }

    @Override
    public String getAuthorName() {
        return "";
    }

    @Override
    public String getComicTitle() {
        return "bunsen";
    }

    @Override
    public String getComicTitleAbbrev() {
        return "bunsen";
    }

    @Override
    public String getPackageName() {
        return "com.calderonpale.lectorbunsen";
    }

    @Override
    public boolean hasAltText() {
        return true;
    }

    @Override
    public boolean idsAreNumbers() {
        return true;
    }

    @Override
    public boolean isArchiveUrl(Uri url) {
        return archiveUrlPattern.matcher(url.toString()).matches();
    }
    
    @Override
    public boolean isComicUrl(Uri url) {
        return comicUrlPattern.matcher(url.toString()).matches();
    }

    @Override
    public boolean isHomeUrl(Uri url) {
        return bunsenHomePattern.matcher(url.toString()).matches();
    }

    @Override
    public IComicProvider getProvider() {
        return provider;
    }

    @Override
    public Uri getDonateUrl() {
        return Uri.parse("");
    }

}
