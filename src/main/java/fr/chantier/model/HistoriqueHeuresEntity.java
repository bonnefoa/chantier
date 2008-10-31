package fr.chantier.model;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 22:52:32
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(catalog = "chantier", name = "historique_heures")
public class HistoriqueHeuresEntity  implements Serializable {
    private Float historiqueHeures;

    @Basic
    @Column(name = "historique_heures", nullable = false, length = 5, precision = 2)
    public Float getHistoriqueHeures() {
        return historiqueHeures;
    }

    public void setHistoriqueHeures(Float historiqueHeures) {
        this.historiqueHeures = historiqueHeures;
    }

    private Date historiqueDate;

    @Basic
    @Column(name = "historique_date", nullable = false, length = 19)
    public Date getHistoriqueDate() {
        return historiqueDate;
    }

    public void setHistoriqueDate(Date historiqueDate) {
        this.historiqueDate = historiqueDate;
    }

    private int historiqueHeuresId;

    @Id
    @GeneratedValue
    @Column(name = "historique_heures_id", nullable = false, length = 8)
    public int getHistoriqueHeuresId() {
        return historiqueHeuresId;
    }

    public void setHistoriqueHeuresId(int historiqueHeuresId) {
        this.historiqueHeuresId = historiqueHeuresId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoriqueHeuresEntity that = (HistoriqueHeuresEntity) o;

        if (historiqueHeuresId != that.historiqueHeuresId) return false;
        if (historiqueDate != null ? !historiqueDate.equals(that.historiqueDate) : that.historiqueDate != null)
            return false;
        if (historiqueHeures != null ? !historiqueHeures.equals(that.historiqueHeures) : that.historiqueHeures != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = historiqueHeures != null ? historiqueHeures.hashCode() : 0;
        result = 31 * result + (historiqueDate != null ? historiqueDate.hashCode() : 0);
        result = 31 * result + historiqueHeuresId;
        return result;
    }

    private IntervenantsEntity intervenantsByInterId;

    @ManyToOne
    @JoinColumn(name = "inter_id", referencedColumnName = "inter_id", nullable = false)
    public IntervenantsEntity getIntervenantsByInterId() {
        return intervenantsByInterId;
    }

    public void setIntervenantsByInterId(IntervenantsEntity intervenantsByInterId) {
        this.intervenantsByInterId = intervenantsByInterId;
    }

    private CommandesEntity commandesByCommandId;

    @ManyToOne
    @JoinColumn(name = "command_id", referencedColumnName = "command_id", nullable = false)
    public CommandesEntity getCommandesByCommandId() {
        return commandesByCommandId;
    }

    public void setCommandesByCommandId(CommandesEntity commandesByCommandId) {
        this.commandesByCommandId = commandesByCommandId;
    }
}
