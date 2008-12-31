package fr.chantier.tapestry.pages.intervenants;

import fr.chantier.model.IntervenantsEntity;
import fr.chantier.service.CoefficientManager;
import fr.chantier.service.IntervenantsManager;
import fr.chantier.tapestry.base.GestionEntite;
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
public class GestionIntervenants extends GestionEntite {

    @Property
    private IntervenantsEntity intervenant;

    @Property(write = false)
    private Collection<IntervenantsEntity> intervenantsEntityList;

    @Property
    private IntervenantsEntity intervenantRow;

    @Inject
    private IntervenantsManager intervenantsManager;

    @Inject
    private CoefficientManager coefficientManager;

    private List<IntervenantsEntity> interToDelete;

    /**
     * Initialisation des variables au rendu de la page
     */
    @SetupRender
    private void onSetupRender() {
        intervenantsEntityList = intervenantsManager.findAllExisting();
    }

    /**
     * Ajout de l'intervenant en base
     */
    @OnEvent(component = "intervenants", value = Form.SUCCESS)
    private void onSuccess() {
        intervenantsManager.makePersistent(intervenant);
    }

    @OnEvent(component = "interForm",value = Form.PREPARE_FOR_SUBMIT)
    private void onPrepareSubmit() {
        interToDelete = new ArrayList<IntervenantsEntity>();
    }

    public boolean getSelected() {
        return false;
    }

    public void setSelected(boolean checked) {
        if (checked) {
            interToDelete.add(intervenantRow);
        }
    }

    @OnEvent("afterSubmit")
    private void onAfterSubmit() {
        intervenantsManager.makePersistent(intervenantRow);
    }

    @OnEvent(value = Form.SUCCESS, component = "interForm")
    private void onSuccessFromInterForm() {
        for (IntervenantsEntity entity : interToDelete) {
            entity.setInterOld(true);
            intervenantsManager.makePersistent(entity);
        }
    }
}