package fr.chantier.tapestry.base;

import fr.chantier.model.CoefficientEntity;
import fr.chantier.service.CoefficientManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 29 oct. 2008
 * Time: 11:17:45
 * To change this template use File | Settings | File Templates.
 */
public class GestionEntite {

    @Property
    @Persist("entity")
    private CoefficientEntity coefficientEntity;

    @Inject
    private CoefficientManager coefficientManager;

    /**
     * Verification des coefficients au chargement de la page
     */
    @PageLoaded
    private void onPageLoaded() {
        coefficientEntity = coefficientManager.findCurrentCoefficient();
        if (coefficientEntity == null) {
            CoefficientEntity tempCoefficient = new CoefficientEntity();
            tempCoefficient.setStCoef(0.f);
            tempCoefficient.setInterCoef(0.f);
            coefficientManager.makePersistent(tempCoefficient);
        }
    }

    /**
     * Recuperation du coefficient
     */
    @SetupRender
    private void onBaseSetupRender() {
        coefficientEntity = coefficientManager.findCurrentCoefficient();
    }

    /**
     * Modification du coefficient d'intervenant
     */
    @OnEvent(component = "coefficientForm", value = Form.SUCCESS)
    private void onSuccesFromCoefficientForm() {
        coefficientManager.makePersistent(coefficientEntity);
    }

}
