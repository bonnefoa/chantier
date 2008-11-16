package fr.chantier.tapestry.pages.commandes;

import fr.chantier.enumeration.Mois;
import fr.chantier.model.*;
import fr.chantier.service.*;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.Response;

import java.io.IOException;
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

    @Property
    private Mois mois;

    @Property
    private Integer annee;

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

    private Calendar cal;

    @Inject
    private Response response;

    /**
     * Initialisation du model
     */
    @PageLoaded
    private void onPageLoaded() {
        beanModel = beanModelSource.create(CommandesEntity.class, true, resources.getMessages());
        beanModel.add("client", null);
        beanModel.add("historiqueHeures", null);
        beanModel.add("historiqueSommes", null);
        beanModel.reorder("client", "commandDevis", "commandLibelle", "commandDate", "historiqueHeures", "historiqueSommes");
    }

    /**
     * A l'activation
     *
     * @param idCommande
     */
    @OnEvent("activate")
    private void onActivate(Integer idCommande) {
        try {
            this.idCommande = idCommande;
        } catch (NumberFormatException e) {
            redirectUser();
        }
    }

    /**
     * Initialisation des variables
     */
    @SetupRender
    private void onSetupRender() {
        listeClients = clientsManager.findAllExisting();
        listeIntervenants = intervenantsManager.findAllExisting();
        listSousTraitants = sousTraitantsManager.findAllExisting();
        cal = new GregorianCalendar();
        if (idCommande != null) {
            isModification = true;
            commandesEntity = commandesManager.findById(idCommande);
            if (commandesEntity == null) {
                redirectUser();
            }
            idClient = commandesEntity.getClientsByClientId().getClientId();
            cal.setTime(commandesEntity.getCommandDate());
        } else {
            commandesEntity = null;
        }
        annee = cal.get(Calendar.YEAR);
        mois = Mois.lookup(cal.get(Calendar.MONTH));
        this.initModel();
    }

    private void redirectUser() {
        try {
            response.sendRedirect("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Initialise le model pour le select du client
     */
    private void initModel() {
        modelSelectClient = new LinkedHashMap<Integer, String>();
        Collection<ClientsEntity> listClients = clientsManager.findAllExisting();
        for (ClientsEntity listClient : listClients) {
            modelSelectClient.put(listClient.getClientId(), listClient.getClientName());
        }
    }

    /**
     * Prepare les listes temporaires
     */
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
        if (commandesEntity.getCommandDevis() == null)
            commandesEntity.setCommandDevis(0.f);
        cal = new GregorianCalendar();
        if (mois.getI() != cal.get(Calendar.MONTH) || annee != cal.get(Calendar.YEAR)) {
            cal.set(Calendar.DAY_OF_MONTH, 1);
            cal.set(Calendar.MONTH, mois.getI());
            cal.set(Calendar.YEAR, annee);
        }
        commandesEntity.setCommandDate(cal.getTime());
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
     * Toggle l'affichage de l'historique des heures
     *
     * @return la page de modification
     */
    @OnEvent(value = "action", component = "HistoriqueHeuresShow")
    private Object onHistoriqueHeuresShow(Integer commandId) {
        isDisplayHeure = !isDisplayHeure;
        if (commandId != null) {
            return resources.createPageLink(ModificationCommandes.class, false, commandId);
        }
        return null;
    }

    /**
     * Toggle l'affichage de l'historique des sommes
     *
     * @return la page de modification
     */
    @OnEvent(value = "action", component = "HistoriqueSommesShow")
    private Object onHistoriqueSomme(Integer commandId) {
        isDisplaySommes = !isDisplaySommes;
        if (commandId != null) {
            return resources.createPageLink(ModificationCommandes.class, false, commandId);
        }
        return null;
    }

    /**
     * Libelle pour le lien de toggle de l'historique
     *
     * @return Label pour le lien
     */
    public String getLabelLienHeure() {
        if (isDisplayHeure) {
            return "Cacher l'historique des heures";
        }
        return "Afficher l'historique des heures";
    }

    /**
     * libelle pour le lien de toggle de l'historique des sommes
     *
     * @return
     */
    public String getLabelLienSomme() {
        if (isDisplaySommes) {
            return "Cacher l'historique des sous-traitants";
        }
        return "Afficher l'historique des sous-traitants";
    }

    /**
     * Met la case vide dans le cas d'un devis a 0
     *
     * @return
     */
    public Float getCommandDevis() {
        if (commandesEntity != null && commandesEntity.getCommandDevis() != null && commandesEntity.getCommandDevis() > 0) {
            return commandesEntity.getCommandDevis();
        }
        return null;
    }

    /**
     * Applique les modifications du devis
     *
     * @param inDevis
     */
    public void setCommandDevis(Float inDevis) {
        commandesEntity.setCommandDevis(inDevis);
    }

}