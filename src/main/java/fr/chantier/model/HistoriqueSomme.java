package fr.chantier.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:01:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "historique_somme")
public class HistoriqueSomme {
    private BigInteger historiqueSomme;

    @Basic
    @Column(name = "historique_somme", nullable = false, length = 9, precision = 2)
    public BigInteger getHistoriqueSomme() {
        return historiqueSomme;
    }

    public void setHistoriqueSomme(BigInteger historiqueSomme) {
        this.historiqueSomme = historiqueSomme;
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

        HistoriqueSomme that = (HistoriqueSomme) o;

        if (historiqueDate != null ? !historiqueDate.equals(that.historiqueDate) : that.historiqueDate != null)
            return false;
        if (historiqueSomme != null ? !historiqueSomme.equals(that.historiqueSomme) : that.historiqueSomme != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = historiqueSomme != null ? historiqueSomme.hashCode() : 0;
        result = 31 * result + (historiqueDate != null ? historiqueDate.hashCode() : 0);
        return result;
    }

    private SousTraitants sousTraitantsByStId;

    @ManyToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id")
    public SousTraitants getSousTraitantsByStId() {
        return sousTraitantsByStId;
    }

    public void setSousTraitantsByStId(SousTraitants sousTraitantsByStId) {
        this.sousTraitantsByStId = sousTraitantsByStId;
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

    private int historiqueSommeId;

    @Id
    @Column(name = "historique_somme_id", nullable = false, length = 10)
    public int getHistoriqueSommeId() {
        return historiqueSommeId;
    }

    public void setHistoriqueSommeId(int historiqueSommeId) {
        this.historiqueSommeId = historiqueSommeId;
    }
}
