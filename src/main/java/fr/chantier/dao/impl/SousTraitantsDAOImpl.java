package fr.chantier.dao.impl;

import fr.chantier.model.*;
import fr.chantier.dao.*;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SousTraitantsDAOImpl extends GenericHibernateDAO<SousTraitantsEntity, Integer> implements SousTraitantsDAO {

    public SousTraitantsDAOImpl(Session session) {
        super(session);
    }

    public Collection<SousTraitantsEntity> findAllExisting() {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("stOld", false));
        crit.addOrder(Order.asc("stName"));
        return new LinkedHashSet<SousTraitantsEntity>(crit.list());
    }

    public Collection<SousTraitantsEntity> findSousTraitantsForCommandes(CommandesEntity commandesEntity) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("stOld", false));
        crit.createCriteria("historiqueSommesByStId").add(Restrictions.eq("commandesByCommandId.commandId", commandesEntity.getCommandId()));
        crit.addOrder(Order.asc("stName"));
        return new LinkedHashSet<SousTraitantsEntity>(crit.list());
    }
}