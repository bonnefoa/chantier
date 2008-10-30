package fr.chantier.service;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CoefficientEntity;
import fr.chantier.dao.CoefficientDAO;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CoefficientManager extends GenericManager<CoefficientEntity, Integer, CoefficientDAO> {

    CoefficientEntity findCurrentCoefficient();
}