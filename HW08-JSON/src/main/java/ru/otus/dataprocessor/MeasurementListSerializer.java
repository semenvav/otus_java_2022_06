package ru.otus.dataprocessor;

import com.google.gson.*;
import ru.otus.model.Measurement;

import java.lang.reflect.Type;
import java.util.List;

public class MeasurementListSerializer implements JsonSerializer<List<Measurement>> {

    @Override
    public JsonElement serialize(List<Measurement> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        for(Measurement measurement : src) {
            result.add(measurement.getName(), new JsonPrimitive(measurement.getValue()));
        }
        return result;
    }
}