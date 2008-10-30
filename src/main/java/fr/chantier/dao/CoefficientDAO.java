package fr.chantier.dao;

import fr.chantier.model.CoefficientEntity;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:39:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CoefficientDAO extends GenericDAO<CoefficientEntity, Integer> {

    CoefficientEntity findCurrentCoefficient();
}
