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
@Table(schema = "chantier", name = "sous_traitants")
public class SousTraitantsEntity implements Serializable {
    private int stId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCommandes")
    @SequenceGenerator(name = "generatorCommandes", sequenceName = "sous_traitants_st_id_seq")
    @Column(name = "st_id", nullable = false, length = 10)
    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    private String stName;

    @Basic
    @Validate("required=true")
    @Column(name = "st_name")
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    private boolean stOld;

    @Basic
    @NonVisual
    @Column(name = "st_old", nullable = false, length = 1)
    public boolean isStOld() {
        return stOld;
    }

    public void setStOld(boolean stOld) {
        this.stOld = stOld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SousTraitantsEntity that = (SousTraitantsEntity) o;

        if (stId != that.stId) return false;
        if (stOld != that.stOld) return false;
        if (stName != null ? !stName.equals(that.stName) : that.stName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stId;
        result = 31 * result + (stName != null ? stName.hashCode() : 0);
        result = 31 * result + (stOld ? 1 : 0);
        return result;
    }

    private Collection<HistoriqueSommeEntity> historiqueSommesByStId;

    @OneToMany(mappedBy = "sousTraitantsByStId")
    public Collection<HistoriqueSommeEntity> getHistoriqueSommesByStId() {
        return historiqueSommesByStId;
    }

    public void setHistoriqueSommesByStId(Collection<HistoriqueSommeEntity> historiqueSommesByStId) {
        this.historiqueSommesByStId = historiqueSommesByStId;
    }
}
