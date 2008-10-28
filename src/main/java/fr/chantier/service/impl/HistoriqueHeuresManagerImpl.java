package fr.chantier.service.impl;

import fr.chantier.dao.HistoriqueHeuresDAO;
import fr.chantier.model.HistoriqueHeures;
import fr.chantier.service.HistoriqueHeuresManager;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueHeuresManagerImpl extends GenericHibernateManager<HistoriqueHeures, Integer, HistoriqueHeuresDAO> implements HistoriqueHeuresManager {
    public HistoriqueHeuresManagerImpl(HistoriqueHeuresDAO historiqueHeuresDAO) {
        super(historiqueHeuresDAO);
    }
}