package org.linkcutter.impl.dao;

import com.google.inject.ImplementedBy;
import org.linkcutter.impl.dao.impl.LinkDaoImpl;
import org.linkcutter.impl.model.Link;

import java.util.List;

/**
 * @author Lipatov Nikita
 */
@ImplementedBy(LinkDaoImpl.class)
public interface LinkDao {

    public void create(Link link);

    public void update(Link link);

    public void remove(Long entityId);

    public Link find(Object id);

    public List<Link> findByPath(String path);

    public List<Link> findAll();
}
