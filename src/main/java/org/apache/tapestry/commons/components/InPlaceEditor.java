/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/
 *
 * Copyright 1996-2008 by Sven Homburg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.apache.tapestry.commons.components;

import org.apache.tapestry.commons.base.AbstractAjaxField;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

import java.util.List;


/**
 * a "just in place" edit component that dont must emmbedded in a form.
 *
 * @author <a href="mailto:homburgs@googlemail.com">S.Homburg</a>
 * @version $Id: InPlaceEditor.java 682 2008-05-20 22:00:02Z homburgs $
 */
@IncludeJavaScriptLibrary(value = {"${tapestry.scriptaculous}/controls.js"})
public class InPlaceEditor extends AbstractAjaxField {
   public final static String SAVE_EVENT = "save";

   /**
    * The value to be read and updated. This is not necessarily a string, a translator may be provided to convert
    * between client side and server side representations. If not bound, a default binding is made to a property of the
    * container matching the component's id. If no such property exists, then you will see a runtime exception due to
    * the unbound value parameter.
    */
   @Parameter(required = true, principal = true)

   private String _value;

   /**
    * Type de validation a faire sur le champ
    */
   @Parameter(required = false)
   private boolean _isDouble;

   /**
    * Size of the input text tag.
    */
   @Parameter(value = "20", required = false, defaultPrefix = BindingConstants.LITERAL)
   private String _size;

   /**
    * max. input length of the input text tag.
    */
//    @Parameter(value = "20", required = false, defaultPrefix = TapestryConstants.LITERAL_BINDING_PREFIX)
//    private String _maxlength;

   /**
    * The context for the link (optional parameter). This list of values will be converted into strings and included in
    * the URI.
    */
   @Parameter(required = false)
   private List _context;

   @Inject
   private ComponentResources _resources;

   @Inject
   private Messages _messages;

   @Environmental
   private RenderSupport _pageRenderSupport;

   @Inject
   private Request _request;

   private Object[] _contextArray;

   Binding defaultValue() {
      return createDefaultParameterBinding("value");
   }

   void setupRender() {
      _contextArray = _context == null ? new Object[0] : _context.toArray();
   }

   void beginRender(MarkupWriter writer) {
      writer.element("span", "id", getClientId());

      if (_value != null && _value.length() > 0)
         writer.write(_value);
      else
         writer.writeRaw(_messages.get("empty"));
   }

   void afterRender(MarkupWriter writer) {
      writer.end();
      _pageRenderSupport.addScript("new Ajax.InPlaceEditor('%s', '%s', {cancelControl: 'button', cancelText: '%s', " +
              "clickToEditText: '%s', savingText: '%s', okText: '%s', htmlResponse: true, size: %s, stripLoadedTextTags: true});",
              getClientId(), getActionLink(),
              _messages.get("cancelbutton"),
              _messages.get("title"),
              _messages.get("saving"),
              _messages.get("savebutton"),
              _size);
   }

   public String getActionLink() {
      Link link = _resources.createActionLink(EventConstants.ACTION, true, _contextArray);
      return link.toAbsoluteURI();
   }

   StreamResponse onAction(String value) {
      String valueText = _request.getParameter("value");
      if (valueText == null)
         valueText = "";
      if (_isDouble) {
         try {
            Double.parseDouble(valueText);
         } catch (NumberFormatException e) {
            valueText = "0";
         }
      }
      _resources.triggerEvent(SAVE_EVENT, new Object[]{value, valueText}, null);

      if (valueText.length() == 0)
         valueText = "";

      return new TextStreamResponse("text/html", valueText);
   }

}