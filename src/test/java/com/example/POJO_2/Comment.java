package com.example.POJO_2;

import java.util.List;

import com.example.POJO_2.Comments;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private List<Comments> comments;
    int maxResults;
    int total;
    int startAt;

    public List<Comments> getComments() {
        return this.comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartAt() {
        return this.startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }
}
