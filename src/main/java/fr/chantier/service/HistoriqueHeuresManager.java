package fr.chantier.service;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.dao.HistoriqueHeuresDAO;

import java.util.List;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HistoriqueHeuresManager extends GenericManager<HistoriqueHeuresEntity, Integer, HistoriqueHeuresDAO> {

    /**
     * Recupere l'historique des heures selon l'intervenants et la commande donnee
     *
     * @param intervenantsEntity Intervenants
     * @param commandesEntity    Commande concernee
     * @return Collection des heures
     */
    Collection<HistoriqueHeuresEntity> findHistoriqueHeuresByIntervenantAndCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity);


}