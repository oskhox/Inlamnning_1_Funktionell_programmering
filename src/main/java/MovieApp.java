import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieApp {
    public MovieApp() {

        String uri = "mongodb+srv://osk_testuser:CnP0c6K5agpZzFve@functionalprogramming.qfawe.mongodb.net/?retryWrites=true&w=majority&appName=functionalProgramming";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Alla filmer från 1975
            final List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            //Funktionsanrop
            System.out.println("År 1975 gjordes " + allMovies(movieList) + " filmer.");

            System.out.println("Den längsta filmen är " + longestMovie(movieList) + " minuter lång.");

            System.out.println("Antal unika genrer är " + uniqueGenres(movieList) + " stycken.");

            System.out.print("Skådespelare från filmer med högst rating: ");
            System.out.print(String.join(", ", highestRankedCast(movieList)));
            System.out.println(".");

            System.out.println("Filmen med lägst antal skådespelare är " + movieFewestActors(movieList) + ".");

            System.out.println("Antal skådespelare som är med i flera filmer är " + actorsMultipleMovies(movieList) + " stycken.");

            System.out.println(actorMostMovies(movieList) + " var med i flest filmer.");

            System.out.println("Antal unika språk är " + uniqueLanguages(movieList) + " stycken.");

            if (sameTitle(movieList)) System.out.println("Det finns flera filmer med samma titel.");
            else System.out.println("Inga filmer delar titel.");

            //HOF: Implementation av interfacet (1)
            LongestMovieInterface longestM = (m) -> m.getRuntime();

            //HOF: Anrop HOF (3)
            System.out.println("HOF: Längsta filmen är " + longestMovieHOF(movieList, longestM) + " minuter lång.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MovieApp m = new MovieApp();
    }

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