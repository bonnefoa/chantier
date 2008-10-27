package fr.chantier.service.impl;

import fr.chantier.model.Clients;
import fr.chantier.dao.ClientsDAO;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientsManagerImpl extends GenericHibernateManager<Clients, Integer, ClientsDAO> {
    public ClientsManagerImpl(ClientsDAO clientsDAO) {
        super(clientsDAO);
    }
}
