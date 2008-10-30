package fr.chantier.tapestry.pages.commandes;

import fr.chantier.model.*;
import fr.chantier.service.*;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 28, 2008
 * Time: 3:55:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModificationCommandes {

    /* Managers */
    @Inject
    private CommandesManager commandesManager;

    @Inject
    private IntervenantsManager intervenantsManager;

    @Inject
    private ClientsManager clientsManager;

    @Inject
    private SousTraitantsManager sousTraitantsManager;

    @Inject
    private HistoriqueHeuresManager historiqueHeuresManager;

    @Inject
    private HistoriqueSommeManager historiqueSommeManager;

    @Inject
    private ComponentResources resources;

    @Inject
    private BeanModelSource beanModelSource;

    /**
     * Boolean d'etat de la page
     */
    @Property
    private boolean isModification;

    @Persist
    @Property
    private boolean isDisplayHeure;

    @Persist
    @Property
    private boolean isDisplaySommes;

    /**
     * Model pour le beaneditform
     */
    @Property
    @Retain
    private BeanModel<CommandesEntity> beanModel;

    /**
     * Liste des intervenants
     */
    @Property(write = false)
    private Collection<IntervenantsEntity> listeIntervenants;

    /**
     * Liste des clients
     */
    @Property(write = false)
    private Collection<ClientsEntity> listeClients;

    /**
     * Liste des sous-traitants
     */
    @Property(write = false)
    private Collection<SousTraitantsEntity> listSousTraitants;

    /* Entites individuelles */

    @Property
    private IntervenantsEntity intervenantsEntity;

    @Property
    private SousTraitantsEntity sousTraitantsEntity;

    @Property
    @Persist
    private CommandesEntity commandesEntity;

    @Property
    private Float heuresSelected;

    @Property
    private Integer minutesSelected;

    @Property
    private Float sommeSelected;

    /**
     * Model pour le select
     */
    @Property
    private Map<Integer, String> modelSelectClient;

    /**
     * Resultat du select
     */
    @Property
    private Integer idClient;

    /**
     * Identifiant de la commande
     */
    private Integer idCommande;

    /**
     * Liste de historique heures a persister
     */
    private Collection<HistoriqueHeuresEntity> listHeureToPersist;

    /**
     * Liste de historique somme a persister
     */
    private Collection<HistoriqueSommeEntity> listSommesToPersist;


    /**
     * Initialisation du model
     */
    @PageLoaded
    private void onPageLoaded() {
        beanModel = beanModelSource.create(CommandesEntity.class, true, resources.getMessages());
        beanModel.add("client", null);
        beanModel.add("historiqueHeures", null);
        beanModel.add("historiqueSommes", null);
    }

    /**
     * A l'activation
     *
     * @param idCommande
     */
    @OnEvent("activate")
    private void onActivate(Integer idCommande) {
        this.idCommande = idCommande;
    }

    /**
     * Initialisation des variables
     */
    @SetupRender
    private void onSetupRender() {
        listeClients = clientsManager.findAllExisting();
        listeIntervenants = intervenantsManager.findAllExisting();
        listSousTraitants = sousTraitantsManager.findAllExisting();
        if (idCommande != null) {
            isModification = true;
            commandesEntity = commandesManager.findById(idCommande, false);
            idClient = commandesEntity.getClientsByClientId().getClientId();
        }
        this.initModel();
    }

    /**
     * Initialise le model pour le select du client
     */
    private void initModel() {
        modelSelectClient = new TreeMap<Integer, String>();
        Collection<ClientsEntity> listClients = clientsManager.findAllExisting();
        for (ClientsEntity listClient : listClients) {
            modelSelectClient.put(listClient.getClientId(), listClient.getClientName());
        }
    }

    @OnEvent(component = "commandesForm", value = Form.PREPARE_FOR_SUBMIT)
    private void onPrepareforSubmit() {
        listHeureToPersist = new ArrayList<HistoriqueHeuresEntity>();
        listSommesToPersist = new ArrayList<HistoriqueSommeEntity>();
    }

    /**
     * Traitement du formulaire
     */
    @OnEvent(component = "commandesForm", value = Form.SUCCESS)
    private Object onSuccessFromCommandesForm() {
        ClientsEntity clientsEntity = clientsManager.findById(idClient, false);
        commandesEntity.setClientsByClientId(clientsEntity);
        commandesEntity = commandesManager.makePersistent(commandesEntity);
        for (HistoriqueHeuresEntity historiqueHeuresEntity : listHeureToPersist) {
            historiqueHeuresManager.makePersistent(historiqueHeuresEntity);
        }
        for (HistoriqueSommeEntity historiqueSommeEntity : listSommesToPersist) {
            historiqueSommeManager.makePersistent(historiqueSommeEntity);
        }
        return resources.createPageLink(ModificationCommandes.class, false, commandesEntity.getCommandId());
    }

    /**
     * Recupere les heures et minutes de l'intervenants pour la commande donnee
     *
     * @return
     */
    public String getHeuresEtMinutes() {
        Float res = intervenantsManager.getSumOfHoursForCommand(intervenantsEntity, commandesEntity);
        int heures;
        int minutes;
        heures = (int) Math.floor(res);
        minutes = (int) ((res - heures) * 60);
        return heures + " heures " + minutes + " minutes";
    }

    /**
     * Recupere le cout du sous-traitant pour la commande et le sous-traitants donne
     *
     * @return
     */
    public Float getSousTraitantsTotalCost() {
        return sousTraitantsManager.getSumOfCostForCommande(sousTraitantsEntity, commandesEntity);
    }

    /**
     * Traite les minutes rentrees
     */
    @OnEvent(value = "AfterSubmit", component = "ajoutHeure")
    private void traiteHeure() {
        Float res = 0.f;
        Float quotient = (float) 1 / 60;
        if (heuresSelected != null)
            res += heuresSelected;
        if (minutesSelected != null)
            res += minutesSelected * quotient;
        if (heuresSelected != null || minutesSelected != null) {
            HistoriqueHeuresEntity historiqueHeuresEntity = new HistoriqueHeuresEntity();
            historiqueHeuresEntity.setHistoriqueHeures(res);
            historiqueHeuresEntity.setHistoriqueDate(new Date());
            historiqueHeuresEntity.setIntervenantsByInterId(intervenantsEntity);
            historiqueHeuresEntity.setCommandesByCommandId(commandesEntity);
            listHeureToPersist.add(historiqueHeuresEntity);
        }
    }

    /**
     * Traite les sommes rentrees
     */
    @OnEvent(value = "AfterSubmit", component = "ajoutSomme")
    private void traiteSomme() {
        if (sommeSelected != null) {
            HistoriqueSommeEntity historiqueSommeEntity = new HistoriqueSommeEntity();
            historiqueSommeEntity.setCommandesByCommandId(commandesEntity);
            historiqueSommeEntity.setHistoriqueDate(new Date());
            historiqueSommeEntity.setHistoriqueSomme(sommeSelected);
            historiqueSommeEntity.setSousTraitantsByStId(sousTraitantsEntity);
            listSommesToPersist.add(historiqueSommeEntity);
        }
    }


    /**
     * Remplissage de la zone au click
     */
    @OnEvent(value = "action", component = "HistoriqueHeuresShow")
    private Object onHistoriqueHeuresShow() {
        isDisplayHeure = !isDisplayHeure;
        return resources.createPageLink(ModificationCommandes.class, false, commandesEntity.getCommandId());
    }

    @OnEvent(value = "action", component = "HistoriqueSommesShow")
    private Object onHistoriqueSomme() {
        isDisplaySommes = !isDisplaySommes;
        return resources.createPageLink(ModificationCommandes.class, false, commandesEntity.getCommandId());
    }

    public String getLabelLienHeure() {
        if (isDisplayHeure) {
            return "Cacher l'historique des heures";
        }
        return "Afficher l'historique des heures";
    }

    public String getLabelLienSomme() {
        if (isDisplaySommes) {
            return "Cacher l'historique des sommes";
        }
        return "Afficher l'historique des sommes";
    }

}