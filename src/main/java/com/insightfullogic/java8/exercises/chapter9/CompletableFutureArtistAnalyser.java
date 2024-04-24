package com.insightfullogic.java8.exercises.chapter9;

import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.exercises.Exercises;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

public class CompletableFutureArtistAnalyser implements ArtistAnalyzer {

    private final Function<String, Artist> artistLookupService;

    public CompletableFutureArtistAnalyser(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler) {
        CompletableFuture.supplyAsync(() -> getNumberOfMembers(artistName))
                .thenCombine(CompletableFuture.completedFuture(getNumberOfMembers(otherArtistName)),
                        (firstArtistNumberOfMembers, secondArtistNumberOfMembers) -> firstArtistNumberOfMembers > secondArtistNumberOfMembers)
                .thenAccept(handler);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName)
                .getMembers()
                .count();
    }

}
