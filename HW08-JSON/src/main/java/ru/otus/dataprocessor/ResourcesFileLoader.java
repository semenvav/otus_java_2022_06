package ru.otus.dataprocessor;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.otus.model.Measurement;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class ResourcesFileLoader implements Loader {

    File file;

    public ResourcesFileLoader(String fileName) {
        file = getFileFromResource(fileName);
    }

    @Override
    public List<Measurement> load() {
        //читает файл, парсит и возвращает результат
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Measurement>>(){}.getType();
            return gson.fromJson(reader.readLine(), listType);
        }
        catch (Exception e){
            throw new FileProcessException(e);
        }
    }

    private File getFileFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        try {
            return new File(resource.toURI());
        }catch (Exception e){
            throw new FileProcessException(e);
        }
    }
}
