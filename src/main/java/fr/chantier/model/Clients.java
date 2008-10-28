package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: sora
 * Date: Oct 27, 2008
 * Time: 5:00:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "clients")
public class Clients {
    private int clientId;

    @Id
    @NonVisual
    @Column(name = "client_id", nullable = false, length = 10)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private String clientName;

    @Basic
    @Validate("required")
    @Column(name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private boolean clientOld;

    @Basic
    @Column(name = "client_old", nullable = false, length = 1)
    public boolean isClientOld() {
        return clientOld;
    }

    public void setClientOld(boolean clientOld) {
        this.clientOld = clientOld;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clients clients = (Clients) o;

        if (clientId != clients.clientId) return false;
        if (clientOld != clients.clientOld) return false;
        if (clientName != null ? !clientName.equals(clients.clientName) : clients.clientName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientOld ? 1 : 0);
        return result;
    }

    private Collection<Commandes> commandesesByClientId;

    @OneToMany(mappedBy = "clientsByClientId")
    public Collection<Commandes> getCommandesesByClientId() {
        return commandesesByClientId;
    }

    public void setCommandesesByClientId(Collection<Commandes> commandesesByClientId) {
        this.commandesesByClientId = commandesesByClientId;
    }
}
