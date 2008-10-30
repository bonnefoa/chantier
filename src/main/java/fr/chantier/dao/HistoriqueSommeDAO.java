package fr.chantier.dao;

import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import fr.chantier.model.SousTraitantsEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:44:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HistoriqueSommeDAO extends GenericDAO<HistoriqueSommeEntity, Integer> {

    /**
     * Recupere l'historique des somme selon la commande et le sous-traitants donne
     *
     * @param sousTraitantsEntity Sous traitants concerne
     * @param commandesEntity     Commande concernee
     * @return Collection des sommes
     */
    Collection<HistoriqueSommeEntity> recupererHistoriqueSommeBySousTraitantsAndCommandes(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity);
}
