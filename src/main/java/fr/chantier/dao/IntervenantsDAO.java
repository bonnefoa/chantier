package fr.chantier.dao;

import fr.chantier.model.IntervenantsEntity;
import fr.chantier.model.SousTraitantsEntity;
import fr.chantier.model.CommandesEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:40:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IntervenantsDAO extends GenericDAO<IntervenantsEntity, Integer> {

    Collection<IntervenantsEntity> findAllExisting();

    /**
     * Recupere les intervenants pour la commande donnee
     *
     * @param commandesEntity
     * @return
     */
    Collection<IntervenantsEntity> findIntervenantsForCommandes(CommandesEntity commandesEntity);
}
