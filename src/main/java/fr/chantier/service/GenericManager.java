package fr.chantier.service;

import fr.chantier.dao.GenericDAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 3:25:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericManager<T, ID extends Serializable, DAO extends GenericDAO> {

    /**
     * Recupere l'entite selon l'identifiant donne
     *
     * @param id   identifiant de l'entite
     * @param lock Bloque la modification de l'objet recupere
     * @return Entite correspondante
     */
    T findById(ID id, boolean lock);

    /**
     * Recupere toute les entites de la base de donnees
     *
     * @return Liste d'entites de la base
     */
    List<T> findAll();

    /**
     * Rend l'entite donne persistante. L'entite est inscrite dans la base si elle n'existe pas. Elle est mise a jour si elle a ete modifie
     *
     * @param entity Entite a rendre persistente
     * @return Entite persistante
     */
    T makePersistent(T entity);

    /**
     * Decouple l'entite de la base de donne. La supprime de la base de donnee.
     *
     * @param entity Entite a supprimer.
     */
    void makeTransient(T entity);

}
