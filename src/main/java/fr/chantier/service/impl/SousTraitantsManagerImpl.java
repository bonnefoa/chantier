package fr.chantier.service.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.SousTraitantsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import fr.chantier.dao.SousTraitantsDAO;
import fr.chantier.service.SousTraitantsManager;
import fr.chantier.service.HistoriqueSommeManager;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SousTraitantsManagerImpl extends GenericHibernateManager<SousTraitantsEntity, Integer, SousTraitantsDAO> implements SousTraitantsManager {

    private HistoriqueSommeManager historiqueSommeManager;

    public SousTraitantsManagerImpl(SousTraitantsDAO sousTraitantsDAO, HistoriqueSommeManager historiqueSommeManager) {
        super(sousTraitantsDAO);
        this.historiqueSommeManager = historiqueSommeManager;
    }

    public Collection<SousTraitantsEntity> findAllExisting() {
        return dao.findAllExisting();
    }

    public Float getSumOfCostForCommande(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity) {
        Float res = 0.f;
        Collection<HistoriqueSommeEntity> collectionSommes = historiqueSommeManager.recupererHistoriqueSommeBySousTraitantsAndCommandes(sousTraitantsEntity, commandesEntity);
        for (HistoriqueSommeEntity collectioSomme : collectionSommes) {
            res += collectioSomme.getHistoriqueSomme();
        }
        return res;
    }

    public Collection<SousTraitantsEntity> findSousTraitantsForCommandes(CommandesEntity commandesEntity) {
        return dao.findSousTraitantsForCommandes(commandesEntity);
    }
}