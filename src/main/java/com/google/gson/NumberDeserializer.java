package com.google.gson;

import java.lang.reflect.Type;

/**
 * <p>Number deserializers common class</p>
 * 
 *
 * @author pietrot -- Auriga S.p.A.
 */
abstract class NumberDeserializer {

    protected Long getLongValue(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        Long deserialized = null;
        try {
            if (json.isJsonObject()) {
                JsonElement numberLongElement = json.getAsJsonObject().get("$numberLong");
                if (numberLongElement != null) {
                    deserialized = Long.decode(json.getAsJsonObject().get("$numberLong").getAsString());
                }
            } else {
                deserialized = Long.valueOf(json.getAsLong());
            }
        } catch (@SuppressWarnings("unused") Exception ign) {
            /*
             * ignored, nothing to do here.
             */
        }
        return deserialized;
    }

}
