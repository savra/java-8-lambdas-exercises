package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class LongestName {

    public static Artist byReduce(List<Artist> artists) {
        return artists.stream()
                .reduce((acc, artist2) -> acc.getName().length() >= artist2.getName().length() ? acc : artist2)
                .orElseThrow(RuntimeException::new);
    }

    public static Artist byCollecting(List<Artist> artists) {
        return artists.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(value -> value.getName().length())))
                .orElseThrow(RuntimeException::new);

    }

}
