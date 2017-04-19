package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by scott on 4/17/2017.
 */
public class SimpleBlogDao implements BlogDao {
    List<BlogEntry> mEntries;

    public boolean addEntry(BlogEntry blogEntry) {
        return mEntries.add(blogEntry);
    }

    public SimpleBlogDao() {
        mEntries = new ArrayList<>();
    }

    public List<BlogEntry> findAllEntries() {
        return mEntries;
    }

    public BlogEntry findEntryBySlug(String slug) {
        return mEntries.stream()
                .filter(idea->idea.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public static void addMockData(SimpleBlogDao dao){
        BlogEntry entry1 = new BlogEntry("The best day I’ve ever had","here's the main article this article is sooooooooooo looong","BestDay");
        Comment comment = new Comment("Bob The Builder","Reminds me of my brick laying days");
        entry1.addComment(comment);
        BlogEntry entry2 = new BlogEntry("The worst day I’ve ever had","here's the main article this article is sooooooooooo looong","WorstDay");
        BlogEntry entry3 = new BlogEntry("The weirdest thing at the mall happened","here's the main article this article is sooooooooooo looong","WeirdMallDay");
        BlogEntry entry4 = new BlogEntry("Dude where's my car?","here's the main article this article is sooooooooooo looong","DudeWheresMyCar");
        dao.addEntry(entry1);
        dao.addEntry(entry2);
        dao.addEntry(entry3);
        dao.addEntry(entry4);
    }

}
