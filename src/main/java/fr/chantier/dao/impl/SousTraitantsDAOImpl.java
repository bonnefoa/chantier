package fr.chantier.dao.impl;

import fr.chantier.model.*;
import fr.chantier.dao.*;
import org.hibernate.Session;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class SousTraitantsDAOImpl extends GenericHibernateDAO<SousTraitants, Integer> implements SousTraitantsDAO {

    public SousTraitantsDAOImpl(Session session) {
        super(session);
    }

}