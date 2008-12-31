package fr.chantier.tapestry.pages.commandes;

import fr.chantier.model.*;
import fr.chantier.service.*;
import fr.chantier.tapestry.components.commandes.HeaderCommand;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.RequestGlobals;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffichageCommandes {
    @Inject
    private CommandesManager commandesManager;

    @Inject
    private CoefficientManager coefficientManager;

    @Inject
    private IntervenantsManager intervenantsManager;

    @Inject
    private SousTraitantsManager sousTraitantsManager;

    @Inject
    private HistoriqueHeuresManager historiqueHeuresManager;

    @Inject
    private HistoriqueSommeManager historiqueSommeManager;

    @Property
    private Collection<CommandesEntity> listCommandes;

    @Property(write = false)
    private CommandesEntity commandesEntity;

    @Property
    private SousTraitantsEntity sousTraitantsEntity;

    @Property
    private IntervenantsEntity intervenantsEntity;

    @Property
    private CoefficientEntity coefficientEntity;

    @Retain
    @Property
    private DecimalFormat decimalFormat;

    @Inject
    private RequestGlobals _requestGlobals;

    /**
     * Composant de recherche
     */
    @Component
    private HeaderCommand headerCommand;

    /**
     * Somme des heures pour l'entite dans la boucle
     */
    @Property
    private Float sumOfHours;

    /**
     * Somme des couts de sous-traitants
     */
    @Property
    private Float sumOfCost;

    /**
     * Couts des sous-traitants
     */
    @Property
    private Float costOfSousTraitants;
    /**
     * Cout des intervenants
     */
    @Property
    private Float costOfIntervenants;

    /**
     * Resultat d'une commande
     */
    @Property
    private Float result;
    /**
     * Cout reel d'une commande
     */
    @Property
    private Float realCost;
    /**
     * Somme des devis
     */
    @Property
    private Float sumDevis = 0.f;
    /**
     * Somme des couts reels
     */
    @Property
    private Float sumRealCost = 0.f;
    /**
     * Somme des resultats
     */
    @Property
    private Float sumResult = 0.f;

    private Integer idCommand;

    @OnEvent("activate")
    private void onActivate(Integer idCommand) {
        this.idCommand = idCommand;
    }

    @PageLoaded
    private void onPageLoaded() {
        decimalFormat = (DecimalFormat) NumberFormat.getInstance(
                _requestGlobals.getRequest().getLocale());
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(0);
    }

    /**
     * Tapestry render phase method.
     * Initialize temporary instance variables here.
     */
    @SetupRender
    private void setupRender() {
        if (idCommand != null) {
            listCommandes = new ArrayList<CommandesEntity>();
            listCommandes.add(commandesManager.findById(idCommand));
        } else {
            listCommandes = headerCommand.getCommandesEntityCollection();
        }
        coefficientEntity = coefficientManager.findCurrentCoefficient();
    }

    public Collection<SousTraitantsEntity> getListSousTraitants() {
        return sousTraitantsManager.findSousTraitantsForCommandes(commandesEntity);
    }

    public Collection<IntervenantsEntity> getListIntervenants() {
        return intervenantsManager.findIntervenantsForCommandes(commandesEntity);
    }

    /**
     * Setter de l'entite. permet d'initialiser les differentes valeurs concernees
     *
     * @param commandesEntity
     */
    public void setCommandesEntity(CommandesEntity commandesEntity) {
        this.commandesEntity = commandesEntity;
        sumOfHours = commandesManager.getSumOfHoursByCommandes(commandesEntity);
        sumOfCost = commandesManager.getSumOfCostByCommandes(commandesEntity);
        costOfSousTraitants = sumOfCost * coefficientEntity.getStCoef();
        costOfIntervenants = sumOfHours * coefficientEntity.getInterCoef();
        realCost = costOfIntervenants + costOfSousTraitants;
        result = commandesEntity.getCommandDevis() - realCost;
        sumDevis += commandesEntity.getCommandDevis();
        sumRealCost += realCost;
        sumResult += result;
    }

    public String getListHeures() {
        StringBuilder res = new StringBuilder();
        Collection<HistoriqueHeuresEntity> listHistoriqueHeures = historiqueHeuresManager.findHistoriqueHeuresByIntervenantAndCommand(intervenantsEntity, commandesEntity);
        for (HistoriqueHeuresEntity listHistoriqueHeure : listHistoriqueHeures) {
            int sup = (int) Math.floor(listHistoriqueHeure.getHistoriqueHeures());
            int dec = (int) ((listHistoriqueHeure.getHistoriqueHeures() - sup) * 100);
            res.append(sup);
            if (dec > 0) {
                res.append("<sup>" + dec + "</sup>");
            }
            res.append(" + ");
        }
        return res.substring(0, res.length() - 1);
    }

    public String getListSommes() {
        StringBuilder res = new StringBuilder();
        Collection<HistoriqueSommeEntity> historiqueSommes = historiqueSommeManager.recupererHistoriqueSommeBySousTraitantsAndCommandes(sousTraitantsEntity, commandesEntity);
        for (HistoriqueSommeEntity historiqueSomme : historiqueSommes) {
            res.append(decimalFormat.format(historiqueSomme.getHistoriqueSomme()) + "+");
        }
        return res.substring(0, res.length() - 1);
    }

    public String getDisplayResult() {
        return currencyColor(result);
    }

    public String getRecapColor() {
        return currencyColor(commandesManager.getSumResult(listCommandes));
    }

    private String currencyColor(Float res) {
        if (res > 0) {
            return "display_positive";
        }
        return "display_negative";
    }
}