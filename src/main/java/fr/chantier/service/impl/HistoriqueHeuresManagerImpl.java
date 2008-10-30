package fr.chantier.service.impl;

import fr.chantier.dao.HistoriqueHeuresDAO;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.service.HistoriqueHeuresManager;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class HistoriqueHeuresManagerImpl extends GenericHibernateManager<HistoriqueHeuresEntity, Integer, HistoriqueHeuresDAO> implements HistoriqueHeuresManager {
    public HistoriqueHeuresManagerImpl(HistoriqueHeuresDAO historiqueHeuresDAO) {
        super(historiqueHeuresDAO);
    }

    public Collection<HistoriqueHeuresEntity> findHistoriqueHeuresByIntervenantAndCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity) {
        return dao.findHistoriqueHeuresByIntervenantAndCommand(intervenantsEntity, commandesEntity);
    }
}