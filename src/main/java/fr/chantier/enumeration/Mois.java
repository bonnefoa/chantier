package fr.chantier.enumeration;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 16:52:05
 * To change this template use File | Settings | File Templates.
 */
public enum Mois {
    JANVIER(0),FEVRIER(1),MARS(2),AVRIL(3),MAI(4),JUIN(5),JUILLET(6),AOUT(7),SEPTEMBRE(8),OCTOBRE(9),NOVEMBRE(10),DECEMBRE(11);

    private static Map<Integer,Mois> lookup ;
    static{
        lookup = new HashMap<Integer,Mois>();
        for (Mois mois : EnumSet.allOf(Mois.class)) {
            lookup.put(mois.getI(),mois);
        }

    }
    private int i;

    Mois(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public static Mois lookup(int i) {
        if(lookup.containsKey(i)){
            return lookup.get(i);
        }
        return null;
    }
}
