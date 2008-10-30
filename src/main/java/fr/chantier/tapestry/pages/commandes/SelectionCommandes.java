package fr.chantier.tapestry.pages.commandes;

import fr.chantier.model.CommandesEntity;
import fr.chantier.service.CommandesManager;

import java.util.Collection;
import java.util.ArrayList;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.corelib.components.Form;

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

    @Persist
    @Property(write = false)
    private Collection<CommandesEntity> listeCommandes;

    /**
     * Liste des entites a supprimer
     */
    private Collection<CommandesEntity> listeCommandesToRemove;

    @Property
    private CommandesEntity commandesEntity;

    private Float tempRealCost;

    /**
     * Recuperation de la liste de commande au chargement
     */
    @SetupRender
    private void onSetupRender() {
        listeCommandes = commandesManager.findAll();
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
     * Preparation du submit
     */
    @OnEvent(value = Form.PREPARE_FOR_SUBMIT, component = "modifierCommandesForm")
    private void onPrepareForSubmit() {
        listeCommandesToRemove = new ArrayList<CommandesEntity>();
    }

    /**
     * Decocher la suppression de la commande par defaut
     *
     * @return Faux
     */
    public boolean getSuppressionChecked() {
        return false;
    }

    /**
     * Remplir la liste pour la suppression
     *
     * @param ckecked
     */
    public void setSuppressionChecked(boolean ckecked) {
        if (ckecked) {
            listeCommandesToRemove.add(commandesEntity);
        }
    }

    /**
     * Traitement du formulaire
     */
    @OnEvent(component = "modifierCommandesForm", value = Form.SUCCESS)
    private void onSucces() {
        for (CommandesEntity listeCommande : listeCommandes) {
            commandesManager.makePersistent(listeCommande);
        }
        for (CommandesEntity entity : listeCommandesToRemove) {
            commandesManager.makeTransient(entity);
        }
    }
}
