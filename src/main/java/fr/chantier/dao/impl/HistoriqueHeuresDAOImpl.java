package fr.chantier.dao.impl;

import fr.chantier.model.*;
import fr.chantier.dao.HistoriqueHeuresDAO;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.HashSet;

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
        return new HashSet<HistoriqueHeuresEntity> (crit.list());
    }
}