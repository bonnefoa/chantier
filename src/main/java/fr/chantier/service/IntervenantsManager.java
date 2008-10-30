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
public interface IntervenantsManager extends GenericManager<IntervenantsEntity, Integer, IntervenantsDAO> {
    /**
     * Recupere les intervenants exitants
     *
     * @return
     */
    Collection<IntervenantsEntity> findAllExisting();

    /**
     * Recupere la somme des heures pour la commande donnee
     *
     * @param intervenantsEntity
     * @param commandesEntity
     * @return
     */
    Float getSumOfHoursForCommand(IntervenantsEntity intervenantsEntity, CommandesEntity commandesEntity);
}