package fr.chantier.dao.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.dao.ClientsDAO;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import java.util.Collection;
import java.util.HashSet;

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
        return new HashSet(crit.list());
    }
}
