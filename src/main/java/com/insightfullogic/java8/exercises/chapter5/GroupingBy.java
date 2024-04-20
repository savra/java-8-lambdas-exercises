package com.insightfullogic.java8.exercises.chapter5;

import com.insightfullogic.java8.exercises.Exercises;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final Function<? super T, ? extends K> classifier;

    public GroupingBy(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, element) -> {
            K key = classifier.apply(element);
            List<T> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(element);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return ((kListMap, kListMap2) -> {
            kListMap.putAll(kListMap2);
            return kListMap;
        });
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.UNORDERED);
    }

}
