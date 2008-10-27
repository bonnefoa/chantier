package fr.chantier.dao.impl;

import fr.chantier.model.Clients;
import fr.chantier.dao.ClientsDAO;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientsDAOImpl extends GenericHibernateDAO<Clients, Integer> implements ClientsDAO {
    public ClientsDAOImpl(Session session) {
        super(session);
    }
    
}
