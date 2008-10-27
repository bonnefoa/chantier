package fr.chantier.service.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.HistoriqueHeures;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.HistoriqueHeuresDAO;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueHeuresManagerImpl extends GenericHibernateManager<HistoriqueHeures, Integer, HistoriqueHeuresDAO> {
    public HistoriqueHeuresManagerImpl(HistoriqueHeuresDAO historiqueHeuresDAO) {
        super(historiqueHeuresDAO);
    }
}