package com.insightfullogic.java8.answers.chapter4;

import com.insightfullogic.java8.examples.chapter1.Artist;

import java.util.stream.Stream;

public interface PerformanceFixedMy {
    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return Stream.concat(getMusicians(), getMusicians()
                .filter(artist -> artist.getMembers().count() > 1)
                .flatMap(Artist::getMembers));
    }
}
