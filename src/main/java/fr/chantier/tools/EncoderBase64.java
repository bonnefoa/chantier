/*
 * Created on 13 Apr 2008
 *
 */
package fr.chantier.tools;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.internal.util.Base64ObjectInputStream;
import org.apache.tapestry5.internal.util.Base64ObjectOutputStream;
import org.apache.tapestry5.ioc.internal.util.InternalUtils;

import java.io.ObjectInputStream;

public class EncoderBase64 implements ValueEncoder<Object> {

        public Object toValue(String clientValue) {
        Object value = null;
        ObjectInputStream in = null;
        try {
            in = new Base64ObjectInputStream(clientValue);
            value = in.readObject();

        } catch (Exception e) {
            throw new RuntimeException("client state corrupted", e);
        } finally {
            InternalUtils.close(in);
        }
        return value;
    }

         public String toClient(Object value) {
         Base64ObjectOutputStream os = null;
         try {
             os = new Base64ObjectOutputStream();
             os.writeObject(value);
         } catch (Exception ex) {
             throw new RuntimeException(ex.getMessage(), ex);
         } finally {
             InternalUtils.close(os);
         }
         return os.toBase64();
    }


}
