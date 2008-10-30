package fr.chantier.tapestry.components.commandes;

import fr.chantier.enumeration.Mois;
import fr.chantier.enumeration.TypeClassement;
import fr.chantier.enumeration.TypeFinalise;
import fr.chantier.enumeration.TypeOrdonnancement;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.service.ClientsManager;
import fr.chantier.service.CommandesManager;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 30 oct. 2008
 * Time: 14:46:50
 * To change this template use File | Settings | File Templates.
 */
public class HeaderCommand {

    @Inject
    private CommandesManager commandesManager;

    @Inject
    private ClientsManager clientsManager;

    @Property
    @Persist("cookie")
    private TypeClassement typeClassement;

    @Property
    @Persist("cookie")
    private TypeOrdonnancement typeOrdonnancement;

    @Property
    @Persist("cookie")
    private TypeFinalise typeFinalise;

    /**
     * Boolean pour l'activation de la selection des commandes par date
     */
    @Property
    @Persist("cookie")
    private Boolean activateDate;

    @Property
    @Persist("cookie")
    private Mois mois;

    @Property
    @Persist("cookie")
    private Integer annee;

    private Calendar calendar;

    @Persist("flash")
    private ClientsEntity clientsEntity;

    private CommandesEntity commandesEntity;

    @Property
    private String inputSearch;

    @PageLoaded
    private void onPageLoaded() {
        if (mois == null || annee == null) {
            Calendar cal = Calendar.getInstance();
            mois = Mois.lookup(cal.get(Calendar.MONTH));
            annee = cal.get(Calendar.YEAR);
        }
        if (typeOrdonnancement == null) {
            typeOrdonnancement = TypeOrdonnancement.CROISSANTE;
        }
        if (typeClassement == null) {
            typeClassement = TypeClassement.DATE;
        }
        if (typeFinalise == null) {
            typeFinalise = TypeFinalise.LES_DEUX;
        }
    }

    /**
     * Proposition d'une liste  de choix lors de la recherche de client
     *
     * @param input
     * @return
     */
    @OnEvent(component = "rechercheClient", value = "providecompletions")
    private Collection<String> onProvideCompletionsFromMyField(String input) {
        return clientsManager.findByName(input);
    }

    /**
     * Recherche de client par le formulaire
     */
    @OnEvent(component = "rechercheClientForm", value = Form.SUCCESS)
    private void onSuccessFromRechercheClientForm() {
        try {
            commandesEntity = commandesManager.findById(Integer.valueOf(inputSearch), false);
        } catch (NumberFormatException e) {
            clientsEntity = clientsManager.findClientByName(inputSearch);
        }
    }

    /**
     * Retourne la liste des commandes selon l'etat des classements
     *
     * @return
     */
    public Collection<CommandesEntity> getCommandesEntityCollection() {
        Collection<CommandesEntity> res;
        Date dateAfter = null;
        Date dateBefore = null;
        if (activateDate != null && activateDate) {
            calendar = new GregorianCalendar();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.YEAR, annee);
            calendar.set(Calendar.MONTH, mois.getI());
            dateBefore = calendar.getTime();
            calendar = (Calendar) calendar.clone();
            calendar.add(Calendar.MONTH, 1);
            dateAfter = calendar.getTime();
        }

        res = commandesManager.findByCriterions(clientsEntity, typeClassement.getOrder(typeOrdonnancement), typeFinalise.getSimpleExpression(), dateBefore, dateAfter);
        return res;
    }
}