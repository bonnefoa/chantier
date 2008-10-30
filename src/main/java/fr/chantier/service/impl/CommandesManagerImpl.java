package fr.chantier.service.impl;

import fr.chantier.dao.CommandesDAO;
import fr.chantier.model.CoefficientEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import fr.chantier.service.CoefficientManager;
import fr.chantier.service.CommandesManager;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:16:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommandesManagerImpl extends GenericHibernateManager<CommandesEntity, Integer, CommandesDAO> implements CommandesManager {

    private CoefficientManager coefficientManager;

    public CommandesManagerImpl(CommandesDAO commandesDAO, CoefficientManager coefficientManager) {
        super(commandesDAO);
        this.coefficientManager = coefficientManager;
    }

    public CommandesEntity addHistoriqueHeuresToCommandes(HistoriqueHeuresEntity historiqueHeuresEntity, CommandesEntity commandesEntity) {
        commandesEntity.getHistoriqueHeuresesByCommandId().add(historiqueHeuresEntity);
        dao.makePersistent(commandesEntity);
        return commandesEntity;
    }

    public CommandesEntity removeHistoriqueHeuresToCommandes(HistoriqueHeuresEntity historiqueHeuresEntity, CommandesEntity commandesEntity) {
        commandesEntity.getHistoriqueHeuresesByCommandId().remove(historiqueHeuresEntity);
        dao.makePersistent(commandesEntity);
        return commandesEntity;
    }

    public CommandesEntity addHistoriqueSommeToCommandes(HistoriqueSommeEntity historiqueSommeEntity, CommandesEntity commandesEntity) {
        commandesEntity.getHistoriqueSommesByCommandId().add(historiqueSommeEntity);
        dao.makePersistent(commandesEntity);
        return commandesEntity;
    }

    public CommandesEntity removeHistoriqueSommeToCommandes(HistoriqueSommeEntity historiqueSommeEntity, CommandesEntity commandesEntity) {
        commandesEntity.getHistoriqueSommesByCommandId().remove(historiqueSommeEntity);
        dao.makePersistent(commandesEntity);
        return commandesEntity;
    }

    public Float getSumOfHoursByCommandes(CommandesEntity commandesEntity) {
        Float res = 0.f;
        for (HistoriqueHeuresEntity historiqueHeuresEntity : commandesEntity.getHistoriqueHeuresesByCommandId()) {
            res += historiqueHeuresEntity.getHistoriqueHeures();
        }
        return res;
    }

    public Float getSumOfCostByCommandes(CommandesEntity commandesEntity) {
        Float res = 0.f;
        for (HistoriqueSommeEntity historiqueSommeEntity : commandesEntity.getHistoriqueSommesByCommandId()) {
            res += historiqueSommeEntity.getHistoriqueSomme();
        }
        return res;
    }

    public Float getCostOfIntervenants(CommandesEntity commandesEntity) {
        CoefficientEntity coefficientEntity = coefficientManager.findCurrentCoefficient();
        return coefficientEntity.getInterCoef() * this.getSumOfHoursByCommandes(commandesEntity);
    }

    public Float getCostOfSousTraitants(CommandesEntity commandesEntity) {
        CoefficientEntity coefficientEntity = coefficientManager.findCurrentCoefficient();
        return coefficientEntity.getInterCoef() * this.getSumOfCostByCommandes(commandesEntity);
    }

    public CommandesEntity finaliserCommande(CommandesEntity commandesEntity, boolean finaliser) {
        commandesEntity.setFinalise(finaliser);
        dao.makePersistent(commandesEntity);
        return commandesEntity;
    }

    public Float getRealCost(CommandesEntity commandesEntity) {
        return this.getCostOfIntervenants(commandesEntity) + this.getCostOfSousTraitants(commandesEntity);
    }

    public Float getResult(CommandesEntity commandesEntity) {
        return commandesEntity.getCommandDevis() - this.getRealCost(commandesEntity);
    }
}