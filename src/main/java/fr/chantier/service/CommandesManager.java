package fr.chantier.service;

import fr.chantier.dao.CommandesDAO;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.HistoriqueSommeEntity;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommandesManager extends GenericManager<CommandesEntity, Integer, CommandesDAO> {

    /**
     * Ajoute une heure travaillee a la commande
     *
     * @param historiqueHeuresEntity Heure travaillee
     * @param commandesEntity        Commande concernee
     * @return
     */
    CommandesEntity addHistoriqueHeuresToCommandes(HistoriqueHeuresEntity historiqueHeuresEntity, CommandesEntity commandesEntity);

    /**
     * Retire une heure de la commande
     *
     * @param historiqueHeuresEntity
     * @param commandesEntity
     * @return
     */
    CommandesEntity removeHistoriqueHeuresToCommandes(HistoriqueHeuresEntity historiqueHeuresEntity, CommandesEntity commandesEntity);

    /**
     * Ajoute une somme affecte a un sous-traitants
     *
     * @param historiqueSommeEntity Somme affectee
     * @param commandesEntity       Commande concernee
     * @return La commande persiste
     */
    CommandesEntity addHistoriqueSommeToCommandes(HistoriqueSommeEntity historiqueSommeEntity, CommandesEntity commandesEntity);

    /**
     * Retire une somme de la commande
     *
     * @param historiqueSommeEntity Somme affectee
     * @param commandesEntity       Commande concernee
     * @return La commande persiste
     */
    CommandesEntity removeHistoriqueSommeToCommandes(HistoriqueSommeEntity historiqueSommeEntity, CommandesEntity commandesEntity);

    /**
     * Recupere la somme des heures pour la commande donnee
     *
     * @param commandesEntity Commande a traiter
     * @return Somme des heures
     */
    Float getSumOfHoursByCommandes(CommandesEntity commandesEntity);

    /**
     * Recupere la somme des couts de sous-traitants pour la commande donnee
     *
     * @param commandesEntity Commande a traiter
     * @return Somme des couts des sous-traitants
     */
    Float getSumOfCostByCommandes(CommandesEntity commandesEntity);

    /**
     * Recupere le cout total des intervenants
     *
     * @param commandesEntity
     * @return
     */
    Float getCostOfIntervenants(CommandesEntity commandesEntity);

    /**
     * Recupere le cout totat des sous-traitants
     *
     * @param commandesEntity
     * @return
     */
    Float getCostOfSousTraitants(CommandesEntity commandesEntity);

    /**
     * Finalise la commande selon l'etat du boolean
     *
     * @param commandesEntity Commande a traiter
     * @param finaliser       Finaliser la commande ou non
     * @return Commande persiste
     */
    CommandesEntity finaliserCommande(CommandesEntity commandesEntity, boolean finaliser);

    /**
     * Recupere le cout reel de la commande
     *
     * @param commandesEntity
     * @return
     */
    Float getRealCost(CommandesEntity commandesEntity);

    /**
     * Recupere le resultat final
     *
     * @param commandesEntity
     * @return
     */
    Float getResult(CommandesEntity commandesEntity);

    /**
     * Recupere la somme des devis de la liste de commande
     *
     * @param commandesEntityCollection
     * @return
     */
    Float getSumDevis(Collection<CommandesEntity> commandesEntityCollection);

    /**
     * Recupere la somme des couts reels de ces commandes
     *
     * @param commandesEntityCollection
     * @return
     */
    Float getSumRealCost(Collection<CommandesEntity> commandesEntityCollection);

    /**
     * Recupere la somme des resultat de ces commandes
     *
     * @param commandesEntityCollection
     * @return
     */
    Float getSumResult(Collection<CommandesEntity> commandesEntityCollection);

    /**
     * Recupere la liste des commandes correspondant a la recherche donne
     *
     * @param order
     * @param typeFinalise
     * @param dateBefore
     * @return
     */
    Collection<CommandesEntity> findByCriterions(ClientsEntity clientsEntity, Order order, SimpleExpression typeFinalise, Date dateBefore);

    /**
     * Cherche par l'identifiant
     *
     * @param integer
     * @return
     */
    CommandesEntity findById(Integer integer);
}