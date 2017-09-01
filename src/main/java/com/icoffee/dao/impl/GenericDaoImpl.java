package com.icoffee.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.icoffee.dao.GenericDao;
import com.icoffee.utils.HibernateSessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private Class<T> clazz;

    GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public PK create(T o) {
        Session session = getSessionAndBeginTransaction();
        @SuppressWarnings("unchecked")
        PK pk =  (PK) session.save(o);
        session.getTransaction().commit();

        return pk;
    }

    @Override
    public T read(PK id) {
        Session session = getSessionAndBeginTransaction();
        T object = session.get(clazz, id);
        session.getTransaction().commit();

        return object;
    }

    @Override
    public void update(T o) {
        Session session = getSessionAndBeginTransaction();
        session.update(o);
        session.getTransaction().commit();
    }

    @Override
    public void delete(T o) {
        Session session = getSessionAndBeginTransaction();
        session.delete(o);
        session.getTransaction().commit();
    }

    @Override
    public List<T> getAll(){
        Session session = getSessionAndBeginTransaction();
        String nameClass = (clazz.getSimpleName());
        Query query = session.createQuery("from " + nameClass);
        @SuppressWarnings("unchecked")
        List<T> list = query.list();
        session.getTransaction().commit();

        return list;
    }

    private Session getSessionAndBeginTransaction(){
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        if(sessionFactory == null){
            throw new RuntimeException("Session factory is null");
        }
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
        }catch (HibernateException e) {
            e.printStackTrace();
        }
        return session;
    }
}
