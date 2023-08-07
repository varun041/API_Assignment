package com.example.POJO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {
    private String self;
    private String id;
    private Author author;
    private String body;
    private UpdateAuthor updateAuthor;
    private String created ;
    private String updated;
    private Visibility visibility;


      public String getSelf() {
        return this.self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UpdateAuthor getUpdateAuthor() {
        return this.updateAuthor;
    }

    public void setUpdateAuthor(UpdateAuthor updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }




}
