package fr.chantier.dao.impl;

import fr.chantier.model.*;
import fr.chantier.dao.HistoriqueHeuresDAO;
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
public class HistoriqueHeuresDAOImpl extends GenericHibernateDAO<HistoriqueHeuresEntity, Integer> implements HistoriqueHeuresDAO {

    public HistoriqueHeuresDAOImpl(Session session) {
        super(session);
    }

    public Collection<HistoriqueHeuresEntity> findHistoriqueHeuresByIntervenantAndCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("intervenantsByInterId.interId", intervenantsEntity.getInterId()));
        crit.add(Restrictions.eq("commandesByCommandId.commandId", commandesEntity.getCommandId()));
        crit.addOrder(Order.asc("historiqueDate"));
        return new LinkedHashSet<HistoriqueHeuresEntity>(crit.list());
    }
}