package fr.chantier.service;

import fr.chantier.model.*;
import fr.chantier.dao.*;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HistoriqueSommeManager extends GenericManager<HistoriqueSommeEntity, Integer, HistoriqueSommeDAO> {

    /**
     * Recupere l'historique des somme selon la commande et le sous-traitants donne
     *
     * @param sousTraitantsEntity Sous traitants concerne
     * @param commandesEntity     Commande concernee
     * @return Collection des sommes
     */
    Collection<HistoriqueSommeEntity> recupererHistoriqueSommeBySousTraitantsAndCommandes(SousTraitantsEntity sousTraitantsEntity, CommandesEntity commandesEntity);
}