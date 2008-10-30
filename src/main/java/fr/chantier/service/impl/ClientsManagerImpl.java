package fr.chantier.service.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.ClientsEntity;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.service.ClientsManager;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientsManagerImpl extends GenericHibernateManager<ClientsEntity, Integer, ClientsDAO> implements ClientsManager {
    public ClientsManagerImpl(ClientsDAO clientsDAO) {
        super(clientsDAO);
    }

    public Collection<ClientsEntity> findAllExisting() {
        return dao.findAllExisting();
    }
}
