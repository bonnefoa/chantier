package fr.chantier.service;

import fr.chantier.dao.ClientsDAO;
import fr.chantier.model.ClientsEntity;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsManager extends GenericManager<ClientsEntity, Integer, ClientsDAO>{
        Collection<ClientsEntity> findAllExisting();
}
