package br.com.zonework.keeptoo.base.abstracsClasse;

import br.com.zonework.keeptoo.properties.PersistenceUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractRepository<E extends AbstractEntity> {
    @PersistenceContext
    private SessionFactory sessionFactory;
    private Class<E> persistenceClass;

    public AbstractRepository(Class<E> persistenceClass) {
        sessionFactory = PersistenceUtils.getInstance().getSessionFactory();
        this.persistenceClass = persistenceClass;
    }

    public void save(E entity) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(entity);
        session.close();
    }

    public void delete(E entity) {
        Session session = sessionFactory.openSession();

        session.delete(entity);
        session.close();
    }

    public E findBy(Long id) {
        Session session = sessionFactory.openSession();

        E entity = session.find(persistenceClass, id);
        session.close();

        return entity;
    }

    public List<E> findAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(persistenceClass);
        criteria.from(persistenceClass);
        List<E> entities = session.createQuery(criteria).getResultList();

        session.close();

        return entities;
    }
}
