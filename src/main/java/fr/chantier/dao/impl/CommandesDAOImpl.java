package fr.chantier.dao.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.dao.CommandesDAO;
import org.hibernate.Session;

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

}