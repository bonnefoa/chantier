package fr.chantier.service;

import fr.chantier.model.Clients;
import fr.chantier.model.Coefficient;
import fr.chantier.model.Commandes;
import fr.chantier.dao.ClientsDAO;
import fr.chantier.dao.CoefficientDAO;
import fr.chantier.dao.CommandesDAO;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommandesManager extends GenericManager<Commandes, Integer, CommandesDAO> {
}