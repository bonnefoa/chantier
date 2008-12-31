package fr.chantier.tapestry.pages.clients;

import fr.chantier.model.ClientsEntity;
import fr.chantier.service.ClientsManager;
import org.apache.log4j.Logger;
import org.apache.tapestry.commons.components.InPlaceEditor;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestionClients {

    private Logger log = Logger.getLogger(this.getClass());

    @Property
    private ClientsEntity client;

    @Property(write = false)
    private Collection<ClientsEntity> clientsEntityList;

    @Property
    private ClientsEntity clientRow;

    @Inject
    private ClientsManager clientsManager;

    private List<ClientsEntity> clientsToDeletes;

    /**
     * Au rendu de la page
     */
    @SetupRender
    private void onSetupRender() {
        clientsEntityList = clientsManager.findAllExisting();
    }

    /**
     * Au succes du beaneditForm, creation du client en base
     */
    @OnEvent(value = Form.SUCCESS, component = "client")
    private void onSuccess() {
        clientsManager.makePersistent(client);
    }

    @OnEvent(component = "clientForm", value = Form.PREPARE_FOR_SUBMIT)
    private void prepareForSubmit() {
        clientsToDeletes = new ArrayList<ClientsEntity>();
    }

    /**
     * Au succes du formulaire, mise a jour des clients a archiver
     */
    @OnEvent(value = Form.SUCCESS, component = "clientForm")
    private void onSuccessFromClientForm() {
        for (ClientsEntity delete : clientsToDeletes) {
            delete.setClientOld(true);
            clientsManager.makePersistent(delete);
        }
    }

    public boolean getSuppressClient() {
        return false;
    }

    public void setSuppressClient(boolean checked) {
        if (checked) {
            clientsToDeletes.add(clientRow);
        }
    }

    public String getClientNameField() {
        return clientRow.getClientName();
    }

    public void setClientNameField(String input) {
        if (!input.equals(clientRow.getClientName())) {
            clientRow.setClientName(input);
            clientsManager.makePersistent(clientRow);
        }
    }
}
