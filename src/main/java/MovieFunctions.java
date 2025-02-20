import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieFunctions {

    public long allMovies(List<Movie> l) {
        return l.stream().count();
    }

    public int longestMovie(List<Movie> l) {
        return l.stream().mapToInt(a -> a.getRuntime())
                .summaryStatistics().getMax();
    }

    public long uniqueGenres(List<Movie> l) {
        return l.stream()
                .flatMap(a -> a.getGenres().stream())
                .distinct()
                .count();
    }

    public List<String> highestRankedCast(List<Movie> l) {
        return l.stream()
                //Mappar filmer med maxrating, filtrerar
                .filter(a -> a.getImdbRating() == l.stream().mapToDouble(Movie::getImdbRating).max().orElseThrow())
                .flatMap(b -> b.getCast().stream())
                .sorted().toList();
    }

    public String movieFewestActors(List<Movie> l) {
        return l.stream()
                //Film med lägst antal cast med comparator
                .min(Comparator.comparingInt(a -> a.getCast().size()))
                .map(Movie::getTitle).orElseThrow();
    }

    public long actorsMultipleMovies(List<Movie> l) {
        final List<String> allActors = l.stream()
                .flatMap(a -> a.getCast()
                        .stream()).toList();

        return allActors.stream()
                .filter(a -> Collections.frequency(allActors, a) > 1)
                .distinct()
                .count();
    }

    public String actorMostMovies(List<Movie> l) {
        final List<String> allActors = l.stream()
                .flatMap(a -> a.getCast().stream())
                .toList();

        //Vilken skådespelare i flest filmer
        final int mostMovies = allActors.stream()
                .mapToInt(b -> Collections.frequency(allActors, b))
                .summaryStatistics().getMax();

        //Vad skådespelaren heter
        return allActors.stream()
                .filter(c -> Collections.frequency(allActors, c) == mostMovies)
                .findAny().orElseThrow();
    }

    public long uniqueLanguages(List<Movie> l) {
        return l.stream()
                .flatMap(a -> a.getLanguages().stream())
                .distinct()
                .count();
    }

    public boolean sameTitle(List<Movie> l) {
        final List<String> allTitles = l.stream()
                .map(Movie::getTitle)
                .toList();

        return allTitles.stream()
                .anyMatch(a -> Collections.frequency(allTitles, a) > 1);
    }

    //HOF med interfacet som inparameter (2)
    public int longestMovieHOF(List<Movie> l, LongestMovieInterface lmi) {
        return l.stream().mapToInt(a -> lmi.movieLength(a)).summaryStatistics().getMax();
    }
}