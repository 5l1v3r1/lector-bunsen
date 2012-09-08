package com.calderonpale.lectorbunsen;


import net.bytten.comicviewer.ArchiveActivity;
import net.bytten.comicviewer.IComicDefinition;

public class BunsenArchiveActivity extends ArchiveActivity {

    @Override
    protected IComicDefinition makeComicDef() {
        return new BunsenComicDefinition();
    }

    @Override
    protected String getStringArchive() {
        return null;
    }

    @Override
    protected String getStringBookmarks() {
        return getResources().getString(R.string.app_bookmarks_label);
    }

    @Override
    protected String getStringSearchByTitle() {
        return null;
    }

}
