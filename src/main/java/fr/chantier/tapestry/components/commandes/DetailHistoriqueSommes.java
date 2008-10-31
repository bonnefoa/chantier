package fr.chantier.tapestry.components.commandes;

import fr.chantier.model.*;
import fr.chantier.service.HistoriqueHeuresManager;
import fr.chantier.service.CommandesManager;
import fr.chantier.service.HistoriqueSommeManager;
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
public class DetailHistoriqueSommes {

    @Inject
    private HistoriqueSommeManager historiqueSommeManager;

    @Property
    private CommandesManager commandesManager;

    @Parameter(required = true)
    @Property
    private SousTraitantsEntity sousTraitantsEntity;

    @Parameter(required = true)
    private CommandesEntity commandesEntity;

    @Property
    private HistoriqueSommeEntity historiqueSommes;

    @Property(write = false)
    private boolean sommesToRemove;

    @Property(write = false)
    private Collection<HistoriqueSommeEntity> listHistoriqueSommes;

    /**
     * Test si il faut afficher l'historique
     *
     * @return
     */
    public boolean gethasData() {
        listHistoriqueSommes = historiqueSommeManager.recupererHistoriqueSommeBySousTraitantsAndCommandes(sousTraitantsEntity, commandesEntity);
        if (listHistoriqueSommes.size() == 0) {
            return false;
        }
        return true;
    }    

    public void setSommesToRemove(boolean checked) {
        if (checked) {
            historiqueSommeManager.makeTransient(historiqueSommes);
        }
    }

}