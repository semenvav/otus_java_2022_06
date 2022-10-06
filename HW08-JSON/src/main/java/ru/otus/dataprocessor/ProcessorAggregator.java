package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        Map<String, Double> map = new HashMap<>();
        for(Measurement measurement : data){
            map.put(measurement.getName(),
                    map.getOrDefault(measurement.getName(), 0.0) + measurement.getValue());
        }
        return map;
    }
}
