package fr.chantier.tapestry.pages.clients;

import fr.chantier.service.ClientsManager;
import fr.chantier.model.Clients;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.corelib.components.Form;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestionClients {

    private Clients client;

    @Inject
    private ClientsManager clientsManager;

    @OnEvent(value = Form.SUCCESS, component = "client")
    private void onSuccess() {
        clientsManager.makePersistent(client);
    }
}
