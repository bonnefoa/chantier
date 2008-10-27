package fr.chantier.service.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.HistoriqueHeures;
import fr.chantier.model.HistoriqueSomme;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.HistoriqueHeuresDAO;
import fr.chantier.dao.HistoriqueSommeDAO;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueSommeManagerImpl extends GenericHibernateManager<HistoriqueSomme, Integer, HistoriqueSommeDAO>{
    public HistoriqueSommeManagerImpl(HistoriqueSommeDAO historiqueSommeDAO) {
        super(historiqueSommeDAO);
    }
}