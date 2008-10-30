package fr.chantier.service;

import fr.chantier.dao.SousTraitantsDAO;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.SousTraitantsEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SousTraitantsManager extends GenericManager<SousTraitantsEntity, Integer, SousTraitantsDAO> {
    /**
     * Recupere la liste des sous-traitants presents
     *
     * @return
     */
    Collection<SousTraitantsEntity> findAllExisting();

    /**
     * Recupere la somme du sous-traitants donnee pour
     *
     * @param sousTraitantsEntity
     * @param commandesEntity
     * @return
     */
    Float getSumOfCostForCommande(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity);

    /**
     * Retourne la liste de sous-traitants participant a la commande
     *
     * @param commandesEntity
     * @return
     */
    Collection<SousTraitantsEntity> findSousTraitantsForCommandes(CommandesEntity commandesEntity);
}