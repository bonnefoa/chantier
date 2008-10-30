package fr.chantier.service.impl;

import fr.chantier.dao.IntervenantsDAO;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.service.HistoriqueHeuresManager;
import fr.chantier.service.IntervenantsManager;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class IntervenantsManagerImpl extends GenericHibernateManager<IntervenantsEntity, Integer, IntervenantsDAO> implements IntervenantsManager {

    private HistoriqueHeuresManager historiqueHeuresManager;

    public IntervenantsManagerImpl(IntervenantsDAO intervenantsDAO, HistoriqueHeuresManager historiqueHeuresManager) {
        super(intervenantsDAO);
        this.historiqueHeuresManager = historiqueHeuresManager;
    }

    public Collection<IntervenantsEntity> findAllExisting() {
        return dao.findAllExisting();
    }

    public Float getSumOfHoursForCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity) {
        Float res = 0.f;
        if (commandesEntity != null) {
            Collection<HistoriqueHeuresEntity> collectionHeures = historiqueHeuresManager.findHistoriqueHeuresByIntervenantAndCommand(intervenantsEntity, commandesEntity);
            for (HistoriqueHeuresEntity collectionHeure : collectionHeures) {
                res += collectionHeure.getHistoriqueHeures();
            }
        }
        return res;
    }

    public Collection<IntervenantsEntity> findIntervenantsForCommandes(CommandesEntity commandesEntity) {
        return dao.findIntervenantsForCommandes(commandesEntity);
    }
}