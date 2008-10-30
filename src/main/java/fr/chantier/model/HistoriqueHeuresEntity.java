package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 28 oct. 2008
 * Time: 17:52:02
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "historique_heures")
public class HistoriqueHeuresEntity implements Serializable {
    private int historiqueHeuresId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCommandes")
    @SequenceGenerator(name = "generatorCommandes", sequenceName = "historique_heures_historique_heures_id_seq")
    @Column(name = "historique_heures_id", nullable = false, length = 10)
    public int getHistoriqueHeuresId() {
        return historiqueHeuresId;
    }

    public void setHistoriqueHeuresId(int historiqueHeuresId) {
        this.historiqueHeuresId = historiqueHeuresId;
    }

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
    @Column(name = "historique_date", nullable = false, length = 29, precision = 6)
    public Date getHistoriqueDate() {
        return historiqueDate;
    }

    public void setHistoriqueDate(Date historiqueDate) {
        this.historiqueDate = historiqueDate;
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
        int result = historiqueHeuresId;
        result = 31 * result + (historiqueHeures != null ? historiqueHeures.hashCode() : 0);
        result = 31 * result + (historiqueDate != null ? historiqueDate.hashCode() : 0);
        return result;
    }

    private CommandesEntity commandesByCommandId;

    @ManyToOne
    @JoinColumn(name = "command_id", referencedColumnName = "command_id")
    public CommandesEntity getCommandesByCommandId() {
        return commandesByCommandId;
    }

    public void setCommandesByCommandId(CommandesEntity commandesByCommandId) {
        this.commandesByCommandId = commandesByCommandId;
    }

    private IntervenantsEntity intervenantsByInterId;

    @ManyToOne
    @JoinColumn(name = "inter_id", referencedColumnName = "inter_id")
    public IntervenantsEntity getIntervenantsByInterId() {
        return intervenantsByInterId;
    }

    public void setIntervenantsByInterId(IntervenantsEntity intervenantsByInterId) {
        this.intervenantsByInterId = intervenantsByInterId;
    }
}
