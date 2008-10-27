package fr.chantier.dao;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import java.io.Serializable;
import java.util.List;

/**
 * Dao generique pour l'acces aux entites de la base de donnees
 * User: sora
 * Date: 2 oct. 2008
 * Time: 20:55:01
 */
public interface GenericDAO<T, ID extends Serializable> {
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
    @CommitAfter
    T makePersistent(T entity);

    /**
     * Decouple l'entite de la base de donne. La supprime de la base de donnee.
     *
     * @param entity Entite a supprimer.
     */
    @CommitAfter
    void makeTransient(T entity);
}