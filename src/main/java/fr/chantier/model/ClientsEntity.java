package fr.chantier.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

import javax.persistence.*;
import java.util.Collection;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 28 oct. 2008
 * Time: 17:52:00
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(schema = "chantier", name = "clients")
public class ClientsEntity implements Serializable {
    private int clientId;

    @Id
    @NonVisual
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorClients")
    @SequenceGenerator(name = "generatorClients", sequenceName = "clients_client_id_seq")
    @Column(name = "client_id", nullable = false, length = 10)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    private String clientName;

    @Basic
    @Validate("required=true")
    @Column(name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    private boolean clientOld;

    @Basic
    @NonVisual
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

    @Override
    public String toString() {
        return "ClientsEntity{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientOld=" + clientOld +
                '}';
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
