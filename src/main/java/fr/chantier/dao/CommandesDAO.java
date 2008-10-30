package fr.chantier.dao;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.SimpleExpression;

import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:04:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommandesDAO extends GenericDAO<CommandesEntity, Integer> {

    /**
     * Recupere la liste des commandes correspondants aux criteres donnes
     *
     * @param clientsEntity
     * @param order
     * @param typeFinalise
     * @param dateBefore
     * @param dateAfter
     * @return
     */
    Collection<CommandesEntity> findByCriterions(ClientsEntity clientsEntity, Order order, SimpleExpression typeFinalise, Date dateBefore, Date dateAfter);
}
