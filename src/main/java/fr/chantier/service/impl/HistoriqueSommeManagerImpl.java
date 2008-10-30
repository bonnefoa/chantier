package fr.chantier.service.impl;

import fr.chantier.dao.HistoriqueSommeDAO;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import fr.chantier.model.SousTraitantsEntity;
import fr.chantier.service.HistoriqueSommeManager;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueSommeManagerImpl extends GenericHibernateManager<HistoriqueSommeEntity, Integer, HistoriqueSommeDAO> implements HistoriqueSommeManager {
    public HistoriqueSommeManagerImpl(HistoriqueSommeDAO historiqueSommeDAO) {
        super(historiqueSommeDAO);
    }

    public Collection<HistoriqueSommeEntity> recupererHistoriqueSommeBySousTraitantsAndCommandes(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity) {
        return dao.recupererHistoriqueSommeBySousTraitantsAndCommandes(sousTraitantsEntity, commandesEntity);
    }
}