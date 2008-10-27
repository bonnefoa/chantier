package fr.chantier.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:00:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "coefficient")
public class Coefficient {
    private BigInteger stCoef;

    @Basic
    @Column(name = "st_coef", nullable = false, length = 5, precision = 4)
    public BigInteger getStCoef() {
        return stCoef;
    }

    public void setStCoef(BigInteger stCoef) {
        this.stCoef = stCoef;
    }

    private BigInteger interCoef;

    @Basic
    @Column(name = "inter_coef", nullable = false, length = 5, precision = 2)
    public BigInteger getInterCoef() {
        return interCoef;
    }

    public void setInterCoef(BigInteger interCoef) {
        this.interCoef = interCoef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coefficient that = (Coefficient) o;

        if (interCoef != null ? !interCoef.equals(that.interCoef) : that.interCoef != null) return false;
        if (stCoef != null ? !stCoef.equals(that.stCoef) : that.stCoef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stCoef != null ? stCoef.hashCode() : 0;
        result = 31 * result + (interCoef != null ? interCoef.hashCode() : 0);
        return result;
    }

    private int coefficientId;

    @Id
    @Column(name = "coefficient_id", nullable = false, length = 10)
    public int getCoefficientId() {
        return coefficientId;
    }

    public void setCoefficientId(int coefficientId) {
        this.coefficientId = coefficientId;
    }
}
