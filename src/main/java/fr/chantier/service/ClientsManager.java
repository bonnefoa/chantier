package fr.chantier.service;

import fr.chantier.dao.ClientsDAO;
import fr.chantier.model.ClientsEntity;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 6:07:51 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsManager extends GenericManager<ClientsEntity, Integer, ClientsDAO> {
    Collection<ClientsEntity> findAllExisting();

    /**
     * Retourne une liste de chois pour la recherche donne
     *
     * @param input
     * @return
     */
    Collection<String> findByName(String input);

    /**
     * Recupere le client par son nom
     *
     * @param inputSearch
     * @return
     */
    ClientsEntity findClientByName(String inputSearch);
}
