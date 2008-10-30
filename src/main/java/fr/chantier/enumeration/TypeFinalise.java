package fr.chantier.enumeration;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 15:43:07
 * To change this template use File | Settings | File Templates.
 */
public enum TypeFinalise {
    FINALISE(Restrictions.eq("finalise", true)), NON_FINALISEE(Restrictions.eq("finalise", false)), LES_DEUX(null);

    private SimpleExpression simpleExpression;

    TypeFinalise(SimpleExpression simpleExpression) {
        this.simpleExpression = simpleExpression;
    }

    public SimpleExpression getSimpleExpression() {
        return simpleExpression;
    }
}
