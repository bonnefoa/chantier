package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.*;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 28 oct. 2008
 * Time: 17:52:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "intervenants")
public class IntervenantsEntity implements Serializable {
    private int interId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCommandes")
    @SequenceGenerator(name = "generatorCommandes", sequenceName = "intervenants_inter_id_seq")
    @Column(name = "inter_id", nullable = false, length = 10)
    public int getInterId() {
        return interId;
    }

    public void setInterId(int interId) {
        this.interId = interId;
    }

    private String interName;

    @Basic
        @Validate("required=true")
    @Column(name = "inter_name")
    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    private String interFirstname;

    @Basic
    @NonVisual
    @Column(name = "inter_firstname")
    public String getInterFirstname() {
        return interFirstname;
    }

    public void setInterFirstname(String interFirstname) {
        this.interFirstname = interFirstname;
    }

    private boolean interOld;

    @Basic
    @NonVisual
    @Column(name = "inter_old", nullable = false, length = 1)
    public boolean isInterOld() {
        return interOld;
    }

    public void setInterOld(boolean interOld) {
        this.interOld = interOld;
    }

    private short interOrdre;

    @Basic
    @Column(name = "inter_ordre", nullable = false, length = 5)
    public short getInterOrdre() {
        return interOrdre;
    }

    public void setInterOrdre(short interOrdre) {
        this.interOrdre = interOrdre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntervenantsEntity that = (IntervenantsEntity) o;

        if (interId != that.interId) return false;
        if (interOld != that.interOld) return false;
        if (interOrdre != that.interOrdre) return false;
        if (interFirstname != null ? !interFirstname.equals(that.interFirstname) : that.interFirstname != null)
            return false;
        if (interName != null ? !interName.equals(that.interName) : that.interName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = interId;
        result = 31 * result + (interName != null ? interName.hashCode() : 0);
        result = 31 * result + (interFirstname != null ? interFirstname.hashCode() : 0);
        result = 31 * result + (interOld ? 1 : 0);
        result = 31 * result + (int) interOrdre;
        return result;
    }

    private Collection<HistoriqueHeuresEntity> historiqueHeuresesByInterId;

    @OneToMany(mappedBy = "intervenantsByInterId")
    public Collection<HistoriqueHeuresEntity> getHistoriqueHeuresesByInterId() {
        return historiqueHeuresesByInterId;
    }

    public void setHistoriqueHeuresesByInterId(Collection<HistoriqueHeuresEntity> historiqueHeuresesByInterId) {
        this.historiqueHeuresesByInterId = historiqueHeuresesByInterId;
    }
}
