package fr.chantier.dao;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.ClientsEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:02:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsDAO extends GenericDAO<ClientsEntity, Integer> {
    
    Collection<ClientsEntity> findAllExisting();
}
