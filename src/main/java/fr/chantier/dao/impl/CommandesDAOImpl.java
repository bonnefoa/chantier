package fr.chantier.dao.impl;

import fr.chantier.dao.CommandesDAO;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandesDAOImpl extends GenericHibernateDAO<CommandesEntity, Integer> implements CommandesDAO {

    public CommandesDAOImpl(Session session) {
        super(session);
    }

    public CommandesEntity findById(Integer commandId) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("commandId", commandId));
        return (CommandesEntity) crit.uniqueResult();
    }

    public Collection<CommandesEntity> findByCriterions(ClientsEntity clientsEntity, Order order, SimpleExpression simpleExpression, Date date) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        if (clientsEntity != null) {
            crit.add(Restrictions.eq("clientsByClientId.clientId", clientsEntity.getClientId()));
        }
        if (order != null) {
            crit.addOrder(order);
        }
        if (simpleExpression != null) {
            crit.add(simpleExpression);
        }
        if (date != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTime(date);
            int mois = cal.get(Calendar.MONTH) + 1;
            String moisString = (mois > 9) ? mois + "" : "0" + mois;
            crit.add(Restrictions.sqlRestriction("Command_date LIKE '" + cal.get(Calendar.YEAR)
                    + "-" + moisString + "%'"));
            //ilike("commandDate", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + 1 + "%"));
        }
        return new LinkedHashSet(crit.list());
    }
}