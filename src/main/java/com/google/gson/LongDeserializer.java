package com.google.gson;

import java.lang.reflect.Type;

/**
 * <p>LongDeserializer class, used to deserialize Long number</p>
 * 
 *
 * @author pietrot -- Auriga S.p.A.
 */
public class LongDeserializer extends NumberDeserializer implements JsonDeserializer<Long> {

    /**
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        return getLongValue(json, typeOfT, context);
    }
}
