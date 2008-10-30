package fr.chantier.service.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.service.ClientsManager;

import java.util.Collection;
import java.util.ArrayList;

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

    public Collection<String> findByName(String input) {
        Collection<String> res = new ArrayList<String>();
        for (ClientsEntity clientsEntity : dao.findByName(input)) {
            res.add(clientsEntity.getClientName());
        }
        return res;
    }

    public ClientsEntity findClientByName(String inputSearch) {
        return dao.findClientByName(inputSearch);
    }
}
