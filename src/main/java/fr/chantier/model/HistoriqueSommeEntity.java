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
@Table(schema = "chantier", name = "historique_somme")
public class HistoriqueSommeEntity implements Serializable {
    private int historiqueSommeId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generatorCommandes")
    @SequenceGenerator(name="generatorCommandes",sequenceName="historique_somme_historique_somme_id_seq")    
    @Column(name = "historique_somme_id", nullable = false, length = 10)
    public int getHistoriqueSommeId() {
        return historiqueSommeId;
    }

    public void setHistoriqueSommeId(int historiqueSommeId) {
        this.historiqueSommeId = historiqueSommeId;
    }

    private Float historiqueSomme;

    @Basic
    @Column(name = "historique_somme", nullable = false, length = 9, precision = 2)
    public Float getHistoriqueSomme() {
        return historiqueSomme;
    }

    public void setHistoriqueSomme(Float historiqueSomme) {
        this.historiqueSomme = historiqueSomme;
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

        HistoriqueSommeEntity that = (HistoriqueSommeEntity) o;

        if (historiqueSommeId != that.historiqueSommeId) return false;
        if (historiqueDate != null ? !historiqueDate.equals(that.historiqueDate) : that.historiqueDate != null)
            return false;
        if (historiqueSomme != null ? !historiqueSomme.equals(that.historiqueSomme) : that.historiqueSomme != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = historiqueSommeId;
        result = 31 * result + (historiqueSomme != null ? historiqueSomme.hashCode() : 0);
        result = 31 * result + (historiqueDate != null ? historiqueDate.hashCode() : 0);
        return result;
    }

    private SousTraitantsEntity sousTraitantsByStId;

    @ManyToOne
    @JoinColumn(name = "st_id", referencedColumnName = "st_id")
    public SousTraitantsEntity getSousTraitantsByStId() {
        return sousTraitantsByStId;
    }

    public void setSousTraitantsByStId(SousTraitantsEntity sousTraitantsByStId) {
        this.sousTraitantsByStId = sousTraitantsByStId;
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
}
