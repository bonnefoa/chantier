package fr.chantier.tapestry.services;

import fr.chantier.service.*;
import fr.chantier.service.impl.*;
import fr.chantier.dao.*;
import fr.chantier.dao.impl.*;
import fr.chantier.tapestry.base.CookiePersistentField;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.hibernate.HibernateTransactionDecorator;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.*;
import org.slf4j.Logger;

import java.io.IOException;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule {
    public static void bind(ServiceBinder binder) {
        binder.bind(ClientsManager.class, ClientsManagerImpl.class);
        binder.bind(CommandesManager.class, CommandesManagerImpl.class);
        binder.bind(IntervenantsManager.class, IntervenantsManagerImpl.class);
        binder.bind(SousTraitantsManager.class, SousTraitantsManagerImpl.class);
        binder.bind(HistoriqueHeuresManager.class, HistoriqueHeuresManagerImpl.class);
        binder.bind(HistoriqueSommeManager.class, HistoriqueSommeManagerImpl.class);
        binder.bind(CoefficientManager.class, CoefficientManagerImpl.class);

        binder.bind(CommandesDAO.class, CommandesDAOImpl.class);
        binder.bind(ClientsDAO.class, ClientsDAOImpl.class);
        binder.bind(CoefficientDAO.class, CoefficientDAOImpl.class);
        binder.bind(HistoriqueHeuresDAO.class, HistoriqueHeuresDAOImpl.class);
        binder.bind(HistoriqueSommeDAO.class, HistoriqueSommeDAOImpl.class);
        binder.bind(IntervenantsDAO.class, IntervenantsDAOImpl.class);
        binder.bind(SousTraitantsDAO.class, SousTraitantsDAOImpl.class);
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
        // Make bind() calls on the binder object to define most IoC services.
        // Use service builder methods (example below) when the implementation
        // is provided inline, or requires more initialization than simply
        // invoking the constructor.
    }

    public void contributeHibernateEntityPackageManager(Configuration<String> configuration) {
//      extra packages...
//      tapestry-hibernate will add entities package automaticaly for TAPESTRY_APP_PACKAGE_PARAM+".entities"
//      configuration.add("tapestry.mini.entities");
    }

    /**
     * Gere le @commitAfter
     *
     * @param decorator
     * @param serviceInterface
     * @param delegate
     * @param serviceId
     * @param <T>
     * @return
     */
    @Match("*DAO")
    public static <T> T decorateTransactionally(HibernateTransactionDecorator decorator, Class<T> serviceInterface,
                                                T delegate,
                                                String serviceId) {
        return decorator.build(serviceInterface, delegate, serviceId);
    }

    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration) {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).

        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en");

        // The factory default is true but during the early stages of an application
        // overriding to false is a good idea. In addition, this is often overridden
        // on the command line as -Dtapestry.production-mode=false
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
    }


    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     * <p/>
     * <p/>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     * <p/>
     * <p/>
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log) {
        return new RequestFilter() {
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException {
                long startTime = System.currentTimeMillis();

                try {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                }
                finally {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    public void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
                                         @Local
                                         RequestFilter filter) {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }

    public void contributePersistentFieldManager(MappedConfiguration<String, PersistentFieldStrategy> configuration,
                                                 RequestGlobals requestGlobals, Request request) {
        configuration.add(CookiePersistentField.COOKIE, new CookiePersistentField(requestGlobals, request, CookiePersistentField.COOKIE));
        configuration.add(CookiePersistentField.FLASHCOOKIE, new CookiePersistentField(requestGlobals, request, CookiePersistentField.FLASHCOOKIE));
    }

}
