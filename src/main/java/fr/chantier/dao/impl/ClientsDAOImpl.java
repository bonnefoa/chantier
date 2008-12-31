package fr.chantier.dao.impl;

import fr.chantier.dao.ClientsDAO;
import fr.chantier.model.ClientsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientsDAOImpl extends GenericHibernateDAO<ClientsEntity, Integer> implements ClientsDAO {
    public ClientsDAOImpl(Session session) {
        super(session);
    }

    public Collection<ClientsEntity> findAllExisting() {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("clientOld", false));
        crit.addOrder(Order.asc("clientName"));
        return new LinkedHashSet(crit.list());
    }

    public Collection<ClientsEntity> findByName(String input) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("clientOld", false));
        crit.add(Restrictions.ilike("clientName", input + "%"));
        crit.addOrder(Order.asc("clientName"));
        return new LinkedHashSet(crit.list());
    }

    public ClientsEntity findClientByName(String inputSearch) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("clientOld", false));
        crit.add(Restrictions.eq("clientName", inputSearch));
        return (ClientsEntity) crit.uniqueResult();
    }
}
