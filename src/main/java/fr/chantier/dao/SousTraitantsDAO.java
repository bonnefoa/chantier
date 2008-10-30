package fr.chantier.dao;

import fr.chantier.model.SousTraitantsEntity;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:41:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SousTraitantsDAO extends GenericDAO<SousTraitantsEntity, Integer> {

    Collection<SousTraitantsEntity> findAllExisting();

}
