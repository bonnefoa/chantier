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
public class HistoriqueSommeDAOImpl extends GenericHibernateDAO<HistoriqueSommeEntity, Integer> implements HistoriqueSommeDAO {

    public HistoriqueSommeDAOImpl(Session session) {
        super(session);
    }

    public Collection<HistoriqueSommeEntity> recupererHistoriqueSommeBySousTraitantsAndCommandes(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("sousTraitantsByStId.stId", sousTraitantsEntity.getStId()));
        crit.add(Restrictions.eq("commandesByCommandId.commandId", commandesEntity.getCommandId()));
        crit.addOrder(Order.asc("historiqueDate"));
        return new LinkedHashSet<HistoriqueSommeEntity>(crit.list());
    }
}