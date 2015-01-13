package org.linkcutter.impl.dao.impl;

import org.linkcutter.impl.dao.LinksDao;
import org.linkcutter.impl.model.Link;

import java.util.List;

import javax.inject.Provider;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import javax.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * @author Lipatov Nikita
 */
@Singleton
public class LinksDaoImpl implements LinksDao {

    @Inject
    private Provider<EntityManager> em;

    protected EntityManager em() {
        return em.get();
    }

    @Inject
    public LinksDaoImpl(Provider<EntityManager> em) {
        this.em = em;
    }

    @Inject
    protected void setEm(Provider<EntityManager> em) {
        this.em = em;
    }

    @Transactional
    public void create(Link link) {
        em.get().persist(link);
    }

    @Transactional
    public void update(Link link) {
        em.get().merge(link);
    }

    @Transactional
    public void remove(Long entityId) {
        Link entity = find(entityId);

        if (entity != null) {
            em.get().remove(entity);
        }
    }

    public Link find(Object id) {
        return em.get().find(Link.class, id);
    }

    public Link findByUrl(String url) {
        return em.get().createQuery("select l from Link l where l.url = :url", Link.class)
                .setParameter("url", url)
                .getSingleResult();
    }

    public Link findByShortUrl(String shortUrl) {
        return em.get().createQuery("select l from Link l where l.shortUrl = :shortUrl", Link.class)
                .setParameter("shortUrl", shortUrl)
                .getSingleResult();
    }

    public List<Link> findAll() {
        CriteriaQuery<Link> cq = em.get().getCriteriaBuilder()
                .createQuery(Link.class);
        cq.select(cq.from(Link.class));

        return em.get().createQuery(cq).getResultList();
        //return em.get().createQuery("SELECT l FROM Link l", Link.class).getResultList();
    }
}
