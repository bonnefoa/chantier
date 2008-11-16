package fr.chantier.enumeration;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 15:43:07
 * To change this template use File | Settings | File Templates.
 */
public enum TypeFinalise {
    FINALISE(Restrictions.eq("finalise", true)), NON_FINALISEE(Restrictions.eq("finalise", false)), LES_DEUX(null),
    COMMANDES_DU_MOIS_ET_NON_FINALISEES(Restrictions.eq("finalise", false));

    private Criterion criterion;

    TypeFinalise(Criterion criterion) {
        this.criterion = criterion;
    }

    public Criterion getCriterion() {
        return criterion;
    }
}
