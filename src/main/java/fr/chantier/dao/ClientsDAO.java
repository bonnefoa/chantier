package fr.chantier.dao;

import fr.chantier.model.ClientsEntity;
import fr.chantier.model.ClientsEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:02:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsDAO extends GenericDAO<ClientsEntity, Integer> {

    Collection<ClientsEntity> findAllExisting();

    /**
     * Retourne une liste de possibilite en cherchant le nom du client
     *
     * @param input
     * @return
     */
    Collection<ClientsEntity> findByName(String input);

    /**
     * Recupere un client unique pour le nom donne
     *
     * @param inputSearch
     * @return
     */
    ClientsEntity findClientByName(String inputSearch);
}
