/*
 * Created on 12 Apr 2008
 *
 * @author nicholas[dot] krul _at_ gmail com
 *
 */
package fr.chantier.tapestry.base;

import static org.apache.tapestry5.ioc.internal.util.Defense.notBlank;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.PersistentFieldStrategy;
import org.apache.tapestry5.services.PersistentFieldChange;
import org.apache.tapestry5.internal.services.PersistentFieldChangeImpl;

import javax.servlet.http.Cookie;

import fr.chantier.tools.EncoderBase64;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class CookiePersistentField implements PersistentFieldStrategy {
        public static final String COOKIE = "cookie";
        public static final String FLASHCOOKIE = "flashcookie";

        private String persistenceType;
        private static final String SEPERATOR = "|";

        private RequestGlobals requestGlobals;
        private Request request;

        private EncoderBase64 encoder;

        public CookiePersistentField(RequestGlobals requestGlobals, Request request, String persistenceType) {
                this.requestGlobals = requestGlobals;
                this.request = request;
                this.persistenceType = persistenceType;
                encoder = new EncoderBase64();
        }

        public void postChange(String pageName, String componentId, String fieldName, Object newValue) {
        notBlank(pageName, "pageName");
        notBlank(fieldName, "fieldName");

        StringBuilder builder = new StringBuilder(persistenceType);
        builder.append(SEPERATOR);
        builder.append(pageName);
        builder.append(SEPERATOR);

        if (componentId != null) builder.append(componentId);

        builder.append(SEPERATOR);
        builder.append(fieldName);

        String key = builder.toString();

        if (newValue != null) {
                String value = encoder.toClient(newValue);
                createCookie(key, value);
        } else { //newValue == null
                deleteCookie(key);
        }
        }

         private PersistentFieldChange buildChange(Cookie cookie) {
                String value = cookie.getValue();
                if (value == null || value.isEmpty()) return null; //not needed

        String[] chunks = cookie.getName().split("\\" + SEPERATOR); //oops... regexp
        String componentId = chunks[2];
        String fieldName = chunks[3];

        Object attribute = encoder.toValue(value);
        return new PersistentFieldChangeImpl(componentId, fieldName, attribute);
    }

   public Collection<PersistentFieldChange> gatherFieldChanges(String pageName) {
           Collection<PersistentFieldChange> changes = new ArrayList<PersistentFieldChange>();
           String fullPrefix = persistenceType + SEPERATOR + pageName + SEPERATOR;
           for (Cookie cookie: getCookiesStartingWith(fullPrefix)) {
                   try {
                           PersistentFieldChange fieldChange = buildChange(cookie);
                           if (fieldChange != null) changes.add(fieldChange);
                           if (persistenceType.equals(FLASHCOOKIE)) deleteCookie(cookie.getName());
                   } catch (RuntimeException e) {
                           throw new RuntimeException("Error with cookie name: " + cookie.getName(),e);
                   }
           }
           return changes;
   }

   public void discardChanges(String pageName) {
       String fullPrefix = persistenceType + SEPERATOR + pageName + SEPERATOR;
       for (Cookie cookie: getCookiesStartingWith(fullPrefix)) {
           deleteCookie(cookie.getName());
       }
   }

    private List<Cookie> getCookiesStartingWith(String prefix) {
        List<Cookie> cookieList = new ArrayList<Cookie>();
        Cookie cookies[] = requestGlobals.getHTTPServletRequest().getCookies();
        if (cookies != null) for (int i = 0; i < cookies.length; i++) if (cookies[i].getName().startsWith(prefix)) cookieList.add(cookies[i]);
        return cookieList;
    }

    private void createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        requestGlobals.getHTTPServletResponse().addCookie(cookie);
    }

    private void deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "_"); //'empty values may cause problems'
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        requestGlobals.getHTTPServletResponse().addCookie(cookie);
    }

}
