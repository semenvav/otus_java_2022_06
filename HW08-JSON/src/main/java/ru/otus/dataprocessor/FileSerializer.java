package ru.otus.dataprocessor;


import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import ru.otus.model.Measurement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSerializer implements Serializer {
    //формирует результирующий json и сохраняет его в файл

    File file;

    public FileSerializer(String fileName) {
        file = new File(fileName);
    }

    @Override
    public void serialize(Map<String, Double> data) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            List<Measurement> measurementList = new ArrayList<>();
            for(String key : data.keySet()){
                measurementList.add(new Measurement(key, data.get(key)));
            }
            measurementList = measurementList.stream().
                    sorted((Comparator.comparing(Measurement::getName))).
                    collect(Collectors.toList());
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(measurementList.getClass(), new MeasurementListSerializer());
            Gson gson = builder.create();
            writer.write(gson.toJson(measurementList));
        }
        catch (Exception e){
            throw new FileProcessException(e);
        }
    }
}
