package fr.chantier.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:01:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "sous_traitants")
public class SousTraitants {
    private int stId;

    @Id
    @Column(name = "st_id", nullable = false, length = 10)
    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    private String stName;

    @Basic
    @Column(name = "st_name")
    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    private boolean stOld;

    @Basic
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

        SousTraitants that = (SousTraitants) o;

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

    private Collection<HistoriqueSomme> historiqueSommesByStId;

    @OneToMany(mappedBy = "sousTraitantsByStId")
    public Collection<HistoriqueSomme> getHistoriqueSommesByStId() {
        return historiqueSommesByStId;
    }

    public void setHistoriqueSommesByStId(Collection<HistoriqueSomme> historiqueSommesByStId) {
        this.historiqueSommesByStId = historiqueSommesByStId;
    }
}
