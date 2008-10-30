package fr.chantier.service.impl;

import fr.chantier.service.GenericManager;
import fr.chantier.dao.GenericDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 3:26:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericHibernateManager<T, ID extends Serializable, DAO extends GenericDAO>
        implements GenericManager<T, ID, DAO> {

    protected DAO dao;

    public GenericHibernateManager(DAO dao) {
        this.dao = dao;
    }

    public T findById(ID id, boolean lock) {
        return (T) dao.findById(id, lock);
    }

    public Collection<T> findAll() {
        return dao.findAll();
    }

    public T makePersistent(T entity) {
        return (T) dao.makePersistent(entity);
    }

    public void makeTransient(T entity) {
        dao.makeTransient(entity);
    }
}
