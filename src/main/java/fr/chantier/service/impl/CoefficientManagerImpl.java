package fr.chantier.service.impl;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CoefficientEntity;
import fr.chantier.dao.CoefficientDAO;
import fr.chantier.service.CoefficientManager;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficientManagerImpl extends GenericHibernateManager<CoefficientEntity, Integer, CoefficientDAO> implements CoefficientManager {
    public CoefficientManagerImpl(CoefficientDAO coefficientDAO) {
        super(coefficientDAO);
    }

    public CoefficientEntity findCurrentCoefficient() {
        return dao.findCurrentCoefficient();
    }
}