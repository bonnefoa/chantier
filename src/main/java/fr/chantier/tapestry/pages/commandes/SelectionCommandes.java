package fr.chantier.tapestry.pages.commandes;

import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import fr.chantier.service.CommandesManager;
import fr.chantier.service.HistoriqueHeuresManager;
import fr.chantier.service.HistoriqueSommeManager;
import fr.chantier.tapestry.components.commandes.HeaderCommand;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 29 oct. 2008
 * Time: 16:47:08
 * To change this template use File | Settings | File Templates.
 */
public class SelectionCommandes {

    @Inject
    private CommandesManager commandesManager;

    @Inject
    private HistoriqueHeuresManager historiqueHeuresManager;

    @Inject
    private HistoriqueSommeManager historiqueSommeManager;

    @Property
    private CommandesEntity commandesEntity;

    private Float tempRealCost;

    /**
     * Composant de recherche
     */
    @Component
    private HeaderCommand headerCommand;


    @Property(write = false)
    private boolean suppressionChecked;

    /**
     * Recuperation de la liste de commande au chargement
     */
    @SetupRender
    private void onSetupRender() {
    }

    public Float getRealCost() {
        tempRealCost = commandesManager.getRealCost(commandesEntity);
        return tempRealCost;
    }

    public Float getResult() {
        if (tempRealCost != null) {
            return commandesEntity.getCommandDevis() - tempRealCost;
        }
        return commandesManager.getResult(commandesEntity);
    }

    /**
     * Remplir la liste pour la suppression
     *
     * @param ckecked
     */
    public void setSuppressionChecked(boolean ckecked) {
        if (ckecked) {
            commandesEntity = commandesManager.findById(commandesEntity.getCommandId(), false);
            for (HistoriqueHeuresEntity historiqueHeuresEntity : commandesEntity.getHistoriqueHeuresesByCommandId()) {
                historiqueHeuresManager.makeTransient(historiqueHeuresEntity);
            }
            for (HistoriqueSommeEntity historiqueSommeEntity : commandesEntity.getHistoriqueSommesByCommandId()) {
                historiqueSommeManager.makeTransient(historiqueSommeEntity);
            }
            commandesManager.makeTransient(commandesEntity);
        }
    }

    public void setFinaliserChecked(boolean checked) {
        if (checked != commandesEntity.isFinalise()) {
            commandesManager.finaliserCommande(commandesEntity, checked);
        }
    }

    public boolean isFinaliserChecked() {
        return commandesEntity.isFinalise();
    }

    public Collection<CommandesEntity> getListeCommandes() {
        return headerCommand.getCommandesEntityCollection();
    }
}
