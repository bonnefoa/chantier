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

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class GestionIntervenants extends GestionEntite{

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

    /**
     * Suppression de l'intervenant
     *
     * @param idIntervenants
     */
    @OnEvent(value = "action")
    private void onActionLink(Integer idIntervenants) {
        IntervenantsEntity temp = intervenantsManager.findById(idIntervenants, false);
        temp.setInterOld(true);
        intervenantsManager.makePersistent(temp);
    }


    /**
     * Modification du nom de l'intervenant
     *
     * @param interId
     * @param nomIntervenant
     */
    @OnEvent(component = "inPlaceEditor", value = InPlaceEditor.SAVE_EVENT)
    void actionFromEditor(Integer interId, String nomIntervenant) {
        IntervenantsEntity entite = intervenantsManager.findById(interId, false);
        entite.setInterName(nomIntervenant);
        intervenantsManager.makePersistent(entite);
    }

    /**
     * Modificiation de l'ordre
     *
     * @param interId
     * @param ordre
     */
    @OnEvent(component = "inPlaceEditorOrdre", value = InPlaceEditor.SAVE_EVENT)
    void actionFromEditorOrdre(Integer interId, String ordre) {
        short ordreShort;
        try {
            ordreShort = Short.parseShort(ordre);
        } catch (NumberFormatException e) {
            ordreShort = 1;
        }
        IntervenantsEntity entite = intervenantsManager.findById(interId, false);
        entite.setInterOrdre(ordreShort);
        intervenantsManager.makePersistent(entite);
    }


}