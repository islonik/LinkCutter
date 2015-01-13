package org.linkcutter.impl.dao.impl;

import org.linkcutter.impl.dao.LinksDao;
import org.linkcutter.impl.model.Link;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

/**
 * @author Lipatov Nikita
 */
@Component
public class LinksDaoImpl implements LinksDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEm() {
        return entityManager;
    }

    @PersistenceContext
    protected void setEm(EntityManager em) {
        this.entityManager = em;
    }

    @Transactional
    public void create(Link link) {
        entityManager.persist(link);
    }

    @Transactional
    public void update(Link link) {
        entityManager.merge(link);
    }

    @Transactional
    public void remove(Long entityId) {
        Link entity = find(entityId);

        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public Link find(Object id) {
        return entityManager.find(Link.class, id);
    }

    public Link findByUrl(String url) {
        return entityManager.createQuery("select l from Link l where l.url = :url", Link.class)
                .setParameter("url", url)
                .getSingleResult();
    }

    public Link findByShortUrl(String shortUrl) {
        return entityManager.createQuery("select l from Link l where l.shortUrl = :shortUrl", Link.class)
                .setParameter("shortUrl", shortUrl)
                .getSingleResult();
    }

    public List<Link> findAll() {
        CriteriaQuery<Link> cq = entityManager.getCriteriaBuilder()
                .createQuery(Link.class);
        cq.select(cq.from(Link.class));

        return entityManager.createQuery(cq).getResultList();
        //return em.get().createQuery("SELECT l FROM Link l", Link.class).getResultList();
    }
}
