package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 22:52:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(catalog = "chantier", name = "coefficient")
public class CoefficientEntity implements Serializable {
    private Float stCoef;

    @Basic
    @Column(name = "st_coef", nullable = false, length = 5, precision = 4)
    public Float getStCoef() {
        return stCoef;
    }

    public void setStCoef(Float stCoef) {
        this.stCoef = stCoef;
    }

    private Float interCoef;

    @Basic
    @Column(name = "inter_coef", nullable = false, length = 5, precision = 2)
    public Float getInterCoef() {
        return interCoef;
    }

    public void setInterCoef(Float interCoef) {
        this.interCoef = interCoef;
    }

    private int coefficientId;

    @Id
    @NonVisual
    @Column(name = "coefficient_id", nullable = false, length = 8)
    public int getCoefficientId() {
        return coefficientId;
    }

    public void setCoefficientId(int coefficientId) {
        this.coefficientId = coefficientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoefficientEntity that = (CoefficientEntity) o;

        if (coefficientId != that.coefficientId) return false;
        if (interCoef != null ? !interCoef.equals(that.interCoef) : that.interCoef != null) return false;
        if (stCoef != null ? !stCoef.equals(that.stCoef) : that.stCoef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stCoef != null ? stCoef.hashCode() : 0;
        result = 31 * result + (interCoef != null ? interCoef.hashCode() : 0);
        result = 31 * result + coefficientId;
        return result;
    }
}
