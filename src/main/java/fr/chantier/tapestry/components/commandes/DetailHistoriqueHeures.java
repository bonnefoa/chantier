package fr.chantier.tapestry.components.commandes;

import fr.chantier.model.HistoriqueHeuresEntity;
import fr.chantier.model.IntervenantsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.service.HistoriqueHeuresManager;
import fr.chantier.service.CommandesManager;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 08:38:12
 * To change this template use File | Settings | File Templates.
 */
public class DetailHistoriqueHeures {

    @Inject
    private HistoriqueHeuresManager historiqueHeuresManager;

    @Property
    private CommandesManager commandesManager;

    @Parameter(required = true)
    @Property
    private IntervenantsEntity intervenantsEntity;

    @Parameter(required = true)
    private CommandesEntity commandesEntity;

    @Property
    private HistoriqueHeuresEntity historiqueHeures;

    @Property(write = false)
    private boolean heuresToRemove;

    /**
     * Getter de liste pour la boucle
     *
     * @return
     */
    public Collection<HistoriqueHeuresEntity> getListHistoriqueHeures() {
        Collection<HistoriqueHeuresEntity> res = historiqueHeuresManager.findHistoriqueHeuresByIntervenantAndCommand(intervenantsEntity, commandesEntity);
        return res;
    }

    public int getHeuresIndiv() {
        int heure = (int) Math.floor(historiqueHeures.getHistoriqueHeures());
        return heure;
    }

    public int getMinutesIndiv() {
        return (int) ((historiqueHeures.getHistoriqueHeures() - getHeuresIndiv()) * 60);
    }

    public void setHeuresToRemove(boolean checked) {
        if (checked) {
            historiqueHeuresManager.makeTransient(historiqueHeures);
        }
    }

}
