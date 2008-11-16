package fr.chantier.service.impl;

import fr.chantier.dao.CommandesDAO;
import fr.chantier.model.*;
import fr.chantier.service.CoefficientManager;
import fr.chantier.service.CommandesManager;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Criterion;

import java.util.Collection;
import java.util.Date;

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
        return coefficientEntity.getStCoef() * this.getSumOfCostByCommandes(commandesEntity);
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

    public Float getSumDevis(Collection<CommandesEntity> commandesEntityCollection) {
        Float res = 0.f;
        for (CommandesEntity commandesEntity : commandesEntityCollection) {
            res += commandesEntity.getCommandDevis();
        }
        return res;
    }

    public Float getSumRealCost(Collection<CommandesEntity> commandesEntityCollection) {
        Float res = 0.f;
        for (CommandesEntity commandesEntity : commandesEntityCollection) {
            res += this.getRealCost(commandesEntity);
        }
        return res;
    }

    public Float getSumResult(Collection<CommandesEntity> commandesEntityCollection) {
        Float res = 0.f;
        for (CommandesEntity commandesEntity : commandesEntityCollection) {
            res += this.getResult(commandesEntity);
        }
        return res;
    }


    public Collection<CommandesEntity> findByCriterions(ClientsEntity clientsEntity, Order order, Criterion typeFinalise, Date dateBefore) {
        return dao.findByCriterions(clientsEntity, order, typeFinalise, dateBefore);
    }

    public CommandesEntity findById(Integer integer) {
        return dao.findById(integer);
    }

    public Collection<CommandesEntity> findNonFinaliseAndMonth(Order order, Date date) {
        return dao.findNonFinaliseAndMonth(order,date);
    }
}