package fr.chantier.dao.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.Coefficient;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.CoefficientDAO;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficientDAOImpl extends GenericHibernateDAO<Coefficient, Integer> implements CoefficientDAO {

    public CoefficientDAOImpl(Session session) {
        super(session);
    }

}