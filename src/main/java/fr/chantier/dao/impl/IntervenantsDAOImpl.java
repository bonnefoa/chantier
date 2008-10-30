package fr.chantier.dao.impl;

import fr.chantier.dao.IntervenantsDAO;
import fr.chantier.model.IntervenantsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
public class IntervenantsDAOImpl extends GenericHibernateDAO<IntervenantsEntity, Integer> implements IntervenantsDAO {

    public IntervenantsDAOImpl(Session session) {
        super(session);
    }

    public Collection<IntervenantsEntity> findAllExisting() {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("interOld", false));
        crit.addOrder(Order.asc("interOrdre"));
        return new HashSet<IntervenantsEntity>(crit.list());
    }

}