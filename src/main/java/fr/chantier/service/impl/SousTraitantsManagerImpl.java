package fr.chantier.service.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.HistoriqueHeures;
import fr.chantier.model.Intervenants;
import fr.chantier.model.SousTraitants;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.HistoriqueHeuresDAO;
import fr.chantier.dao.IntervenantsDAO;
import fr.chantier.dao.SousTraitantsDAO;
import fr.chantier.service.SousTraitantsManager;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SousTraitantsManagerImpl extends GenericHibernateManager<SousTraitants, Integer, SousTraitantsDAO> implements SousTraitantsManager {

    public SousTraitantsManagerImpl(SousTraitantsDAO sousTraitantsDAO) {
        super(sousTraitantsDAO);
    }
}