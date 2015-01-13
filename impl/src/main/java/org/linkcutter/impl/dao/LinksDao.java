package org.linkcutter.impl.dao;

import org.linkcutter.impl.model.Link;

import java.util.List;

/**
 * @author Lipatov Nikita
 */
public interface LinksDao {

    public void create(Link link);

    public void update(Link link);

    public void remove(Long entityId);

    public Link find(Object id);

    public Link findByUrl(String url);

    public Link findByShortUrl(String shortUrl);

    public List<Link> findAll();
}
