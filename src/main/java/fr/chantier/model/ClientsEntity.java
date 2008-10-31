package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;

import javax.persistence.*;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 22:52:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(catalog = "chantier", name = "clients")
public class ClientsEntity implements Serializable {
    private int clientId;

    @Id
    @NonVisual
    @GeneratedValue
    @Column(name = "client_id", nullable = false, length = 8)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private String clientName;

    @Basic
    @Column(name = "client_name", nullable = false)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private boolean clientOld;

    @Basic
    @NonVisual
    @Column(name = "client_old", nullable = false, length = 0)
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

        ClientsEntity that = (ClientsEntity) o;

        if (clientId != that.clientId) return false;
        if (clientOld != that.clientOld) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientOld ? 1 : 0);
        return result;
    }

    private Collection<CommandesEntity> commandesesByClientId;

    @OneToMany(mappedBy = "clientsByClientId")
    public Collection<CommandesEntity> getCommandesesByClientId() {
        return commandesesByClientId;
    }

    public void setCommandesesByClientId(Collection<CommandesEntity> commandesesByClientId) {
        this.commandesesByClientId = commandesesByClientId;
    }
}
