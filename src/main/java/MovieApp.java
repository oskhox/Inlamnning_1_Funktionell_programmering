import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
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

            MovieFunctions m = new MovieFunctions();

            //Funktionsanrop
            System.out.println("År 1975 gjordes " + m.allMovies(movieList) + " filmer.");

            System.out.println("Den längsta filmen är " + m.longestMovie(movieList) + " minuter lång.");

            System.out.println("Antal unika genrer är " + m.uniqueGenres(movieList) + " stycken.");

            System.out.print("Skådespelare från filmer med högst rating: ");
            System.out.print(String.join(", ", m.highestRankedCast(movieList)));
            System.out.println(".");

            System.out.println("Filmen med lägst antal skådespelare är " + m.movieFewestActors(movieList) + ".");

            System.out.println("Antal skådespelare som är med i flera filmer är " + m.actorsMultipleMovies(movieList) + " stycken.");

            System.out.println(m.actorMostMovies(movieList) + " var med i flest filmer.");

            System.out.println("Antal unika språk är " + m.uniqueLanguages(movieList) + " stycken.");

            if (m.sameTitle(movieList)) System.out.println("Det finns flera filmer med samma titel.");
            else System.out.println("Inga filmer delar titel.");

            //HOF: Implementation av interfacet (1)
            LongestMovieInterface longestM = (m2) -> m2.getRuntime();

            //HOF: Anrop HOF (3)
            System.out.println("HOF: Längsta filmen är " + m.longestMovieHOF(movieList, longestM) + " minuter lång.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MovieApp m = new MovieApp();
    }
}