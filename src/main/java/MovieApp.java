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
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            /*
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
             */


            //Funktionsanrop
            long amount = allMovies(movieList);
            System.out.println("År 1975 gjordes " + amount + " filmer.");

            int longestRuntime = longestMovie(movieList);
            System.out.println("Den längsta filmen är " + longestRuntime + " minuter lång.");

            long uniqueGenresLong = uniqueGenres(movieList);
            System.out.println("Antal unika genrer är " + uniqueGenresLong + " stycken.");

            List<String> highestRatedCastList = highestRankedCast(movieList);
            System.out.print("Skådespelare från filmer med högst rating: ");
            System.out.print(String.join(", ", highestRatedCastList));
            System.out.println(".");

            System.out.println("Filmen med lägst antal skådespelare är " + movieFewestActors(movieList) + ".");

            System.out.println("Antal skådespelare som är med i flera filmer är " + actorsMultipleMovies(movieList)
            + " stycken.");

            System.out.println(actorMostMovies(movieList) + " var med i flest filmer.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MovieApp m = new MovieApp();
    }

    public long allMovies(List<Movie> l) {
        long numberOfMovies;
        numberOfMovies = l.stream().count();
        return numberOfMovies;
    }

    public int longestMovie(List<Movie> l) {
        return l.stream().mapToInt(Movie::getRuntime).summaryStatistics().getMax();
    }

    public long uniqueGenres(List<Movie> l) {
        long numberOfGenres;
        numberOfGenres = l.stream().flatMap(a -> a.getGenres().stream()).distinct().count();
        return numberOfGenres;
    }

    public List<String> highestRankedCast(List<Movie> l) {
        List<String> highestRatedCast;
        highestRatedCast = l.stream()
                //mappar filmer med maxrating och filtrerar sedan ut desa
                .filter(a -> a.getImdbRating() == l.stream().mapToDouble(Movie::getImdbRating).max().getAsDouble())
                //plattar ut listorna på skådespelare med högst rating
                .flatMap(b -> b.getCast().stream())
                //gör en sorterad lista av strömmen
                .sorted().toList();
        //Returnera listan
        return highestRatedCast;
    }

    public String movieFewestActors(List<Movie> l) {
        return l.stream()
                //Film med lägst antal cast med comparator
                .min(Comparator.comparingInt(a -> a.getCast().size()))
                //Mappa om till titel, plocka ut titel med orElse istället för get
                .map(Movie::getTitle).orElse(null);
    }

    public long actorsMultipleMovies(List<Movie> l){
        //Alla skådespelare i en lång String-lista
        List<String> allActors = l.stream()
                .flatMap(a -> a.getCast()
                .stream()).toList();

        //Filtrera hur många gånger b förekommer i listan, räkna antal unika skådespelare
        return allActors.stream()
                .filter(b -> Collections.frequency(allActors, b) > 1)
                .distinct()
                .count();
    }

    public String actorMostMovies(List<Movie> l) {
        //Alla skådespelare i en lång String-lista
        List<String> allActors = l.stream()
                .flatMap(a -> a.getCast()
                .stream()).toList();

        //Filtrera hur många gånger b förekommer i listan, hitta maxvärdet för förekomster
        int mostMovies = allActors.stream()
                .mapToInt(b -> Collections.frequency(allActors, b))
                .summaryStatistics()
                .getMax();

        //Hitta den/de skådespelare som har det maximala antalet förekomster
        return allActors.stream()
                .filter(c -> Collections.frequency(allActors, c) == mostMovies)
                .findAny().orElse(null);
    }

}