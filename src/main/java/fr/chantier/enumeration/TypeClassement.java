package fr.chantier.enumeration;

import org.hibernate.criterion.Order;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 15:42:59
 * To change this template use File | Settings | File Templates.
 */
public enum TypeClassement {
    NUMERO(Order.asc("commandId"), Order.desc("commandId")), MONTANT_DEVIS(Order.asc("commandDevis"), Order.desc("commandDevis")),
    DATE(Order.asc("commandDate"), Order.desc("commandDate")),
    CLIENT(Order.asc("clientsByClientId"), Order.desc("clientsByClientId"));

    private Order orderAsc;

    private Order orderDesc;

    public Order getOrder(TypeOrdonnancement typeOrdonnancement) {
        if (typeOrdonnancement.equals(TypeOrdonnancement.CROISSANTE)) {
            return getOrderAsc();
        }
        return getOrderDesc();
    }

    public Order getOrderAsc() {
        return orderAsc;
    }

    public Order getOrderDesc() {
        return orderDesc;
    }

    TypeClassement(Order orderAsc, Order orderDesc) {
        this.orderAsc = orderAsc;
        this.orderDesc = orderDesc;
    }
}
