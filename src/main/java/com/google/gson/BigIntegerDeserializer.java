package com.google.gson;

import java.lang.reflect.Type;
import java.math.BigInteger;

/**
 * <p>BigInteger deserializer</p>
 * 
 *
 * @author pietrot -- Auriga S.p.A.
 */
public class BigIntegerDeserializer extends NumberDeserializer implements JsonDeserializer<BigInteger> {

    /**
     * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
     */
    public BigInteger deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Long value = getLongValue(json, typeOfT, context);
        return value != null ? BigInteger.valueOf(value.longValue()) : null;
    }

}
