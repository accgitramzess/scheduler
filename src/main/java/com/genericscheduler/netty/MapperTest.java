package com.genericscheduler.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class MapperTest {

    private List<String> labels = new ArrayList<>();

    public MapperTest() {
        labels.add("Label1");
        labels.add("Label2");
        labels.add("Label3");
    }

    public <T> T getValueOrDefault(String label, Function<String, T> mapper, T defaultValue) {
        return labels
                .stream()
                .filter(l -> Objects.equals(l, label))
                .map(mapper)
                .findFirst()
                .get();
    }

    public <T> T getValueOrDefault(String label, Function<String, T> mapper) {
        return labels
                .stream()
                .filter(l -> Objects.equals(l, label))
                .map(mapper)
                .findFirst()
                .get();
    }
}
