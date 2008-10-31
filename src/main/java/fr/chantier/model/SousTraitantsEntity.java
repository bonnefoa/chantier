package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 22:52:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(catalog = "chantier", name = "sous_traitants")
public class SousTraitantsEntity implements Serializable {
    private int stId;

    @Id
    @NonVisual
    @GeneratedValue
    @Column(name = "st_id", nullable = false, length = 8)
    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    private String stName;

    @Basic
    @Column(name = "st_name", nullable = false)
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    private boolean stOld;

    @Basic
    @NonVisual
    @Column(name = "St_old", nullable = false, length = 0)
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
