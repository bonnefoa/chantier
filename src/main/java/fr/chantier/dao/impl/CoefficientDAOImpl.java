package fr.chantier.dao.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CoefficientEntity;
import fr.chantier.dao.CoefficientDAO;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficientDAOImpl extends GenericHibernateDAO<CoefficientEntity, Integer> implements CoefficientDAO {

    public CoefficientDAOImpl(Session session) {
        super(session);
    }

    public CoefficientEntity findCurrentCoefficient() {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        return (CoefficientEntity) crit.uniqueResult();
    }
}