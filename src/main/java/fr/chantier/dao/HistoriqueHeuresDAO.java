package fr.chantier.dao;

import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.model.CommandesEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:41:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HistoriqueHeuresDAO extends GenericDAO<HistoriqueHeuresEntity, Integer> {

    /**
     * Recupere l'historique des heures selon l'intervenants et la commande donnee
     *
     * @param intervenantsEntity Intervenants
     * @param commandesEntity    Commande concernee
     * @return Collection des heures
     */
    Collection<HistoriqueHeuresEntity> findHistoriqueHeuresByIntervenantAndCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity);

    
}
