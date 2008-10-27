package fr.chantier.dao.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.Coefficient;
import fr.chantier.model.Commandes;
import fr.chantier.model.HistoriqueHeures;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.CoefficientDAO;
import fr.chantier.dao.CommandesDAO;
import fr.chantier.dao.HistoriqueHeuresDAO;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueHeuresDAOImpl extends GenericHibernateDAO<HistoriqueHeures, Integer> implements HistoriqueHeuresDAO {

    public HistoriqueHeuresDAOImpl(Session session) {
        super(session);
    }

}