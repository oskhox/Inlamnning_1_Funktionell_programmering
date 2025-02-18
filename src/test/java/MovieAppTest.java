import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class MovieAppTest {

    //Testdatabas
    public TestDatabase tdb = new TestDatabase();
    List<Movie> testMovies = tdb.getTestMovies();

    //Riktiga koden för att testa funktionerna
    MovieApp app = new MovieApp();

    //Testar med riktiga metoder mot testdatat
    @Test
    public void numberOfMovies(){
    assertEquals(10, app.allMovies(testMovies));
    }

    @Test
    public void longestMovie(){
        assertEquals(200, app.longestMovie(testMovies));
    }

    @Test
    public void uniqueGenres(){
        assertEquals(8, app.uniqueGenres(testMovies));
    }

    @Test
    public void highestRankedCast(){
        List<String> expected = List.of("Gary Roberts", "Nikolas Simmonds", "Pamela Brighton", "Paul Copley");
        assertEquals(expected, app.highestRankedCast(testMovies));
    }

    @Test
    public void movieFewestActors(){
        assertEquals("End of the Game", app.movieFewestActors(testMovies));
    }

    }