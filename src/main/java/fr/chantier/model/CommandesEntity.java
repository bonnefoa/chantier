package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 28 oct. 2008
 * Time: 17:52:01
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "commandes")
public class CommandesEntity implements Serializable {
    private int commandId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCommandes")
    @SequenceGenerator(name = "generatorCommandes", sequenceName = "commandes_command_id_seq")
    @Column(name = "command_id", nullable = false, length = 10)
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    private Float commandDevis;

    @Basic
    @Column(name = "command_devis", nullable = false, length = 9, precision = 2)
    public Float getCommandDevis() {
        return commandDevis;
    }

    public void setCommandDevis(Float commandDevis) {
        this.commandDevis = commandDevis;
    }

    private Date commandDate;

    @Basic
    @Column(name = "command_date", nullable = false, length = 29, precision = 6)
    public Date getCommandDate() {
        return commandDate;
    }

    public void setCommandDate(Date commandDate) {
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
    @NonVisual
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

        CommandesEntity that = (CommandesEntity) o;

        if (commandId != that.commandId) return false;
        if (finalise != that.finalise) return false;
        if (commandDate != null ? !commandDate.equals(that.commandDate) : that.commandDate != null) return false;
        if (commandDevis != null ? !commandDevis.equals(that.commandDevis) : that.commandDevis != null) return false;
        if (commandLibelle != null ? !commandLibelle.equals(that.commandLibelle) : that.commandLibelle != null)
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

    private ClientsEntity clientsByClientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    public ClientsEntity getClientsByClientId() {
        return clientsByClientId;
    }

    public void setClientsByClientId(ClientsEntity clientsByClientId) {
        this.clientsByClientId = clientsByClientId;
    }

    private Collection<HistoriqueHeuresEntity> historiqueHeuresesByCommandId;

    @OneToMany(mappedBy = "commandesByCommandId")
    public Collection<HistoriqueHeuresEntity> getHistoriqueHeuresesByCommandId() {
        return historiqueHeuresesByCommandId;
    }

    public void setHistoriqueHeuresesByCommandId(Collection<HistoriqueHeuresEntity> historiqueHeuresesByCommandId) {
        this.historiqueHeuresesByCommandId = historiqueHeuresesByCommandId;
    }

    private Collection<HistoriqueSommeEntity> historiqueSommesByCommandId;

    @OneToMany(mappedBy = "commandesByCommandId")
    public Collection<HistoriqueSommeEntity> getHistoriqueSommesByCommandId() {
        return historiqueSommesByCommandId;
    }

    public void setHistoriqueSommesByCommandId(Collection<HistoriqueSommeEntity> historiqueSommesByCommandId) {
        this.historiqueSommesByCommandId = historiqueSommesByCommandId;
    }
}
