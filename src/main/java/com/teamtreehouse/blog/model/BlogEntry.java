package com.teamtreehouse.blog.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BlogEntry {
    private List<Comment> mComments;
    private String title;
    private String date;
    private String content;
    private String slug;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        return slug != null ? slug.equals(blogEntry.slug) : blogEntry.slug == null;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        return slug != null ? slug.hashCode() : 0;
    }

    public String getSlug() {

        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public BlogEntry(String title, String content, String slug) {
        this.title = title;
        this.date = DateUtil.getTodaysDate();
        this.slug = slug;
        this.content = content;
        this.mComments = new ArrayList<>();
    }

    public boolean addComment(Comment comment) {
        mComments.add(comment);
        return true;
    }
}
