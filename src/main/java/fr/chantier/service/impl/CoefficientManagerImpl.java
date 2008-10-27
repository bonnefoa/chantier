package fr.chantier.service.impl;

import fr.chantier.model.Clients;
import fr.chantier.model.Coefficient;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.CoefficientDAO;
import fr.chantier.service.CoefficientManager;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoefficientManagerImpl extends GenericHibernateManager<Coefficient, Integer, CoefficientDAO> implements CoefficientManager {
    public CoefficientManagerImpl(CoefficientDAO coefficientDAO) {
        super(coefficientDAO);
    }

    public Coefficient findCurrentCoefficient() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}