package fr.chantier.tapestry.components.commandes;

import fr.chantier.enumeration.Mois;
import fr.chantier.enumeration.TypeClassement;
import fr.chantier.enumeration.TypeFinalise;
import fr.chantier.enumeration.TypeOrdonnancement;
import fr.chantier.model.ClientsEntity;
import fr.chantier.model.CommandesEntity;
import fr.chantier.service.ClientsManager;
import fr.chantier.service.CommandesManager;
import fr.chantier.tools.EncoderBase64;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;

import javax.servlet.http.Cookie;
import java.util.*;

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

    @Inject
    private RequestGlobals requestGlobals;

    @Inject
    private Request request;

    @Retain
    private EncoderBase64 encoder;

    @Property
    private TypeClassement typeClassement;

    @Property
    private TypeOrdonnancement typeOrdonnancement;

    @Property
    private TypeFinalise typeFinalise;

    /**
     * Boolean pour l'activation de la selection des commandes par date
     */
    @Property
    private Boolean activateDate;

    @Property
    private Mois mois;

    @Property
    private Integer annee;

    private Calendar calendar;

    @Persist("entity")
    private ClientsEntity clientsEntity;

    @Persist("entity")
    private CommandesEntity commandesEntity;
    @Property
    private String inputSearch;

    @PageLoaded
    private void onPageLoaded() {
        encoder = new EncoderBase64();
        initCookies();
        if (mois == null || annee == null) {
            activateDate = true;
            Calendar cal = Calendar.getInstance();
            mois = Mois.lookup(cal.get(Calendar.MONTH));
            annee = cal.get(Calendar.YEAR);
        }
        if (typeOrdonnancement == null) {
            typeOrdonnancement = TypeOrdonnancement.DECROISSANTE;
        }
        if (typeClassement == null) {
            typeClassement = TypeClassement.NUMERO;
        }
        if (typeFinalise == null) {
            typeFinalise = TypeFinalise.LES_DEUX;
        }
        writeCookies();
    }

    private void initCookies() {
        mois = (Mois) getCookieValue(Mois.class.getCanonicalName());
        annee = (Integer) getCookieValue("Annee");
        typeOrdonnancement = (TypeOrdonnancement) getCookieValue(TypeOrdonnancement.class.getCanonicalName());
        typeClassement = (TypeClassement) getCookieValue(TypeClassement.class.getCanonicalName());
        typeFinalise = (TypeFinalise) getCookieValue(TypeFinalise.class.getCanonicalName());
        activateDate = (Boolean) getCookieValue("activateDate");
    }


    private void writeCookies() {
        createCookie(Mois.class.getCanonicalName(), encoder.toClient(mois));
        createCookie("Annee", encoder.toClient(annee));
        createCookie(TypeOrdonnancement.class.getCanonicalName(), encoder.toClient(typeOrdonnancement));
        createCookie(TypeClassement.class.getCanonicalName(), encoder.toClient(typeClassement));
        createCookie(TypeFinalise.class.getCanonicalName(), encoder.toClient(typeFinalise));
        createCookie("activateDate",encoder.toClient(activateDate));
    }

    private void createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        requestGlobals.getHTTPServletResponse().addCookie(cookie);
    }

    private Object getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if (cookie != null) {
            return encoder.toValue(cookie.getValue());
        }
        return null;
    }

    private Cookie getCookie(String name) {
        Cookie cookies[] = requestGlobals.getHTTPServletRequest().getCookies();
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals(name)) {
                    return cooky;
                }
            }
        }
        return null;
    }

    @SetupRender
    private void onSetupRender() {
        initCookies();
    }


    @OnEvent(component = "classementForm", value = Form.SUCCESS)
    private void onSuccessFromClassementForm() {
        writeCookies();
    }

    /**
     * Proposition d'une liste  de choix lors de la recherche de client
     *
     * @param input
     *
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
            commandesEntity = commandesManager.findById(Integer.valueOf(inputSearch));
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
        initCookies();        
        if (commandesEntity != null) {
            ArrayList<CommandesEntity> temp = new ArrayList<CommandesEntity>();
            temp.add(commandesEntity);
            commandesEntity = null;
            return temp;
        }
        Date dateAfter = null;
        Date date = null;
        if (activateDate != null && activateDate) {
            calendar = new GregorianCalendar();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.YEAR, annee);
            calendar.set(Calendar.MONTH, mois.getI());
            date = calendar.getTime();
        }
        if (commandesEntity != null) {
            return commandesManager.findByCriterions(clientsEntity, typeClassement.getOrder(typeOrdonnancement), null, null);
        }
        if (typeFinalise.equals(TypeFinalise.COMMANDES_DU_MOIS_ET_NON_FINALISEES)) {
            return commandesManager.findNonFinaliseAndMonth(typeClassement.getOrder(typeOrdonnancement), date);
        }
        return commandesManager.findByCriterions(clientsEntity, typeClassement.getOrder(typeOrdonnancement), typeFinalise.getCriterion(), date);
    }
}