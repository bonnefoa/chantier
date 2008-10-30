package fr.chantier.tapestry.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;
import org.apache.tapestry5.services.RequestGlobals;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: bonnefoy
 * Date: 29 oct. 2008
 * Time: 17:02:35
 * To change this template use File | Settings | File Templates.
 */
public class OutputDecimal {

    @Parameter(required = true)
    private Object _value;

    @Parameter("componentResources.elementName")
    private String _elementName;

    @Inject
    private ComponentResources _resources;

    @Inject
    private RequestGlobals _requestGlobals;

    boolean beginRender(MarkupWriter writer) throws Exception {
        String formatted = null;
        formatted = decimal().format(_value);
        if (InternalUtils.isNonBlank(formatted)) {
            if (_elementName != null) {
                writer.element(_elementName);
                _resources.renderInformalParameters(writer);
            }
            writer.writeRaw(formatted);
            if (_elementName != null)
                writer.end();
        }
        return false;
    }

    public DecimalFormat decimal() {
        DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(
                _requestGlobals.getRequest().getLocale());
        format.setDecimalSeparatorAlwaysShown(false);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(0);
        return format;
    }
}