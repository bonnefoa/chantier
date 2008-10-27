package fr.chantier.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:09:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "historique_heures")
public class HistoriqueHeures {
    private BigInteger historiqueHeures;

    @Basic
    @Column(name = "historique_heures", nullable = false, length = 5, precision = 2)
    public BigInteger getHistoriqueHeures() {
        return historiqueHeures;
    }

    public void setHistoriqueHeures(BigInteger historiqueHeures) {
        this.historiqueHeures = historiqueHeures;
    }

    private Timestamp historiqueDate;

    @Basic
    @Column(name = "historique_date", nullable = false, length = 29, precision = 6)
    public Timestamp getHistoriqueDate() {
        return historiqueDate;
    }

    public void setHistoriqueDate(Timestamp historiqueDate) {
        this.historiqueDate = historiqueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoriqueHeures that = (HistoriqueHeures) o;

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
        return result;
    }

    private Commandes commandesByCommandId;

    @ManyToOne
    @JoinColumn(name = "command_id", referencedColumnName = "command_id")
    public Commandes getCommandesByCommandId() {
        return commandesByCommandId;
    }

    public void setCommandesByCommandId(Commandes commandesByCommandId) {
        this.commandesByCommandId = commandesByCommandId;
    }

    private Intervenants intervenantsByInterId;

    @ManyToOne
    @JoinColumn(name = "inter_id", referencedColumnName = "inter_id")
    public Intervenants getIntervenantsByInterId() {
        return intervenantsByInterId;
    }

    public void setIntervenantsByInterId(Intervenants intervenantsByInterId) {
        this.intervenantsByInterId = intervenantsByInterId;
    }

    private int historiqueHeuresId;

    @Id
    @Column(name = "historique_heures_id", nullable = false, length = 10)
    public int getHistoriqueHeuresId() {
        return historiqueHeuresId;
    }

    public void setHistoriqueHeuresId(int historiqueHeuresId) {
        this.historiqueHeuresId = historiqueHeuresId;
    }
}
