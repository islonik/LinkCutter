package org.linkcutter.impl.model;

import javax.persistence.*;

import java.util.Date;

/**
 * @author Lipatov Nikita
 */
@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue
    private Long id;

    private String url;
    private String shortUrl;
    private Date creationDate;

    public Link() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
