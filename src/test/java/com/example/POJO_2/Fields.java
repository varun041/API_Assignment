package com.example.POJO_2;

import com.example.POJO_2.Comment;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
    private Comment comment;

    public Comment getComment() {
        return this.comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
