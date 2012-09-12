package com.calderonpale.lectorbunsen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bytten.comicviewer.ArchiveData.ArchiveItem;
import net.bytten.comicviewer.IComicInfo;
import net.bytten.comicviewer.IComicProvider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.net.Uri;
import android.util.Log;

public class BunsenComicProvider implements IComicProvider {

    private static final String ARCHIVE_URL = "http://www.heroeslocales.com/bunsen/category/comics/";
    private static final String COMIC_URL ="http://www.heroeslocales.com/bunsen/";
    private BunsenComicDefinition def;
    
    private int topId;
    private String currentId;
    public BunsenComicProvider(BunsenComicDefinition def) {
        this.def = def;
    }

    @Override
    public Uri comicDataUrlForUrl(Uri url) {
        return null;
    }

    @Override
    public Uri createComicUrl(String comicId) {
        currentId=comicId;
        return Uri.parse(COMIC_URL+comicId);  
    }


    private static String imageFromUrl(Document doc) {
     
            Elements posts = doc.select("#comic");
            Log.d("bunsen","posts size "+posts.size());
            return posts.get(0).childNode(0).attr("src");        
    }

    @Override
    public IComicInfo fetchComicInfo(Uri url) throws Exception {
        
        Document doc = Jsoup.connect(url.toString()).timeout(30 * 1000).get();
        String img=imageFromUrl(doc);
        BunsenComicInfo data = new BunsenComicInfo();
        data.img=Uri.parse(img);
        data.num=Integer.parseInt(currentId);
        data.title=currentId;
        return data;
    }

    @Override
    public Uri fetchRandomComicUrl() throws Exception {

        Integer ranInteger= (int)(Math.random()*topId);
        return createComicUrl(ranInteger+"");
        
    }

    @Override
    public Uri getFinalComicUrl() {
       topId = 300;
        try {
        	InputStream input = new URL("http://www.google.com/reader/api/0/stream/contents/feed/http://feeds.feedburner.com/Bunsen?c=default&n=1").openStream();

            final Pattern pattern = Pattern.compile("\"title\":\"(.+?)\",\"published");
            String theString = convertStreamToString(input);
            final Matcher matcher = pattern.matcher(theString);
            matcher.find();
            topId = Integer.parseInt(matcher.group(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return createComicUrl(topId+"");
    }

    @Override
    public String getFirstId() {
        return "1";
    }

    @Override
    public IComicInfo createEmptyComicInfo() {
        return new BunsenComicInfo();
    }

    @Override
    public List<ArchiveItem> fetchArchive() throws Exception {
        List<ArchiveItem> archiveItems = new ArrayList<ArchiveItem>();
        return archiveItems;
    }
    public String convertStreamToString(InputStream is) throws IOException {
    	if (is != null) {
    		Writer writer = new StringWriter();

    		char[] buffer = new char[1024];
    		try {
    			Reader reader = new BufferedReader(
    					new InputStreamReader(is, "UTF-8"));
    			int n;
    			while ((n = reader.read(buffer)) != -1) {
    				writer.write(buffer, 0, n);
    			}
    		} finally {
    		is.close();
    	}
    	return writer.toString();
    	} else {        
    		return "";
    	}
    }
}
