package fr.chantier.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * User: sora
 * Date: 3 oct. 2008
 * Time: 08:47:53
 */
public class UtilisateurDAOImpl {

    public UtilisateurDAOImpl(Session session) {
    }
/*
   public Utilisateur recupererUtilisateurParIp(String ip, Lan lan) {
      Criteria crit = getSession().createCriteria(getPersistentClass());
      crit.add(Restrictions.eq("ip", ip));
      crit.add(Restrictions.eq("lanByLanIdLan.idLan", lan.getIdLan()));
      Utilisateur result = (Utilisateur) crit.uniqueResult();
      return result;
   }

   public List<Utilisateur> recupererListUtilisateursAyantCommande(Lan lan) {
      Criteria crit = getSession().createCriteria(getPersistentClass());
      crit.add(Restrictions.eq("lanByLanIdLan.idLan", lan.getIdLan()));
      crit.add(Restrictions.isNotEmpty("commandesesByIdUtilisateur"));
      List<Utilisateur> result = crit.list();
      return result;
   }

*/
}