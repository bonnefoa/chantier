package fr.chantier.dao.impl;

import fr.chantier.dao.CommandesDAO;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

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

    public Collection<CommandesEntity> findByCriterions(ClientsEntity clientsEntity, Order order, SimpleExpression simpleExpression, Date dateBefore, Date dateAfter) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        if(clientsEntity != null){
            crit.add(Restrictions.eq("clientsByClientId.clientId", clientsEntity.getClientId()));
        }
        if(order != null){
            crit.addOrder(order);
        }
        if(simpleExpression != null){
            crit.add(simpleExpression);
        }
        if(dateBefore != null && dateAfter != null){
            crit.add(Restrictions.between("commandDate",dateBefore,dateAfter));
        }
        return new LinkedHashSet(crit.list());
    }
}