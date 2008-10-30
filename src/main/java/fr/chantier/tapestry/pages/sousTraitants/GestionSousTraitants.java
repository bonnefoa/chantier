package fr.chantier.tapestry.pages.sousTraitants;

import fr.chantier.model.SousTraitantsEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.model.CoefficientEntity;
import fr.chantier.service.SousTraitantsManager;
import fr.chantier.service.CoefficientManager;
import fr.chantier.dao.CoefficientDAO;
import fr.chantier.tapestry.base.GestionEntite;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry.commons.components.InPlaceEditor;

import java.util.List;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestionSousTraitants extends GestionEntite {

    @Property
    private SousTraitantsEntity sousTraitant;

    @Property(write = false)
    private Collection<SousTraitantsEntity> sousTraitantsEntityList;

    @Property
    private SousTraitantsEntity sousTraitantRow;

    @Inject
    private SousTraitantsManager sousTraitantsManager;

    @Inject
    private CoefficientManager coefficientManager;

    @SetupRender
    private void onSetupRender() {
        sousTraitantsEntityList = sousTraitantsManager.findAllExisting();
    }

    @OnEvent(component = "sousTraitantsForm", value = Form.SUCCESS)
    private void onSuccess() {
        sousTraitantsManager.makePersistent(sousTraitant);
    }

    /**
     * Suppression des sous-traitants
     *
     * @param idSousTraitants
     */
    @OnEvent(component = "suppressionEntite", value = "action")
    private void onActionLink(Integer idSousTraitants) {
        SousTraitantsEntity temp = sousTraitantsManager.findById(idSousTraitants, false);
        temp.setStOld(true);
        sousTraitantsManager.makePersistent(temp);
    }

    /**
     * Modification du nom des sous-traitants
     *
     * @param stId
     * @param nomSousTraitants
     */
    @OnEvent(component = "inPlaceEditor", value = InPlaceEditor.SAVE_EVENT)
    void actionFromEditor(Integer stId, String nomSousTraitants) {
        SousTraitantsEntity entite = sousTraitantsManager.findById(stId, false);
        entite.setStName(nomSousTraitants);
        sousTraitantsManager.makePersistent(entite);
    }
}