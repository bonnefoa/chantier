package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:01:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "commandes")
public class Commandes {
    private int commandId;

    @Id    
    @Column(name = "command_id", nullable = false, length = 10)
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    private BigInteger commandDevis;

    @Basic
    @Column(name = "command_devis", nullable = false, length = 9, precision = 2)
    public BigInteger getCommandDevis() {
        return commandDevis;
    }

    public void setCommandDevis(BigInteger commandDevis) {
        this.commandDevis = commandDevis;
    }

    private Timestamp commandDate;

    @Basic
    @Column(name = "command_date", nullable = false, length = 29, precision = 6)
    public Timestamp getCommandDate() {
        return commandDate;
    }

    public void setCommandDate(Timestamp commandDate) {
        this.commandDate = commandDate;
    }

    private String commandLibelle;

    @Basic
    @Column(name = "command_libelle")
    public String getCommandLibelle() {
        return commandLibelle;
    }

    public void setCommandLibelle(String commandLibelle) {
        this.commandLibelle = commandLibelle;
    }

    private boolean finalise;

    @Basic
    @Column(name = "finalise", nullable = false, length = 1)
    public boolean isFinalise() {
        return finalise;
    }

    public void setFinalise(boolean finalise) {
        this.finalise = finalise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commandes commandes = (Commandes) o;

        if (commandId != commandes.commandId) return false;
        if (finalise != commandes.finalise) return false;
        if (commandDate != null ? !commandDate.equals(commandes.commandDate) : commandes.commandDate != null)
            return false;
        if (commandDevis != null ? !commandDevis.equals(commandes.commandDevis) : commandes.commandDevis != null)
            return false;
        if (commandLibelle != null ? !commandLibelle.equals(commandes.commandLibelle) : commandes.commandLibelle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commandId;
        result = 31 * result + (commandDevis != null ? commandDevis.hashCode() : 0);
        result = 31 * result + (commandDate != null ? commandDate.hashCode() : 0);
        result = 31 * result + (commandLibelle != null ? commandLibelle.hashCode() : 0);
        result = 31 * result + (finalise ? 1 : 0);
        return result;
    }

    private Clients clientsByClientId;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    public Clients getClientsByClientId() {
        return clientsByClientId;
    }

    public void setClientsByClientId(Clients clientsByClientId) {
        this.clientsByClientId = clientsByClientId;
    }

    private Collection<HistoriqueSomme> historiqueSommesByCommandId;

    @OneToMany(mappedBy = "commandesByCommandId")
    public Collection<HistoriqueSomme> getHistoriqueSommesByCommandId() {
        return historiqueSommesByCommandId;
    }

    public void setHistoriqueSommesByCommandId(Collection<HistoriqueSomme> historiqueSommesByCommandId) {
        this.historiqueSommesByCommandId = historiqueSommesByCommandId;
    }

    private Collection<HistoriqueHeures> historiqueHeuresesByCommandId;

    @OneToMany(mappedBy = "commandesByCommandId")
    public Collection<HistoriqueHeures> getHistoriqueHeuresesByCommandId() {
        return historiqueHeuresesByCommandId;
    }

    public void setHistoriqueHeuresesByCommandId(Collection<HistoriqueHeures> historiqueHeuresesByCommandId) {
        this.historiqueHeuresesByCommandId = historiqueHeuresesByCommandId;
    }
}
